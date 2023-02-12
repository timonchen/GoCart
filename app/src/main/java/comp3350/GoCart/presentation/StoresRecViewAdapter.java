package comp3350.GoCart.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
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
        holder.txtName.setText(stores.get(position).getStoreName() + " " + stores.get(position).getStoreName());
    }

    @Override
    public int getItemCount() {
        return stores.size();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName); // Since we are not in the activity, we have to go through itemView
        }
        protected TextView getTxtName() {
            return txtName;
        }
    }
}
