package comp3350.GoCart.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.R;
import comp3350.GoCart.objects.StoreProduct;

public class ProductsRecViewAdapter extends RecyclerView.Adapter<ProductsRecViewAdapter.ViewHolder> {

    private List<StoreProduct> products;

    public ProductsRecViewAdapter() {
        products = new ArrayList<>();
    }

    // Create instance of view holder for every product
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewGroup is the parent of all layouts (RelativeLayout, LinearLayout, etc.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Modify views (UI Element) here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(products.get(position).getProductName());
        holder.productPrice.setText(products.get(position).getPrice().toString());

        // String price = "$" + products.get(position).getProductPrice().toString();
        // holder.productPrice.setText(price);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private TextView productPrice;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Variables related to the card
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}
