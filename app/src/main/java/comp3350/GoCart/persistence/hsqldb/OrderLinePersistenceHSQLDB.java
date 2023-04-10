package comp3350.GoCart.persistence.hsqldb;

/*
This is the access Orders class and deals with all logic needed for the orders in our app
IT interacts with the DB through the orderPersistence interface
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.GoCart.objects.Order;
import comp3350.GoCart.objects.OrderLineItem;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.OrderLinePersistence;

public class OrderLinePersistenceHSQLDB implements OrderLinePersistence {
    private final String dbPath;

    public OrderLinePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private OrderLineItem fromResultSet(final ResultSet rs) throws SQLException {
        final String orderID = String.valueOf(rs.getInt("OID"));
        final String productID = String.valueOf(rs.getInt("PID"));
        final BigDecimal price = rs.getBigDecimal("PRICE");

        return new OrderLineItem(new Order.OrderBuilder().orderID(orderID).build(), new Product.ProductBuilder().productID(productID).build(), price);
    }
    @Override
    public OrderLineItem insertOrderLine(OrderLineItem toInsert) {
        try(Connection conn = connection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO ORDERLINEITEMS VALUES(?, ?, ?)");
            statement.setInt(1, Integer.parseInt(toInsert.getOrderID()));
            statement.setInt(2, Integer.parseInt(toInsert.getProductID()));
            statement.setBigDecimal(3, toInsert.getPrice());

            statement.executeUpdate();
            return toInsert;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public OrderLineItem getOrderLineItem(int orderID, int productID) {
        try(Connection conn = connection()) {
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM ORDERLINEITEMS WHERE OID = ? and PID = ?");
            statement.setInt(1, orderID);
            statement.setInt(2, productID);

            final ResultSet set = statement.executeQuery();

            if(set.next()) {
                return fromResultSet(set);
            }

            return new OrderLineItem(new Order.OrderBuilder().orderID("-1").build(), new Product.ProductBuilder().productID("-1").build(), new BigDecimal(-1));
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public List<OrderLineItem> getAllOrderLineItems(int orderID) {
        List<OrderLineItem> allOrders = new ArrayList<>();

        try(final Connection conn = connection()) {
            final PreparedStatement statement = conn.prepareStatement("SELECT * FROM ORDERLINEITEMS WHERE OID = ?");
            statement.setInt(1, orderID);

            final ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                final OrderLineItem order = fromResultSet(rs);
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
