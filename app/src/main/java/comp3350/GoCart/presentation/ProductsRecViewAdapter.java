package comp3350.GoCart.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.R;
import comp3350.GoCart.objects.StoreProduct;

public class ProductsRecViewAdapter extends RecyclerView.Adapter<ProductsRecViewAdapter.ViewHolder> {

    private List<StoreProduct> products;
    AccessProducts accessProducts = new AccessProducts();

    public ProductsRecViewAdapter() {
        products = new ArrayList<>();
    }

    // Create instance of view holder for every product
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewGroup is the parent of all layouts (RelativeLayout, LinearLayout, etc.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view, new cartClickListener() {
            @Override
            public void onAdd(String p,int quant) {
                ShoppingCart.getInstance().addProduct(accessProducts.searchProductsByName(p).get(0),quant);
            }
        });
        return holder;
    }

    // Modify views (UI Element) here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(products.get(position).getProductName());
        holder.productPrice.setText(products.get(position).getPrice().toString());
    }

    @Override
    public int getItemCount() {
        int size = 0;

        if (products != null)
            return products.size();

        return size;
    }

    // Set the stores that we want to display
    public void setProducts(List<StoreProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    protected List<StoreProduct> getProducts(){
        return products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        cartClickListener listener;
        private TextView productName;
        private TextView productPrice;
        private CardView parent;

        private EditText cartQuantity;
        private Button cartAddTo;

        public ViewHolder(@NonNull View itemView,cartClickListener listener) {
            super(itemView);

            // Variables related to the card
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            parent = itemView.findViewById(R.id.parent);
            cartQuantity = itemView.findViewById(R.id.etxtQuantityEntry);
            cartAddTo = itemView.findViewById(R.id.btnAddToCart);

            this.listener = listener ;
            cartAddTo.setOnClickListener(this);

        }


        //Adds product selected with quantity entered into shopping cart
        @Override
        public void onClick(View view){
            listener.onAdd(productName.getText().toString(),Integer.parseInt(cartQuantity.getText().toString()));

        }
    }

    public interface cartClickListener{
        void onAdd(String prodName,int quant);
    }

}
