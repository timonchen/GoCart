package comp3350.GoCart.presentation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.R;
import comp3350.GoCart.objects.StoreProduct;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    //private HashMap<Product,Integer> prodData;
    private List<Product> products;
    private List<Integer> quantitys;
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

        if (products.isEmpty()) {
            holder.productname.setText("Cart is Empty");
        }
        else {  // Display our search results

            holder.productname.setText(products.get(position).getProductName());
            //prodListing = prodData.get(position).getKey();
            //holder.txtName.setText(prodListing.getProductName() + "  Quantity " + prodData.get(position).getValue());
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;

        if (products != null)
            return products.size();

        return size;
    }

    public void setCartProducts(List<Product> prods,List<Integer> quant ) {
        this.products = prods;
        this.quantitys = quant;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productname;
        private EditText productQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.txtProductName);
            productQuantity = itemView.findViewById(R.id.txtSetQuantity);

        }
        //protected TextView getTxtName() { return txtName;        }
    }

    public void incrementOnClick(View v) {



    }


}
