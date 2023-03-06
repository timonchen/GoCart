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

    private Product fromResultSet(final ResultSet rs) throws SQLException {
        final boolean has_allergy;
        final String productID = rs.getString("PID");
        final String name = rs.getString("NAME");
        final String allergy = rs.getString("ALLERGY");
        if (Integer.parseInt(allergy) == 1) {
            has_allergy = true;
        } else
            has_allergy = false;

        return new Product(productID, name, has_allergy);
    }

    public StoreProductPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    @Override
    public List<StoreProduct> getStoreProducts(String storeID) {
    return null;
    }
}
