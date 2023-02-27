package comp3350.GoCart.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;

import comp3350.GoCart.objects.Store;
import comp3350.GoCart.R;

public class StoresRecViewAdapter extends RecyclerView.Adapter<StoresRecViewAdapter.ViewHolder> {

    private List<Store> stores = new ArrayList<>(); // The stores in this list will be displayed

    public StoresRecViewAdapter() {

    }

    // Create instance of view holder for every store
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewGroup is the parent of all layouts (RelativeLayout, LinearLayout, etc.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stores_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Modify views here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(stores.get(position).getStoreName());
        holder.txtAddress.setText(stores.get(position).getStoreAddress());

        // When user clicks on an element in the recycler view, run this
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something on click
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;

        if (stores != null)
            return stores.size();

        return size;
    }

    // Set the stores that we want to display
    public void setStores(List<Store> storesToView) {
        stores = storesToView;
        notifyDataSetChanged();
    }

    protected List<Store> getStores(){
        return stores;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAddress;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName); // Since we are not in the activity, we have to go through itemView
            parent = itemView.findViewById(R.id.parent);
            txtAddress =itemView.findViewById(R.id.txtAddress);
        }
        protected TextView getTxtName() {
            return txtName;
        }
    }
}
