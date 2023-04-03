package lat.jack.employee.employee.Controllers;

import com.j256.ormlite.dao.Dao;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Events.General.onAddCategoryButtonClick;
import lat.jack.employee.employee.Events.General.onAddEmployeeButtonClick;
import lat.jack.employee.employee.Events.General.onAddRoleButtonClick;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GeneralView {

    @FXML
    Label labelWelcomeName;

    @FXML
    Tab tabViewEmployee;
    @FXML
    TabPane tabPaneGeneral;

    // Buttons
    @FXML
    Button buttonAddCategory;
    @FXML
    Button buttonAddRole;
    @FXML
    Button buttonAddEmployee;

    // Employee Table
    @FXML
    TableView<Employees> employeeTable;
    @FXML
    TableColumn<Employees, Integer> employeeIDColumn;
    @FXML
    TableColumn<Employees, String> employeeNameColumn;
    @FXML
    TableColumn<Employees, String> employeePhoneColumn;
    @FXML
    TableColumn<Employees, String> employeeEmailColumn;
    @FXML
    TableColumn<Employees, Date> employeeHireDateColumn;

    @FXML
    protected void initialize() {
        System.out.println("GeneralView initialized!");
        labelWelcomeName.setText("Welcome, " + ApplicationManager.getCurrentUser().getUserName() + "!");

        tabPaneGeneral.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab == tabViewEmployee) {
                onTabViewEmployeeSelected();
            }
        });

        buttonAddCategory.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddCategoryButtonClick(this));
        buttonAddRole.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddRoleButtonClick(this));
        buttonAddEmployee.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddEmployeeButtonClick(this));

        Dao<Employees, Integer> employeeDao = Database.getEmployeeDao();

        List<Employees> employees;

        try {
            employees = employeeDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Display employees data in table.

        // Define relative table values.

        employeeIDColumn.setCellValueFactory(cellData -> {
            int idValue = cellData.getValue().getId();
            IntegerProperty idProp = new SimpleIntegerProperty(idValue);
            return idProp.asObject();
        });
        employeeNameColumn.setCellValueFactory(cellData -> {
            String employeeNameValue = cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName();
            return new SimpleStringProperty(employeeNameValue);
        });
        employeePhoneColumn.setCellValueFactory(cellData -> {
            String employeePhoneValue = cellData.getValue().getPhoneNumber();
            return new SimpleStringProperty(employeePhoneValue);
        });

        employeeEmailColumn.setCellValueFactory(cellData -> {
            String employeeEmailValue = cellData.getValue().getEmailAddress();
            return new SimpleStringProperty(employeeEmailValue);
        });

        employeeHireDateColumn.setCellValueFactory(cellData -> {
            Date employeeHireDateValue = cellData.getValue().getHireDate();
            return new SimpleObjectProperty<>(employeeHireDateValue);
        });

        employeeTable.setItems(FXCollections.observableArrayList(employees));

    }

    protected void onTabViewEmployeeSelected() {
        System.out.println("View Employee tab selected!");


    }
}
