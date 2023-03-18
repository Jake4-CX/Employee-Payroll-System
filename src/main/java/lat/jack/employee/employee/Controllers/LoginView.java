package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import lat.jack.employee.employee.Events.Login.onLoginButtonClick;
import lat.jack.employee.employee.Events.Login.onRegisterButtonClick;

public class LoginView {

    @FXML
    TextField inputUserName;
    @FXML
    PasswordField inputUserPassword;
    @FXML
    Button buttonLogin;
    @FXML
    Button buttonRegister;

    @FXML
    protected void initialize() {
        System.out.println("LoginView initialized!");

        buttonLogin.addEventFilter(MouseEvent.MOUSE_PRESSED, new onLoginButtonClick(this));
        buttonRegister.addEventFilter(MouseEvent.MOUSE_PRESSED, new onRegisterButtonClick(this));

    }

    public TextField getInputUserName() {
        return inputUserName;
    }

    public PasswordField getInputUserPassword() {
        return inputUserPassword;
    }
}
