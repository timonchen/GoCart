package comp3350.GoCart.objects;

public class User
{
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String province;
    private final String zipCode;
    private final int phone;
    private final String email;
    private final String password;

    public User(final String firstName, final String lastName, final String address, final String city, final String province, final String zipCode, final int phone, final String email, final String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public boolean verifyUser(String email, String password)
    {
        boolean userVerified = true;

        if (!this.email.equals(email))
        {
            userVerified = false;
        }

        if (!this.password.equals(password))
        {
            userVerified = false;
        }

        return userVerified;
    }

    public String getInitials()
    {
        return Character.toUpperCase(firstName.charAt(0)) + "" + Character.toUpperCase(lastName.charAt(0));
    }

    public String toString()
    {
        return "First Name: " + firstName + " Last Name: " + lastName + " Address: " + address + " City: " + city + " Province: " + province + " ZIP Code: " + zipCode +
                " Phone: " + phone + " Email: " + email + " Password: " + password;
    }
}
