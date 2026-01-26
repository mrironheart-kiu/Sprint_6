package testdata;

public class Client {
    private final String firstName;
    private final String familyName;
    private final String address;
    private final String phoneNumber;

    public Client(String firstName, String familyName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
