package comp3350.GoCart.presentation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import comp3350.GoCart.R;
import comp3350.GoCart.business.AccessProducts;
import comp3350.GoCart.business.AccessStoreProduct;
import comp3350.GoCart.business.AccessStores;
import comp3350.GoCart.business.ShoppingCart;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

public class ShoppingCartActivity extends Activity {



    private RecyclerView productRecView;

    private Button changeStores;
    private TextView price;
    private ShoppingCartAdapter adapter;
    private StoresRecViewAdapter adapter2;
    private ShoppingCart sCart;
    private AccessStoreProduct aSP;
    private AccessStores aS;
    private StoreProduct cheapest;
    private TextView notif,lp;
    private Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ShoppingCart cart = ShoppingCart.getInstance();
        BigDecimal selectedStorePrice;
        BigDecimal lowerStorePrice;

        productRecView = findViewById(R.id.cartRecView);
        notif = findViewById(R.id.txtCheaperNotification);
        lp = findViewById(R.id.txtCheaperPrice);
        change = findViewById(R.id.btnChangeToCheaper);
        price = findViewById(R.id.txtEditTotalPrice);



        productRecView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> p = ShoppingCart.getInstance().getCartProducts();
        List<Integer> q = ShoppingCart.getInstance().getCartQuantity();
        selectedStorePrice =cart.calculateTotal(true);
        price.setText(selectedStorePrice.toString());
        productRecView.setAdapter(new ShoppingCartAdapter(getApplicationContext(),p,q,price,notif,lp,change));
        //check(notif,lp,change);
        change.setText("Check for cheaper price");
        change.setVisibility(View.VISIBLE);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessStoreProduct accessStoreProduct = new AccessStoreProduct();
                AccessStores accessStores = new AccessStores();

                check(notif,lp,change,price);
                ShoppingCart.getInstance().setStore(accessStoreProduct.findCheapestStore(p,q,accessStores.getStores()).getStore());
                //price.setText(cart.calculateTotal(true).toString());


            }
        });


    }



    public static void check(TextView newNotif,TextView newLp,Button change,TextView topPrice){
    //because i dont know any better
        TextView notif;
        TextView lowerPrice;
        TextView tp;
        AccessStoreProduct aSP;
        AccessStores aS;
        StoreProduct cheapest;
        Button changeStores;


        notif = newNotif;
        lowerPrice = newLp;
        changeStores = change;
        tp = topPrice;
        ShoppingCart cart = ShoppingCart.getInstance();
        aSP = new AccessStoreProduct();
        aS = new AccessStores();

        List<Product> p = ShoppingCart.getInstance().getCartProducts();
        List<Integer> q = ShoppingCart.getInstance().getCartQuantity();

        cheapest = aSP.findCheapestStore(p,q,aS.getStores());

        BigDecimal cheapestValue = aSP.calculateTotal(p,q,cheapest.getStoreId());



        if(cheapestValue.equals(BigDecimal.ZERO) || cheapestValue.equals(cart.calculateTotal(true))) {
            notif.setText("");
            lowerPrice.setText("");
            changeStores.setVisibility(View.INVISIBLE);
            changeStores.setText("Set to cheaper store");
            tp.setText(cheapestValue.toString());
        } else {
            if(!cheapest.equals( cart.getStore() )){
                notif.setText("New Price:");
                lowerPrice.setText(cheapestValue.toString());
                changeStores.setVisibility(View.VISIBLE);
                changeStores.setText("Set to cheaper store");

            }
        }



    }


}