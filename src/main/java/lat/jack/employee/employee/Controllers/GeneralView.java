package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class GeneralView {

    @FXML
    Label labelWelcomeName;

    @FXML
    Tab tabViewEmployee;

    @FXML
    TabPane tabPaneGeneral;

    @FXML
    protected void initialize() {
        System.out.println("GeneralView initialized!");
        labelWelcomeName.setText("Welcome, " + ApplicationManager.getCurrentUser().getUserName() + "!");

        tabPaneGeneral.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab == tabViewEmployee) {
                onTabViewEmployeeSelected();
            }
        });
    }

    protected void onTabViewEmployeeSelected() {
        System.out.println("View Employee tab selected!");


    }
}
