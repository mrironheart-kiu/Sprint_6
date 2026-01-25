package testdata;

public class Client {
    private String firstName;
    private String familyName;
    private String address;
    private String telNumber = "+79998887766";

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
