package lat.jack.employee.employee.Events.AddEmployee;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.AddEmployeeView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Addresses;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.EmployeesBenefits;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class onAddEmployeeButtonClick implements EventHandler<MouseEvent> {

    private AddEmployeeView addEmployeeView;

    public onAddEmployeeButtonClick(AddEmployeeView addEmployeeView) {
        this.addEmployeeView = addEmployeeView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Add user button clicked!");

        String firstName = addEmployeeView.getInputFirstName();
        String lastName = addEmployeeView.getInputLastName();
        String email = addEmployeeView.getInputEmailAddress();
        String phoneNumber = addEmployeeView.getInputPhoneNumber();
        LocalDate hireDate = addEmployeeView.getInputHireDate();
        String employeeRole = addEmployeeView.getInputEmployeeRole();

        // Address
        String streetName = addEmployeeView.getInputStreetName();
        String streetRegion = addEmployeeView.getInputStreetRegion();
        String streetCity = addEmployeeView.getInputStreetCity();
        String streetPostCode = addEmployeeView.getInputStreetPostCode();
        String streetCountry = addEmployeeView.getInputStreetCountry();

        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Hire date: " + hireDate);
        System.out.println("Employee role: " + employeeRole);
        System.out.println("Street name: " + streetName);
        System.out.println("Street region: " + streetRegion);
        System.out.println("Street city: " + streetCity);
        System.out.println("Street post code: " + streetPostCode);
        System.out.println("Street country: " + streetCountry);

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || hireDate == null || employeeRole.isEmpty() || streetName.isEmpty() || streetRegion.isEmpty() || streetCity.isEmpty() || streetPostCode.isEmpty() || streetCountry.isEmpty()) {
            System.out.println("One or more fields are empty!");
            inputEmpty();
            return;
        }

        // Get EmployeeRole
        Dao<EmployeeRoles, Integer> employeeRolesDao = Database.getEmployeeRoleDao();
        EmployeeRoles employeeRoles = null;

        try {
            employeeRoles = employeeRolesDao.queryForFirst(employeeRolesDao.queryBuilder().where().eq("roleName", employeeRole).prepare());

            if (employeeRoles == null) {
                System.out.println("Employee role not found!");
                roleNotFound();
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (employeeRoles == null) {
            System.out.println("Employee role not found!");
            roleNotFound();
            return;
        }

        // Got employee role

        Addresses address = new Addresses();
        address.setStreetName(streetName);
        address.setAddressRegion(streetRegion);
        address.setAddressCity(streetCity);
        address.setPostalCode(streetPostCode);
        address.setCountry(streetCountry);

        // Save address

        Dao<Addresses, Integer> addressesDao = Database.getAddressDao();

        try {
            int response = addressesDao.create(address);

            if (response == 0) {
                System.out.println("Address not saved!");
                addressNotSaved();
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Making Employee's personal EmployeesBenefits

        EmployeesBenefits employeesBenefits = new EmployeesBenefits();
        employeesBenefits.setEmployeeBonus(0.00);
        employeesBenefits.setEmployeeLoan(0.00);
        employeesBenefits.setEmployeeSalary(employeeRoles.getStartingSalary());
        employeesBenefits.setHousingAllowance(employeeRoles.getRoleBenefits().getHousingAllowance());
        employeesBenefits.setTravelingAllowance(employeeRoles.getRoleBenefits().getTravelingAllowance());
        employeesBenefits.setHealthAllowance(employeeRoles.getRoleBenefits().getHealthAllowance());

        // Save employeesRole

        Dao<EmployeesBenefits, Integer> employeesBenefitsDao = Database.getEmployeeBenefitsDao();

        try {
            int response = employeesBenefitsDao.create(employeesBenefits);

            if (response != 1) {
                System.out.println("Failed to add employees benefits to database");
                employeeNotSaved();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Employees employee = new Employees();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmailAddress(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setHireDate(Date.from(hireDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        employee.setEmployeeRole(employeeRoles);
        employee.setAddress(address);
        employee.setEmployeeBenefits(employeesBenefits);

        // Save employee

        Dao<Employees, Integer> employeesDao = Database.getEmployeeDao();

        try {
            int response = employeesDao.create(employee);

            if (response == 0) {
                System.out.println("Employee not saved!");
                employeeNotSaved();
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Employee saved!");
        employeeSaved();
    }

    private void employeeSaved() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Employee saved!");
        alert.showAndWait();
    }

    private void employeeNotSaved() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Employee not saved!");
        alert.showAndWait();
    }

    private void addressNotSaved() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Address not saved!");
        alert.showAndWait();
    }

    private void roleNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Employee role not found!");
        alert.showAndWait();
    }

    private void inputEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("One or more fields are empty!");
        alert.showAndWait();
    }
}
