package comp3350.GoCart.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{

    private int userID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private int phone;
    private String email;
    private String password;


    private User() {}
    public User(int userID, final String firstName, final String lastName, final String address, final String city, final String province, final String zipCode, final int phone, final String email, final String password) {
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
        userID = in.readInt();
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
        dest.writeInt(userID);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(province);
        dest.writeString(zipCode);
        dest.writeInt(phone);
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
        return Character.toUpperCase(this.firstName.charAt(0)) + "" + Character.toUpperCase(this.lastName.charAt(0));
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

    public void setUserID(int userID)
    {
        this.userID = userID;
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
        return "Id: " + userID + "First Name: " + firstName + " Last Name: " + lastName + " Address: " + address + " City: " + city + " Province: " + province + " ZIP Code: " + zipCode +
                " Phone: " + phone + " Email: " + email + " Password: " + password;
    }

    public static class UserBuilder {
        private User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder userID(int userID) {
            this.user.userID = userID;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.user.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.user.lastName = lastName;
            return this;
        }

        public UserBuilder address(String address) {
            this.user.address = address;
            return this;
        }

        public UserBuilder city(String city) {
            this.user.city = city;
            return this;
        }

        public UserBuilder province(String province) {
            this.user.province = province;
            return this;
        }

        public UserBuilder zipCode(String zipCode) {
            this.user.zipCode = zipCode;
            return this;
        }
        public UserBuilder phone(int phone) {
            this.user.phone = phone;
            return this;
        }

        public UserBuilder email(String email) {
            this.user.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.user.password = password;
            return this;
        }

        public User build(){
            return this.user;
        }
    }
}
