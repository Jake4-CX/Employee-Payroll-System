package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Events.User.onAddUserButtonClick;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddUserView {

    // Employee
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
    ComboBox inputEmployeeRole;

    // Address
    @FXML
    TextField inputStreetName;
    @FXML
    TextField inputStreetRegion;
    @FXML
    TextField inputStreetCity;
    @FXML
    TextField inputStreetPostCode;
    @FXML
    ComboBox inputStreetCountry;

    // Button
    @FXML
    Button buttonAddUser;

    @FXML
    protected void initialize() {
        System.out.println("AddUserView initialized!");
        setData();

    }

    public void setData() {

        List<String> employeeRoleNames = new ArrayList<>();

        try {
            Database.getEmployeeRoleDao().queryForAll().forEach((role) -> {
                System.out.println(role.getRoleName());
                employeeRoleNames.add(role.getRoleName());

            });
            System.out.println("Got Roles");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inputStreetCountry.getItems().addAll(Arrays.asList("United Kingdom", "United States", "Australia", "Canada", "New Zealand", "France", "Germany", "Italy", "Spain"));
        inputStreetCountry.setValue(inputStreetCountry.getItems().get(0));

        inputHireDate.setValue(LocalDate.now());

        System.out.println("Finished");

        // Employee Roles
        inputEmployeeRole.getItems().clear();
        inputEmployeeRole.getItems().addAll(employeeRoleNames);

        if (inputEmployeeRole.getItems().size() > 0) {
            inputEmployeeRole.setValue(inputEmployeeRole.getItems().get(0));
        }

        // Events
        buttonAddUser.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddUserButtonClick(this));
    }

    public String getInputFirstName() {
        return inputFirstName.getText();
    }

    public String getInputLastName() {
        return inputLastName.getText();
    }

    public String getInputEmailAddress() {
        return inputEmailAddress.getText();
    }

    public String getInputPhoneNumber() {
        return inputPhoneNumber.getText();
    }

    public LocalDate getInputHireDate() {
        return inputHireDate.getValue();
    }

    public String getInputEmployeeRole() {
        return inputEmployeeRole.getValue().toString();
    }

    public String getInputStreetName() {
        return inputStreetName.getText();
    }

    public String getInputStreetRegion() {
        return inputStreetRegion.getText();
    }

    public String getInputStreetCity() {
        return inputStreetCity.getText();
    }

    public String getInputStreetPostCode() {
        return inputStreetPostCode.getText();
    }

    public String getInputStreetCountry() {
        return inputStreetCountry.getValue().toString();
    }
}
