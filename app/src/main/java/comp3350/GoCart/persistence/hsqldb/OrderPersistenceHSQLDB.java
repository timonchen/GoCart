package comp3350.GoCart.persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.persistence.OrderPersistence;

public class OrderPersistenceHSQLDB implements OrderPersistence {

    private final String dbPath;

    public OrderPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Order fromResultSet(final ResultSet rs) throws SQLException {
        final int orderID = Integer.parseInt(rs.getString("OID"));
        final int customerID = Integer.parseInt(rs.getString("CID"));
        final int storeID = Integer.parseInt(rs.getString("SID"));

        return new Order(orderID, customerID, storeID);
    }

    @Override
    public Order insertOrder(Order toInsert) {
        try(Connection conn = connection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO ORDERS VALUES(?, ?, ?)");
            statement.setInt(1, toInsert.getOrderID());
            statement.setInt(2, toInsert.getCustomerID());
            statement.setInt(3, toInsert.getStoreID());
            return toInsert;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }


    }

    @Override
    public Order getOrder(int orderID) {
        try(Connection conn = connection()) {
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM ORDERS WHERE OID = ?");
            statement.setInt(1, orderID);
            final ResultSet set = statement.executeQuery();

            if(set.next()) {
                return fromResultSet(set);
            }

            return new Order(-1, -1, -1);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

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
