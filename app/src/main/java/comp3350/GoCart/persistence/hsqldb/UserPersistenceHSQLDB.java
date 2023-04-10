package comp3350.GoCart.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import comp3350.GoCart.R;
import comp3350.GoCart.objects.User;
import comp3350.GoCart.persistence.UserPersistence;

public class UserPersistenceHSQLDB implements UserPersistence
{
    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    public void addUser(User newUser)
    {
        try (final Connection connection = connection()) {

            String query = "INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?,?,?,?,?)";
            final PreparedStatement statement = connection.prepareStatement(query);

            newUser.setUserID(String.valueOf(getNumUsers() + 1));

            statement.setString(1, String.valueOf(getNumUsers() + 1));
            statement.setString(2, newUser.getFirstName());
            statement.setString(3, newUser.getLastName());
            statement.setString(4, newUser.getAddress());
            statement.setString(5, newUser.getCity());
            statement.setString(6, newUser.getProvince());
            statement.setString(7, newUser.getZipCode());
            statement.setString(8, String.valueOf(newUser.getPhone()));
            statement.setString(9, newUser.getEmail());
            statement.setString(10, newUser.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public User getUser(String email, String password)
    {
        User user = null;
        try (final Connection connection = connection()) {
            String query = "SELECT * FROM CUSTOMERS WHERE EMAIL = ? AND PASSWORD = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, email);
            statement.setString(2, password);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                user = fromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
        return user;
    }

    public int getNumUsers()
    {
        int count = 0;
        try (final Connection connection = connection()) {
            String query = "select * from customers";
            final PreparedStatement statement = connection.prepareStatement(query);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                count++;
            }
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
        return count;
    }


    private User fromResultSet(final ResultSet resultSet) throws SQLException {
        final int cID = resultSet.getInt("CID");
        final String firstName = resultSet.getString("FIRSTNAME");
        final String lastName = resultSet.getString("LASTNAME");
        final String address = resultSet.getString("ADDRESS");
        final String city = resultSet.getString("CITY");
        final String province = resultSet.getString("PROVINCE");
        final String zipCode = resultSet.getString("ZIPCODE");
        final int phone = resultSet.getInt("PHONE");
        final String email = resultSet.getString("EMAIL");
        final String password = resultSet.getString("PASSWORD");

        return new User(String.valueOf(cID), firstName, lastName, address, city, province, zipCode, phone, email, password);
    }

    public void updateUser(User user)
    {
        try (final Connection connection = connection()) {
            String cID = user.getUserID();
            String query = "update customers set FIRSTNAME = ?, LASTNAME = ?, ADDRESS = ?, CITY = ?, PROVINCE = ?, ZIPCODE = ?, PHONE = ?, EMAIL = ?, PASSWORD = ? where CID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getCity());
            statement.setString(5, user.getProvince());
            statement.setString(6, user.getZipCode());
            statement.setString(7, String.valueOf(user.getPhone()));
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getPassword());
            statement.setInt(10, Integer.parseInt(cID));
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public void deleteUser(User user)
    {
        try (final Connection connection = connection()){
            String query = "delete from customers where CID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(user.getUserID()));
            statement.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

}
