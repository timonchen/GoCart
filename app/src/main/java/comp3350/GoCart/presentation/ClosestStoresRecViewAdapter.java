package comp3350.GoCart.presentation;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import comp3350.GoCart.objects.Store;

public class    ClosestStoresRecViewAdapter extends StoresRecViewAdapter{

    public ClosestStoresRecViewAdapter(OnStoreListener listener) {
        super(listener);
    }

    //overide the super class method because we need to also get the distances and present to the user
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = super.getStores().get(position);
        NumberFormat format = new DecimalFormat("#0.0");

        holder.getTxtName().setText(store.getStoreName());
        holder.getTxtAddress().setText(store.getStoreAddress() + "\n" + String.valueOf(format.format(store.getDistToUser())) +"km" + "\n");
    }
}
