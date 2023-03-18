package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Events.Register.onBackButtonClick;
import lat.jack.employee.employee.Events.Register.onRegisterButtonClick;

public class RegisterView {

    @FXML
    TextField inputUserName;

    @FXML
    PasswordField inputUserPassword;

    @FXML
    Button buttonRegister;

    @FXML
    Button buttonBack;

    @FXML
    protected void initialize() {
        System.out.println("RegisterView initialized!");

        buttonBack.addEventFilter(MouseEvent.MOUSE_PRESSED, new onBackButtonClick(this));
        buttonRegister.addEventFilter(MouseEvent.MOUSE_PRESSED, new onRegisterButtonClick(this));
    }

    public TextField getInputUserName() {
        return inputUserName;
    }

    public PasswordField getInputUserPassword() {
        return inputUserPassword;
    }
}
