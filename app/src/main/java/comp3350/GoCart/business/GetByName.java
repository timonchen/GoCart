package comp3350.GoCart.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Store;

// Looks through a list for elements with a given name
public class GetByName {

    public static List<Store> stores(String storeName, List<Store> storeList) {
        List<Store> storesWanted = new ArrayList<>();

        for (int i = 0; i < storeList.size(); i++) {
            if (storeList.get(i).getStoreName().equals(storeName)) {
                storesWanted.add(storeList.get(i));
            }
        }

        return storesWanted;
    }
}
