package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Employees {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String firstName;

    @DatabaseField()
    private String lastName;

    @DatabaseField()
    private String emailAddress;

    @DatabaseField()
    private String phoneNumber;

    @DatabaseField()
    private Date hireDate;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Addresses address;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private EmployeeRoles employeeRole;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private EmployeesBenefits employeeBenefits;

    public Employees(String firstName, String lastName, String emailAddress, String phoneNumber, Date hireDate, Addresses address, EmployeeRoles employeeRole, EmployeesBenefits employeeBenefits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.address = address;
        this.employeeRole = employeeRole;
        this.employeeBenefits = employeeBenefits;
    }

    public Employees() {
    }

    public int getId() {
        return id;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public EmployeeRoles getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRoles employeeRole) {
        this.employeeRole = employeeRole;
    }

    public EmployeesBenefits getEmployeeBenefits() {
        return employeeBenefits;
    }

    public void setEmployeeBenefits(EmployeesBenefits employeeBenefits) {
        this.employeeBenefits = employeeBenefits;
    }
}
