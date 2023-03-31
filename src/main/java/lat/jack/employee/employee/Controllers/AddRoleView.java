package lat.jack.employee.employee.Controllers;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.RoleCategories;
import lat.jack.employee.employee.Events.General.onAddCategoryButtonClick;
import lat.jack.employee.employee.Events.Role.onAddRoleButtonClick;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddRoleView {

    @FXML
    TextField inputRoleName;
    @FXML
    TextField inputRoleSalary;
    @FXML
    ComboBox inputRoleCategory;
    @FXML
    TextField inputTravelingAllowance;

    // Display
    @FXML
    TextField displayHousingAllowance;
    @FXML
    TextField displayHealthAllowance;

    // Button
    @FXML
    Button buttonAddRole;

    private HashMap<String, RoleCategories> roleCategoriesHashMap = new HashMap<>();

    @FXML
    protected void initialize() {
        System.out.println("AddRoleView initialized!");

        getRoleCategories().forEach(roleCategory -> {
            inputRoleCategory.getItems().add(roleCategory.getCategoryName());
        });
        if (inputRoleCategory.getItems().size() > 0) {
            inputRoleCategory.setValue(inputRoleCategory.getItems().get(0));
        }

        // Events
        buttonAddRole.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddRoleButtonClick(this));

        inputRoleSalary.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                inputRoleSalary.setText("0.00");
                return;
            }
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                inputRoleSalary.setText(oldValue);
                return;
            }

            double salary = Double.parseDouble(inputRoleSalary.getText());
            double housingAllowance = (salary / 100) * 5;
            double healthAllowance = (salary / 100) * 8;

            setDisplayHousingAllowance(String.valueOf(housingAllowance));
            setDisplayHealthAllowance(String.valueOf(healthAllowance));
        });

        inputTravelingAllowance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                inputTravelingAllowance.setText("0.00");
                return;
            }
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                inputTravelingAllowance.setText(oldValue);
            }
        });
    }

    public List<RoleCategories> getRoleCategories() {

        Dao<RoleCategories, Integer> roleCategoriesDao = Database.getRoleCategoriesDao();
        List<RoleCategories> roleCategories = new ArrayList<>();

        try {
            roleCategories = roleCategoriesDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        roleCategories.forEach(roleCategory -> {
            roleCategoriesHashMap.put(roleCategory.getCategoryName(), roleCategory);
        });

        return roleCategories;
    }

    public HashMap<String, RoleCategories> getRoleCategoriesHashMap() {
        return roleCategoriesHashMap;
    }

    public TextField getInputRoleName() {
        return inputRoleName;
    }

    public TextField getInputRoleSalary() {
        return inputRoleSalary;
    }

    public TextField getInputTravelingAllowance() {
        return inputTravelingAllowance;
    }

    public ComboBox getInputRoleCategory() {
        return inputRoleCategory;
    }

    public void setDisplayHousingAllowance(String displayHousingAllowance) {
        this.displayHousingAllowance.setText(displayHousingAllowance);
    }

    public void setDisplayHealthAllowance(String displayHealthAllowance) {
        this.displayHealthAllowance.setText(displayHealthAllowance);
    }
}
