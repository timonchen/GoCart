package comp3350.GoCart.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import comp3350.GoCart.objects.Store;

public class DistanceCalculatorRandom implements DistanceCalculator{

    private static final double MIN = 0.1;
    private static final double MAX = 15.0;


    /*
    * Simply gives each store a random value for the stub implementation
    * Returns the sorted list according to distances
    */
    public List<Store> calculateNearestStores(String location, List<Store> allStores) throws Exception {
        if(location == null) {
            return allStores;
        }

        List<Store> nearest = new ArrayList<>();
        int count = 0;
        for(Store store : allStores) {
            Random rand = new Random();
            double randomValue = MIN + (MAX - MIN) * rand.nextDouble();
            store.setDistToUser(randomValue);
            if ( randomValue < 15) {

                nearest.add(store);
            }
        }


        Collections.sort(nearest, new Comparator<Store>() {
            @Override
            public int compare(Store s1, Store s2) {
                if(s1.compareTo(s2) < 0.0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return nearest;
    }
}
