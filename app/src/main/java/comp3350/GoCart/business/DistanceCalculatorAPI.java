package comp3350.GoCart.business;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import comp3350.GoCart.objects.Store;

public class DistanceCalculatorAPI implements DistanceCalculator {

    private static String API_URI = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
    private static String API_KEY = "&key=AIzaSyDGfUnYamfvdOBh4gFiEAjVwv5jv80tgf4";

    /*
    * This method uses Google's map api in order to get the distances of the stores given a location.
    * It calls into the api and gets the distance parameter from the JSON response
    * It returns the sorted list or throws an Exception
     */
    public List<Store> calculateNearestStores(String location, List<Store> allStores) throws JSONException, IOException {
        if(location == null) {
            return allStores;
        }

        List<Store> nearest = new ArrayList<>();
        String renderedLoc = location.trim().replace(" ", "%20");

        for (Store store : allStores) {
            String address = store.getStoreAddress().trim().replace(" ", "%20");
            String uri = API_URI + renderedLoc + "&destinations=" + address + API_KEY;

            String res = sendRequest(uri);

            //get the distance
            JSONObject json = new JSONObject(res);
            json.getJSONArray("rows").getJSONObject(0).get("elements").toString();
            double dist = Double.parseDouble(json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text").split(" ")[0]);
            store.setDistToUser(dist);
            if ( dist < 20) {
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

    //this method is used to actually send our apu request. It creates the connection and gets the response in a string format.
    private static String sendRequest(String uri) throws IOException {
        //send the request
        URL url = new URL(uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("accept", "application/json");
        InputStream resStream = conn.getInputStream();
        Scanner s = new Scanner(resStream).useDelimiter("\\A");
        String res = s.hasNext() ? s.next() : "";

        return res;
    }
}
