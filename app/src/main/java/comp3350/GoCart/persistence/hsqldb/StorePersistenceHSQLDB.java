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
import java.sql.SQLException;


import comp3350.GoCart.persistence.StorePersistence;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.objects.Product;
import comp3350.GoCart.persistence.StoreProductPersistence;

public class StorePersistenceHSQLDB implements StorePersistence{
   private final String dbPath;
    public  StorePersistenceHSQLDB(final String dbPath) {
        this.dbPath=dbPath;

    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
    private Store fromResultSet(final ResultSet rs) throws SQLException {
         final String storeID= String.valueOf(rs.getInt("SID"));
         final String storeName=rs.getString("NAME");
         final String storeAddress=rs.getString("ADDRESS");
        return new Store(storeID, storeName,storeAddress);
    }
    @Override
    public List<Store> getAllStores() {
        final List<Store> stores = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM STORES");
            while (rs.next()) {
                final Store store = fromResultSet(rs);
                stores.add(store);
            }
            rs.close();
            st.close();

            return stores;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

}
