package lat.jack.employee.employee.Controllers;

import com.j256.ormlite.dao.Dao;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Events.General.*;
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
    TableColumn<Employees, String> employeeRoleCategoryColumn;
    @FXML
    TableColumn<Employees, String> employeeRoleColumn;
    @FXML
    TableColumn<Employees, Double> employeeSalaryColumn;

    @FXML
    Label labelEmployeeFirstNameValue;
    @FXML
    Label labelEmployeeLastNameValue;
    @FXML
    Label labelEmployeeEmailAddressValue;
    @FXML
    Label labelEmployeePhoneNumberValue;
    @FXML
    Label labelEmployeeRoleNameValue;
    @FXML
    Label labelEmployeeRoleCategoryValue;

    // Employee Benefits
    @FXML
    Label labelEmployeeHouseAllowanceValue;
    @FXML
    Label labelEmployeeTravellingAllowanceValue;
    @FXML
    Label labelEmployeeHealthAllowanceValue;

    // Search

    @FXML
    public
    ComboBox comboBoxSearchBy;
    @FXML
    public
    ComboBox comboBoxSearchAlgorithm;
    @FXML
    public
    TextField inputSearchValue;
    @FXML
    Button buttonSearch;

    // Group
    @FXML
    public
    Group groupSelectedEmployee;
    @FXML
    Button buttonEditSelectedEmployee;
    @FXML
    Button buttonDeleteSelectedEmployee;
    @FXML
    public
    Label outputSelectedIDLabel;
    @FXML
    public
    Label outputSelectedNameLabel;
    @FXML
    public
    Label outputSelectedCategoryLabel;
    @FXML
    public
    Label outputSelectedRoleLabel;

    public Employees getSelectedEmployee() {
        ApplicationManager.setSelectedEmployee(employeeTable.getSelectionModel().getSelectedItem());
        return ApplicationManager.getSelectedEmployee();
    }

    @FXML
    protected void initialize() {
        System.out.println("GeneralView initialized!");
        labelWelcomeName.setText("Welcome, " + ApplicationManager.getCurrentUser().getUserName() + "!");
        groupSelectedEmployee.setVisible(false);

        tabPaneGeneral.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab == tabViewEmployee) {

                if (this.getSelectedEmployee() == null) {
                    tabPaneGeneral.getSelectionModel().select(oldTab);
                    alert();
                    return;
                }

                onTabViewEmployeeSelected();
            }
        });

        comboBoxSearchBy.getItems().addAll("ID");
        comboBoxSearchBy.getSelectionModel().selectFirst();

        comboBoxSearchAlgorithm.getItems().setAll("Binary Search", "Linear Search");
        comboBoxSearchAlgorithm.getSelectionModel().selectFirst();

        buttonAddCategory.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddCategoryButtonClick(this));
        buttonAddRole.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddRoleButtonClick(this));
        buttonAddEmployee.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddEmployeeButtonClick(this));
        buttonSearch.addEventFilter(MouseEvent.MOUSE_PRESSED, new onSearchButtonClick(this));

        comboBoxSearchBy.getSelectionModel().selectedItemProperty().addListener(new onSearchByChanged(this));

        setupEmployeeTable();
        updateEmployeeTable(getAllEmployees());

        employeeTable.getSelectionModel().selectedItemProperty().addListener(new onEmployeeSelectionChanged(this));
        buttonEditSelectedEmployee.addEventFilter(MouseEvent.MOUSE_PRESSED, new onEditSelectedEmployeeButtonClick(this));
        buttonDeleteSelectedEmployee.addEventFilter(MouseEvent.MOUSE_PRESSED, new onDeleteSelectedEmployeeButtonClick(this));

    }

    public List<Employees> getAllEmployees() {
        Dao<Employees, Integer> employeeDao = Database.getEmployeeDao();

        List<Employees> employees;

        try {
            employees = employeeDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public void setupEmployeeTable() {

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

        employeeRoleCategoryColumn.setCellValueFactory(cellData -> {
            String employeeRoleCategoryValue = cellData.getValue().getEmployeeRole().getRoleCategory().getCategoryName();
            return new SimpleStringProperty(employeeRoleCategoryValue);
        });

        employeeRoleColumn.setCellValueFactory(cellData -> {
            String employeeRoleValue = cellData.getValue().getEmployeeRole().getRoleName();
            return new SimpleStringProperty(employeeRoleValue);
        });

        employeeSalaryColumn.setCellValueFactory(cellData -> {
            double employeeSalaryValue = cellData.getValue().getEmployeeBenefits().getEmployeeSalary();
            return new SimpleDoubleProperty(employeeSalaryValue).asObject();
        });

    }

    public void updateEmployeeTable(List<Employees> employees) {
        employeeTable.getItems().clear();
        employeeTable.setItems(FXCollections.observableArrayList(employees));
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Please select an employee first!");

        alert.showAndWait();
    }

    protected void onTabViewEmployeeSelected() {
        System.out.println("View Employee tab selected!");

        Employees selectedEmployee = getSelectedEmployee();

        if (selectedEmployee == null) {
            System.out.println("No employee selected!");
            return;
        }

        labelEmployeeFirstNameValue.setText(selectedEmployee.getFirstName());
        labelEmployeeLastNameValue.setText(selectedEmployee.getLastName());
        labelEmployeeEmailAddressValue.setText(selectedEmployee.getEmailAddress());
        labelEmployeePhoneNumberValue.setText(selectedEmployee.getPhoneNumber());
        labelEmployeeRoleNameValue.setText(selectedEmployee.getEmployeeRole().getRoleName());
        labelEmployeeRoleCategoryValue.setText(selectedEmployee.getEmployeeRole().getRoleCategory().getCategoryName());

        labelEmployeeHouseAllowanceValue.setText(String.valueOf(selectedEmployee.getEmployeeRole().getRoleBenefits().getHousingAllowance()));
        labelEmployeeTravellingAllowanceValue.setText(String.valueOf(selectedEmployee.getEmployeeRole().getRoleBenefits().getTravelingAllowance()));
        labelEmployeeHealthAllowanceValue.setText(String.valueOf(selectedEmployee.getEmployeeRole().getRoleBenefits().getHealthAllowance()));



    }
}
