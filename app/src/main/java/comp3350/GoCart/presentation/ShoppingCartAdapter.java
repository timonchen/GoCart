package comp3350.GoCart.presentation;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.R;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private Context context;
    private List<Product> products;
    private List<Integer> quantites;
    private TextView editPrice;


    public ShoppingCartAdapter(Context context, List<Product> products, List<Integer> quantites, TextView price) {
        this.context = context;
        this.products = products;
        this.quantites = quantites;
        this.editPrice = price;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShoppingCart cart = ShoppingCart.getInstance();
        View v = LayoutInflater.from(context).inflate(R.layout.cart_list_item,parent,false);

        //Click listener that for each individual product, adds product to cart when clicked
        ViewHolder holder = new ViewHolder(v, new clickListener() {
            @Override
            public void clicked(Product p, int quant) {
                ShoppingCart.getInstance().incrementProductQuantity(p);
            }
        });
        return  holder;
    }

    // sets name and price for each product card
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productname.setText(products.get(position).getProductName());
        holder.productQuantity.setText(quantites.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        clickListener clickListener;
        private TextView productname;
        private EditText productQuantity;
        private Button incButton;
        private Button decButton;
        private Button changeQuantbtn;

        private AccessProducts accessProducts = new AccessProducts();
        private AccessStoreProduct accessStoreProduct= new AccessStoreProduct();


        public ViewHolder(@NonNull View itemView, clickListener Listener) {
            super(itemView);
            productname = itemView.findViewById(R.id.txtProductName);
            productQuantity = itemView.findViewById(R.id.txtSetQuantity);
            incButton = itemView.findViewById(R.id.btnIncQuantity);
            decButton = itemView.findViewById(R.id.btnDecQuantity);
            changeQuantbtn = itemView.findViewById(R.id.btnChangeQuantity);

            this.clickListener = Listener;
            incButton.setOnClickListener(this);
            decButton.setOnClickListener(this);
            changeQuantbtn.setOnClickListener(this);

        }

        /*
        Listener for three buttons, increments or decrements cart items or sets quantity based
        off button pressed
         */
        @Override
        public void onClick(View view){
            ShoppingCart cart = ShoppingCart.getInstance();
            Product prod = accessProducts.searchProductsByName(productname.getText().toString()).get(0);

            if (view.getId() == R.id.btnDecQuantity)
                cart.decrementProductQuantity(prod);
            else if (view.getId() == R.id.btnIncQuantity)
                cart.incrementProductQuantity(prod);
            else if (view.getId() == R.id.btnChangeQuantity){
                cart.changeProductQuantity(prod,Integer.parseInt(productQuantity.getText().toString()));
            }
            productQuantity.getText().clear();
            productQuantity.append(cart.getQuantity(prod).toString());
            editPrice.setText(ShoppingCart.getInstance().calculateTotal(accessStoreProduct).toString());
            notifyDataSetChanged();
        }

    }

    public interface clickListener{
        void clicked(Product p,int quant);
    }




}
