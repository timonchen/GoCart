package comp3350.GoCart.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.GoCart.objects.Store;

// Looks through a list for elements with a given name
public class GetByName {

    public static List<Store> stores(String storeName, List<Store> storeList) {
        storeName = storeName.toLowerCase();
        List<Store> results;

        if (storeName.equals("")) {
            results = storeList;
        }
        else {
            results = matchStores(storeName, storeList);
        }

        return results;
    }

    private static List<Store> matchStores(String storeWanted, List<Store> stores) {
        List<Store> results = new ArrayList<Store>();
        storeWanted = storeWanted.toLowerCase();

        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            String storeName = store.getStoreName().toLowerCase();
            boolean match = true;

            // Check if all words in the storeName matches with user input
            for (int j = 0; j < storeWanted.length() && match; j++) {
                if (storeWanted.charAt(j) != storeName.charAt(j)) {
                    match = false;
                }
            }

            if (match) {
                results.add(store);
            }
        }

        return results;
    }
}

// API get near store returns a list of assorted list if valid
// If returns empty list, display.