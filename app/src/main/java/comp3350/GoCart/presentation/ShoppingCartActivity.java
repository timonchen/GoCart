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
import comp3350.GoCart.objects.EmptyStore;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.StoreProduct;

public class ShoppingCartActivity extends Activity {



    private RecyclerView productRecView;
    private AccessStoreProduct accessStoreProduct ;

    private TextView price;
    private Store cheapestStore;

    private TextView notif,lp;
    private Button change;
    private Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        accessStoreProduct = new AccessStoreProduct();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ShoppingCart cart = ShoppingCart.getInstance();
        BigDecimal selectedStorePrice;
        BigDecimal lowerStorePrice;

        productRecView = findViewById(R.id.cartRecView);
        notif = findViewById(R.id.txtCheaperNotification);
        lp = findViewById(R.id.txtCheaperPrice);
        change = findViewById(R.id.btnChangeToCheaper);
        check = findViewById(R.id.btnCheckCheaper);
        price = findViewById(R.id.txtEditTotalPrice);

        productRecView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> p = ShoppingCart.getInstance().getCartProducts();
        List<Integer> q = ShoppingCart.getInstance().getCartQuantity();

        selectedStorePrice =cart.calculateTotal(accessStoreProduct);
        price.setText(selectedStorePrice.toString());

        productRecView.setAdapter(new ShoppingCartAdapter(getApplicationContext(),p,q,price,notif,lp,change));
        //check(notif,lp,change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AccessStoreProduct accessStoreProduct = new AccessStoreProduct();
                AccessStores accessStores = new AccessStores();

                cheapestStore = accessStoreProduct.findCheapestStore(p,q,accessStores.getStores()).getStore();
                if (!(cheapestStore instanceof EmptyStore))
                    ShoppingCart.getInstance().setStore(cheapestStore);
                check(notif,lp,change,check,price);
                price.setText(cart.calculateTotal(accessStoreProduct).toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newStore",cheapestStore);
                setResult(Activity.RESULT_OK,returnIntent);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessStoreProduct accessStoreProduct = new AccessStoreProduct();
                AccessStores accessStores = new AccessStores();
                check(notif,lp,change,check,price);


            }
        });





    }



    public static void check(TextView newNotif,TextView newLp,Button change,Button check,TextView topPrice){
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

        BigDecimal cheapestValue = aSP.calculateTotal(p,q,cheapest.getStore());

        if(cheapestValue.equals(BigDecimal.ZERO) || cheapestValue.equals(cart.calculateTotal(aSP))) {
            System.out.println("case 1");
            notif.setText("No Lower Price Found");
            lowerPrice.setText("");
            changeStores.setVisibility(View.INVISIBLE);
            check.setVisibility(View.VISIBLE);
            tp.setText(cheapestValue.toString());
        } else {
            if(!cheapest.equals( cart.getStore() )){
                System.out.println("case 2");
                notif.setText("New Price:");
                lowerPrice.setText(cheapestValue.toString());
                changeStores.setVisibility(View.VISIBLE);
                check.setVisibility(View.INVISIBLE);

            }
        }



    }


}