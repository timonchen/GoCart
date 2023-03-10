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

import java.math.BigDecimal;
import java.util.List;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.R;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    //private HashMap<Product,Integer> prodData;
    Context context;
    private List<Product> products;
    private List<Integer> quantites;
    private Product prodListing;
    private TextView editPrice;
    private TextView notif,lp;
    private Button changeStore;


    public ShoppingCartAdapter(Context context, List<Product> products, List<Integer> quantites, TextView price,TextView newNotif,TextView newLP,Button change) {
        this.context = context;
        this.products = products;
        this.quantites = quantites;
        this.editPrice = price;
        this.notif = newNotif;
        this.lp = newLP;
        this.changeStore = change;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

 */


        View v = LayoutInflater.from(context).inflate(R.layout.cart_list_item,parent,false);
        ViewHolder holder = new ViewHolder(v, new clickListener() {
            @Override
            public void clicked(Product p, int quant) {
                ShoppingCart.getInstance().incrementProductQuantity(p);
            }
        });




        return  holder;
    }

    // Modify views here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productname.setText(products.get(position).getProductName());
        holder.productQuantity.setText(quantites.get(position).toString());



    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setCartProducts(List<Product> prods,List<Integer> quant) {
        this.products = prods;
        this.quantites = quant;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        clickListener clickListener;
        private TextView productname;
        private TextView selectedStorePrice;
        private TextView cheaperStorePrice;
        private EditText productQuantity;
        private Button incButton;
        private Button decButton;
        private AccessProducts ap = new AccessProducts();


        public ViewHolder(@NonNull View itemView, clickListener Listener) {
            super(itemView);
            productname = itemView.findViewById(R.id.txtProductName);
            productQuantity = itemView.findViewById(R.id.txtSetQuantity);
            incButton = itemView.findViewById(R.id.btnIncQuantity);
            decButton = itemView.findViewById(R.id.btnDecQuantity);


            this.clickListener = Listener;
            incButton.setOnClickListener(this);
            decButton.setOnClickListener(this);


        }
        @Override
        public void onClick(View view){
            Product prod = ap.searchProductsByName(productname.getText().toString()).get(0);
            if (view.getId() == R.id.btnDecQuantity)
                ShoppingCart.getInstance().decrementProductQuantity(prod);
            else
                ShoppingCart.getInstance().incrementProductQuantity(prod);
            productQuantity.getText().clear();
            productQuantity.append(ShoppingCart.getInstance().getQuantity(prod).toString());
            editPrice.setText(ShoppingCart.getInstance().calculateTotal().toString());
            notifyDataSetChanged();


            AccessStoreProduct aSP = new AccessStoreProduct();
            AccessStores aS = new AccessStores();
            //BigDecimal cheapest = aSP.findCheapestStore(p,q,aS.getStores());
            ShoppingCartActivity.check(notif,lp,changeStore);


        }



    }

    public interface clickListener{
        void clicked(Product p,int quant);
    }



}
