package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Events.EditEmployees.onEditEmployeeFinancialButtonClick;
import lat.jack.employee.employee.Events.EditEmployees.onUpdateEmployeeButtonClick;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditEmployeeView {

    // Employee Details

    @FXML
    public
    TextField inputFirstName;
    @FXML
    public
    TextField inputLastName;
    @FXML
    public
    TextField inputEmailAddress;
    @FXML
    public
    TextField inputPhoneNumber;
    @FXML
    public
    DatePicker inputHireDate;
    @FXML
    public
    ComboBox<String> inputEmployeeRole;

    // Employee Address Details
    @FXML
    public
    TextField inputStreetName;
    @FXML
    public
    TextField inputStreetRegion;
    @FXML
    public
    TextField inputStreetCity;
    @FXML
    public
    TextField inputStreetPostCode;
    @FXML
    public
    ComboBox<String> inputStreetCountry;

    // Buttons

    @FXML
    Button buttonEditFinancial;
    @FXML
    Button buttonSubmitChanges;

    @FXML
    protected void initialize() {
        System.out.println("EditEmployeeView initialized!");

        setRoles(getRoles());
        setCountries(getCountries());

        populateFields();

        // Register events
        buttonSubmitChanges.addEventFilter(MouseEvent.MOUSE_PRESSED, new onUpdateEmployeeButtonClick(this));
        buttonEditFinancial.addEventFilter(MouseEvent.MOUSE_PRESSED, new onEditEmployeeFinancialButtonClick(this));
    }

    public List<String> getRoles() {

        List<String> employeeRoleNames = new ArrayList<>();

        try {
            Database.getEmployeeRoleDao().queryForAll().forEach((role) -> {
                employeeRoleNames.add(role.getRoleName());

            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeRoleNames;

    }

    public void populateFields() {
        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();

        inputFirstName.setText(selectedEmployee.getFirstName());
        inputLastName.setText(selectedEmployee.getLastName());
        inputEmailAddress.setText(selectedEmployee.getEmailAddress());
        inputPhoneNumber.setText(selectedEmployee.getPhoneNumber());
        inputHireDate.setValue(selectedEmployee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        inputEmployeeRole.setValue(selectedEmployee.getEmployeeRole().getRoleName());

        inputStreetName.setText(selectedEmployee.getAddress().getStreetName());
        inputStreetRegion.setText(selectedEmployee.getAddress().getAddressRegion());
        inputStreetCity.setText(selectedEmployee.getAddress().getAddressCity());
        inputStreetPostCode.setText(selectedEmployee.getAddress().getPostalCode());
        inputStreetCountry.setValue(selectedEmployee.getAddress().getCountry());
    }

    public List<String> getCountries() {
        return Arrays.asList("United Kingdom", "United States", "Australia", "Canada", "New Zealand", "France", "Germany", "Italy", "Spain");
    }

    public void setRoles(List<String> roles) {
        inputEmployeeRole.getItems().addAll(roles);
        inputEmployeeRole.setValue(inputEmployeeRole.getItems().get(0));
    }

    public void setCountries(List<String> countries) {
        inputStreetCountry.getItems().addAll(countries);
        inputStreetCountry.setValue(inputStreetCountry.getItems().get(0));
    }
}
