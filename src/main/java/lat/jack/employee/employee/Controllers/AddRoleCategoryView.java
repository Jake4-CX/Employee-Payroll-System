package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Events.RoleCategory.onAddCategoryButtonClick;

public class AddRoleCategoryView {

    @FXML
    TextField inputCategoryName;
    @FXML
    Button buttonAddCategory;

    @FXML
    protected void initialize() {
        System.out.println("AddRoleCategoryView initialized!");

        buttonAddCategory.addEventFilter(MouseEvent.MOUSE_PRESSED, new onAddCategoryButtonClick(this));
    }

    public TextField getInputCategoryName() {
        return inputCategoryName;
    }
}
