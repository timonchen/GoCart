package comp3350.GoCart.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{

    private int userID;
    private String firstName;
    private String lastName;
    private final String address;
    private final String city;
    private final String province;
    private final String zipCode;
    private long phone;
    private String email;
    private String password;

    public User(int userID, final String firstName, final String lastName, final String address, final String city, final String province, final String zipCode, final long phone, final String email, final String password)
    {
        this.userID = userID;
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

    protected User(Parcel in)
    {
        firstName = in.readString();
        lastName = in.readString();
        address = in.readString();
        city = in.readString();
        province = in.readString();
        zipCode = in.readString();
        phone = in.readInt();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(province);
        dest.writeString(zipCode);
        dest.writeLong(phone);
        dest.writeString(email);
        dest.writeString(password);
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

    public int getUserID()
    {
        return userID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }


    public String getName()
    {
        return firstName + " " + lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getProvince()
    {
        return province;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public long getPhone()
    {
        return phone;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void updateName(String name)
    {
        String[] firstAndLastName = name.split(" ");

        if (firstAndLastName.length > 0)
        {
            this.firstName = firstAndLastName[0];

            if (firstAndLastName.length > 1)
            {
                this.lastName = firstAndLastName[1];
            }
        }
    }

    public void updatePhone(int phone)
    {
        this.phone = phone;
    }

    public void updateEmail(String email)
    {
        this.email = email;
    }

    public void updatePassword(String password)
    {
        this.password = password;
    }

    public String toString()
    {
        return "First Name: " + firstName + " Last Name: " + lastName + " Address: " + address + " City: " + city + " Province: " + province + " ZIP Code: " + zipCode +
                " Phone: " + phone + " Email: " + email + " Password: " + password;
    }
}
