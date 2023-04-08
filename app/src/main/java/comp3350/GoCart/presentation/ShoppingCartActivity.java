package comp3350.GoCart.presentation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.math.BigDecimal;
import java.util.List;


import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.EmptyStore;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

public class ShoppingCartActivity extends Activity {



    private RecyclerView productRecView;
    private AccessStoreProduct accessStoreProduct = new AccessStoreProduct();
    private AccessStores accessStores = new AccessStores();;
    private ShoppingCart cart = ShoppingCart.getInstance();
    private TextView price;
    private Store cheapestStore;
    private TextView notif,lowerPrice;
    private Button change;
    private Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        accessStoreProduct = new AccessStoreProduct();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ShoppingCart cart = ShoppingCart.getInstance();
        BigDecimal selectedStorePrice;

        productRecView = findViewById(R.id.cartRecView);
        notif = findViewById(R.id.txtCheaperNotification);
        lowerPrice = findViewById(R.id.txtCheaperPrice);
        change = findViewById(R.id.btnChangeToCheaper);
        check = findViewById(R.id.btnCheckCheaper);
        price = findViewById(R.id.txtEditTotalPrice);


        List<Product> p = ShoppingCart.getInstance().getCartProducts();
        List<Integer> q = ShoppingCart.getInstance().getCartQuantity();

        //displays price based on carts contents
        selectedStorePrice =cart.calculateTotal(accessStoreProduct);
        price.setText(selectedStorePrice.toString());


        productRecView.setLayoutManager(new LinearLayoutManager(this));
        productRecView.setAdapter(new ShoppingCartAdapter(getApplicationContext(),p,q,price));

        /*
        click listener, Calls check method
         */
        check.setOnClickListener(view -> check());

        /*
        click listener, changes currently selected store to cheaper store found when check button was clicked
         */
        change.setOnClickListener(view -> {
            cheapestStore = accessStoreProduct.findCheapestStore(p,q,accessStores.getStores()).getStore();
            if (!(cheapestStore instanceof EmptyStore))
                ShoppingCart.getInstance().setStore(cheapestStore);
            check();
            price.setText(cart.calculateTotal(accessStoreProduct).toString());
            Intent returnIntent = new Intent();
            returnIntent.putExtra("newStoreID",cheapestStore.getStoreID());
            setResult(Activity.RESULT_OK,returnIntent);
        });


    }


    /*
    Displays result from search for cheaper store, toggles button visibility base on result
     */
    public void check(){
        StoreProduct cheapest;

        List<Product> p = ShoppingCart.getInstance().getCartProducts();
        List<Integer> q = ShoppingCart.getInstance().getCartQuantity();

        cheapest = accessStoreProduct.findCheapestStore(p,q,accessStores.getStores());
        BigDecimal cheapestValue = accessStoreProduct.calculateTotal(p,q,cheapest.getStore());

        if(cheapestValue.equals(BigDecimal.ZERO) || cheapestValue.equals(cart.calculateTotal(accessStoreProduct))) {
            notif.setText("No Lower Price Found");
            lowerPrice.setText("");
            change.setVisibility(View.INVISIBLE);
            check.setVisibility(View.VISIBLE);
            price.setText(cheapestValue.toString());
        } else {
            if(!cheapest.equals( cart.getStore() )){
                notif.setText("New Price:");
                lowerPrice.setText(cheapestValue.toString());
                change.setVisibility(View.VISIBLE);
                check.setVisibility(View.INVISIBLE);

            }
        }
    }




}