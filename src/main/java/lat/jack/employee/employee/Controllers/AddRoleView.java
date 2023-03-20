package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddRoleView {

    @FXML
    TextField inputRoleName;
    @FXML
    TextField inputRoleSalary;
    @FXML
    ComboBox inputRoleCategory;

    // Display
    @FXML
    TextField displayHousingAllowance;
    @FXML
    TextField displayTravelingAllowance;
    @FXML
    TextField displayHealthAllowance;

    @FXML
    protected void initialize() {
        System.out.println("AddRoleView initialized!");
    }
}
