package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lat.jack.employee.employee.DataStructures.TaskQueue;
import lat.jack.employee.employee.Events.Login.onLoginButtonClick;
import lat.jack.employee.employee.Events.Login.onRegisterButtonClick;
import lat.jack.employee.employee.Main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class LoginView implements Toaster {

    TaskQueue taskQueue = null;

    @FXML
    TextField inputUserName;
    @FXML
    PasswordField inputUserPassword;
    @FXML
    Button buttonLogin;
    @FXML
    Button buttonRegister;

    // Toast
    @FXML
    StackPane toasterPane;
    @FXML
    ImageView toasterImage;
    @FXML
    Text toasterText;

    @FXML
    protected void initialize() {
        System.out.println("LoginView initialized!");

        this.taskQueue = new TaskQueue(this);

        buttonLogin.addEventFilter(MouseEvent.MOUSE_PRESSED, new onLoginButtonClick(this));
        buttonRegister.addEventFilter(MouseEvent.MOUSE_PRESSED, new onRegisterButtonClick(this));

    }

    @Override
    public void toaster(String message) {
        toasterText.setText(message);
        toasterPane.setVisible(true);
        toasterPane.setOpacity(1);
        toasterPane.setTranslateY(0);
        toasterPane.setDisable(false);

        toasterImage.setImage(new Image(String.valueOf(Main.class.getResource("Assets/Toasts/Black.png"))));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toasterText.setText("");
                toasterPane.setOpacity(0);
                toasterPane.setTranslateY(-50);
                toasterPane.setDisable(true);
            }
        }, 2000);
    }

    public TaskQueue getTaskQueue() {
        return taskQueue;
    }

    public TextField getInputUserName() {
        return inputUserName;
    }

    public PasswordField getInputUserPassword() {
        return inputUserPassword;
    }
}
