package comp3350.GoCart.presentation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.R;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private List<Map.Entry<Product,String>> prodData;
    private Product prodListing;


    public ShoppingCartAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewGroup is the parent of all layouts (RelativeLayout, LinearLayout, etc.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Modify views here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Display a message if there is nothing to display

        if (prodData.isEmpty()) {
            holder.txtName.setText("Cart is Empty");
        }
        else {  // Display our search results
            //holder.txtName.setText(stores.get(position).getStoreName() + " " + stores.get(position).getStoreAddress());
            prodListing = prodData.get(position).getKey();
            holder.txtName.setText(prodListing.getProductName() + "  Quantity " + prodData.get(position).getValue());
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;

        if (prodData != null)
            return prodData.size();

        return size;
    }
    /*

    // Set the stores that we want to display
    public void setStores(List<Store> storesToView) {
        stores = storesToView;
        notifyDataSetChanged();
    }

    protected List<Store> getStores(){
        return stores;
    }

 */

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName); // Since we are not in the activity, we have to go through itemView
        }
        protected TextView getTxtName() {
            return txtName;
        }
    }

    public void incrementOnClick(View v) {



    }
/*
    public void findStoreClosestOnClick(View v) {
        Intent storeClosestIntent = new Intent(FindStoreActivity.this, FindClosetStoreActivity.class);
        FindStoreActivity.this.startActivity(storeClosestIntent);
    }
*/



}
