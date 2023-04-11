package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Events.EditEmployees.onEditEmployeeFinancialButtonClick;
import lat.jack.employee.employee.Events.EditEmployees.onUpdateEmployeeButtonClick;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditEmployeeView {

    // Employee Details

    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputLastName;
    @FXML
    TextField inputEmailAddress;
    @FXML
    TextField inputPhoneNumber;
    @FXML
    DatePicker inputHireDate;
    @FXML
    ComboBox<String> inputEmployeeRole;

    // Employee Address Details
    @FXML
    TextField inputStreetName;
    @FXML
    TextField inputStreetRegion;
    @FXML
    TextField inputStreetCity;
    @FXML
    TextField inputStreetPostCode;
    @FXML
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

    public List<String> getCountries() {
        return Arrays.asList("United Kingdom", "United States", "Australia", "Canada", "New Zealand", "France", "Germany", "Italy", "Spain");
    }

    public void setRoles(List<String> roles) {
        inputEmployeeRole.getItems().addAll(roles);
    }

    public void setCountries(List<String> countries) {
        inputStreetCountry.getItems().addAll(countries);
    }
}
