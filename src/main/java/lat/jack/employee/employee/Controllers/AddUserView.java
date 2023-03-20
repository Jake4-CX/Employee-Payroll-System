package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;
import java.util.ArrayList;
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
    TextField inputStreetCountry;

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

        System.out.println("Finished");

        // Employee Roles
        inputEmployeeRole.getItems().clear();
        inputEmployeeRole.getItems().addAll(employeeRoleNames);
    }
}
