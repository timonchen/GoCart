package comp3350.GoCart.persistence.hsqldb;

/*
This is the OrderPersistenceHSQLDB class which deals with all querying for our DB with regards to Orders
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.OrderPersistence;

public class OrderPersistenceHSQLDB implements OrderPersistence {

    private final String dbPath;

    public OrderPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    //opens a connection to the DB with the account
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    //This takes a result set that is generated from a query
    //It generates an approriate order from the restult set and returns this
    private Order fromResultSet(final ResultSet rs) throws SQLException {
        final String orderID = rs.getString("OID");
        final int customerID = Integer.parseInt(rs.getString("CID"));
        final String storeID = rs.getString("SID");

        //use the builders here as we don't need all the other info
        User.UserBuilder userBuilder = new User.UserBuilder();
        Store.StoreBuilder storeBuilder = new Store.StoreBuilder();

        return new Order(orderID, userBuilder.userID(String.valueOf(customerID)).build(), storeBuilder.storeID(storeID).build());

    }

    //this inserts an order into the db
    //It does so by executing an update
    @Override
    public Order insertOrder(Order toInsert) {
        try(Connection conn = connection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO ORDERS VALUES(?, ?, ?)");
            statement.setInt(1, Integer.parseInt(toInsert.getOrderID()));
            statement.setInt(2, Integer.parseInt(toInsert.getCustomerID()));
            statement.setInt(3, Integer.parseInt(toInsert.getStoreID()));
            statement.executeUpdate();
            return toInsert;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }


    }

    //this simply gets order from the given order ID as a paramters
    //returns this order if it was found
    @Override
    public Order getOrder(int orderID) {
        try(Connection conn = connection()) {
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM ORDERS WHERE OID = ?");
            statement.setInt(1, orderID);
            final ResultSet set = statement.executeQuery();

            if(set.next()) {
                return fromResultSet(set);
            }

            return new Order("-1", new User.UserBuilder().userID("-1").build(), new Store.StoreBuilder().storeID("-1").build());
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    //this simply gets all the orders from the order table where the cID matches the paramter given
    //returns a list of all the ordres after
    @Override
    public List<Order> getAllOrders(int customerID) {
        List<Order> allOrders = new ArrayList<>();

        try(final Connection conn = connection()) {
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM ORDERS WHERE CID = ?");
            statement.setInt(1, customerID);

            final ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                final Order order = fromResultSet(rs);
                allOrders.add(order);
            }

            rs.close();
            statement.close();

            return allOrders;

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
