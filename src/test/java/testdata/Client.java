package testdata;

public class Client {
    private final String firstName;
    private final String familyName;
    private final String address;
    private final String telNumber;

    public Client(String firstName, String familyName, String address, String telNumber) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.address = address;
        this.telNumber = telNumber;
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

    public String getTelNumber() {
        return telNumber;
    }
}
