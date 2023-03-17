package lat.jack.employee.employee.Models;

public class Employee {

    protected int ID;
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String phoneNumber;
    protected String hireDate;
    protected Address address;
    protected int roleID;

    public Employee(int ID, String firstName, String lastName, String emailAddress, String phoneNumber, String hireDate, Address address, int roleID) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.address = address;
        this.roleID = roleID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddressID(Address address) {
        this.address = address;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
