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

import comp3350.GoCart.objects.Product;
import comp3350.GoCart.objects.Store;
import comp3350.GoCart.persistence.ProductPersistence;
public class ProductPersistenceHSQLDB implements ProductPersistence {

    private final String dbPath;
    private Product fromResultSet(final ResultSet rs) throws SQLException {
        final String productID=  String.valueOf(rs.getInt("PID"));
        final String name=rs.getString("NAME");
        final boolean hasAllergy =rs.getBoolean("HAS_ALLERGY");
        final String category =rs.getString("CATEGORY");

        return new Product(productID, name, hasAllergy, category);
    }
    public ProductPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
    @Override
    public List<Product> getDietaryRestrictedProducts() {
        List<Product> matchingProducts = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS WHERE HAS_ALLERGY = FALSE");
            while (rs.next())
            {
                final Product product = fromResultSet(rs);
                matchingProducts.add(product);
            }
            rs.close();
            st.close();

            return matchingProducts;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Product> productCategory(String category) {
        List<Product> matchingProducts = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM PRODUCTS WHERE category = ?");
            st.setString(1, category);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Product product = fromResultSet(rs);
                matchingProducts.add(product);
            }
            rs.close();
            st.close();

            return matchingProducts;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    public List<Product> searchProductsByName(String productName) {
            List<Product> matchingProducts = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM PRODUCTS WHERE NAME = ?");
            st.setString(1, productName);
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Product product = fromResultSet(rs);
                matchingProducts.add(product);
            }
            rs.close();
            st.close();

            return matchingProducts;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }
    @Override
    public List<Product> getAllProducts() {
        List<Product> matchingProducts = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM PRODUCTS ");
            final ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                final Product product = fromResultSet(rs);
                matchingProducts.add(product);
            }
            rs.close();
            st.close();

            return matchingProducts;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }


}
