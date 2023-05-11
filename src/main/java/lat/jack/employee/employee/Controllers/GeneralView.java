package lat.jack.employee.employee.Controllers;

import com.j256.ormlite.dao.Dao;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.DataStructures.*;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.RoleCategories;
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
    Tab tabViewGeneratePayslip;
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

    // Order Buttons

    @FXML
    Button buttonSortAscend;
    @FXML
    Button buttonSortDescend;

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

    // Payslip

    @FXML
    Button buttonGeneratePayslip;
    @FXML
    public
    TextField inputDaysWorkedValue;
    @FXML
    public
    TextField inputOvertimeHoursValue;
    @FXML
    Label labelPayslipEmployeeIDValue;
    @FXML
    Label labelPayslipFirstNameValue;
    @FXML
    Label labelPayslipLastNameValue;
    @FXML
    Label labelPayslipRoleNameValue;
    @FXML
    Label labelPayslipRoleCategoryValue;

    // Tree View

    @FXML
    Tab tabViewTreeView;

    @FXML
    TreeView employeeTreeView;

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
            } else if (newTab == tabViewGeneratePayslip) {
                if (this.getSelectedEmployee() == null) {
                    tabPaneGeneral.getSelectionModel().select(oldTab);
                    alert();
                    return;
                }

                onTabViewGeneratePayslipSelected();
            } else if (newTab == tabViewTreeView) {
                generateTree();
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

        // Order buttons
        buttonSortAscend.addEventFilter(MouseEvent.MOUSE_PRESSED, new onSortAscendButtonClick(this));
        buttonSortDescend.addEventFilter(MouseEvent.MOUSE_PRESSED, new onSortDescendButtonClick(this));

        // Payslip
        buttonGeneratePayslip.addEventFilter(MouseEvent.MOUSE_PRESSED, new onGeneratePayslipButtonClick(this));

        // Limit Payslip input to numbers only (no decimals)

        inputDaysWorkedValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                inputDaysWorkedValue.setText("20");
                return;
            }

            if (!newValue.matches("\\d*")) {
                inputDaysWorkedValue.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (Integer.parseInt(newValue) > 31) {
                inputDaysWorkedValue.setText("31");
            }

            if (Integer.parseInt(newValue) < 0) {
                inputDaysWorkedValue.setText("0");
            }
        });

        inputOvertimeHoursValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                inputOvertimeHoursValue.setText("0");
                return;
            }

            if (!newValue.matches("\\d*")) {
                inputOvertimeHoursValue.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (Integer.parseInt(newValue) > 40) {
                inputOvertimeHoursValue.setText("40");
            }

            if (Integer.parseInt(newValue) < 0) {
                inputOvertimeHoursValue.setText("0");
            }
        });

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

    protected void onTabViewGeneratePayslipSelected() {
        System.out.println("Generate Payslip tab selected!");

        Employees selectedEmployee = getSelectedEmployee();

        if (selectedEmployee == null) {
            System.out.println("No employee selected!");
            return;
        }

        labelPayslipEmployeeIDValue.setText(String.valueOf(selectedEmployee.getId()));
        labelPayslipFirstNameValue.setText(selectedEmployee.getFirstName());
        labelPayslipLastNameValue.setText(selectedEmployee.getLastName());
        labelPayslipRoleNameValue.setText(selectedEmployee.getEmployeeRole().getRoleName());
        labelPayslipRoleCategoryValue.setText(selectedEmployee.getEmployeeRole().getRoleCategory().getCategoryName());

        inputDaysWorkedValue.setText("20");
        inputOvertimeHoursValue.setText("0");
    }

    public void generateTree() {

        Dao<RoleCategories, Integer> roleCategoriesDao = Database.getRoleCategoriesDao();
        Dao<EmployeeRoles, Integer> employeeRolesDao = Database.getEmployeeRoleDao();
        Dao<Employees, Integer> employeeDao = Database.getEmployeeDao();

        TreeNode<String> root = new TreeNode<String>("Categories");

        try {
            List<RoleCategories> categories = roleCategoriesDao.queryForAll();

            for (RoleCategories category : categories) {
                TreeNode<String> categoryNode = new TreeNode<>(category.getCategoryName());
                root.addChild(categoryNode);

                List<EmployeeRoles> roles = employeeRolesDao.queryForEq("roleCategory_id", category.getId());
                for (EmployeeRoles role : roles) {
                    TreeNode<String> roleNode = new TreeNode<>(role.getRoleName());
                    categoryNode.addChild(roleNode);

                    List<Employees> employees = employeeDao.queryForEq("employeeRole_id", role.getId());
                    for (Employees employee : employees) {
                        TreeNode<String> employeeNode = new TreeNode<>(employee.getFirstName() + " " + employee.getLastName());
                        roleNode.addChild(employeeNode);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TreeItem<String> rootItem = convertToTreeItem(root);

        employeeTreeView.setRoot(rootItem);

    }

    private TreeItem<String> convertToTreeItem(TreeNode<String> treeNode) {
        TreeItem<String> treeItem = new TreeItem<>(treeNode.getData());
        treeItem.setExpanded(true);

        for (TreeNode<String> childNode : treeNode.getChildren()) {
            treeItem.getChildren().add(convertToTreeItem(childNode));
        }

        return treeItem;
    }


}