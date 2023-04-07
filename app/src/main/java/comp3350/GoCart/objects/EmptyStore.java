package comp3350.GoCart.objects;


/*
Special Case class, default for accessStoreProducts findCheapestStore method when no
store has cheaper price
 */
public class EmptyStore extends Store {
    public EmptyStore() {
        super("Emptystore");
    }
}
