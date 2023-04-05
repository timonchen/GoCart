package comp3350.GoCart.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.GoCart.objects.StoreProduct;
import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.StoreProductPersistence;


public class StoreProductPersistenceHSQLDB implements StoreProductPersistence {
    private final String dbPath;

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private StoreProduct fromResultSet(final ResultSet rs) throws SQLException {
        final int productID = rs.getInt("PID");
        final int storeID = rs.getInt("SID");
        final BigDecimal price = rs.getBigDecimal("PRICE");
        final String prodName = rs.getString("PRODUCT_NAME");

        return new StoreProduct( new Store(String.valueOf(storeID)), new Product(String.valueOf(productID), prodName),price);
    }

    public StoreProductPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    @Override
    public List<StoreProduct> getStoreProducts(String storeID) {
        final List<StoreProduct> stores = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STORES_PRODUCTS WHERE SID = ?");
            st.setInt(1, Integer.parseInt(storeID));
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final StoreProduct sp = fromResultSet(rs);
                stores.add(sp);
            }
            rs.close();
            st.close();

            return stores;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<StoreProduct> getAllStoreProducts() {
        final List<StoreProduct> storeProducts = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM STORES_PRODUCTS");
            while (rs.next()) {
                final StoreProduct sp = fromResultSet(rs);
                storeProducts.add(sp);
            }
            rs.close();
            st.close();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }

        return storeProducts;
    }
}
