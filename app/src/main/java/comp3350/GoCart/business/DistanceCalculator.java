package comp3350.GoCart.business;

import java.util.List;

import comp3350.GoCart.objects.Store;

public interface DistanceCalculator {

    List<Store> calculateNearestStores(String location,List<Store> allStores) throws Exception;

}
