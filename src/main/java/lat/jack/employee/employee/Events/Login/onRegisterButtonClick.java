package lat.jack.employee.employee.Events.Login;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Main;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class onRegisterButtonClick implements EventHandler<MouseEvent> {

    private LoginView loginView;

    public onRegisterButtonClick(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("Register button clicked!");
        ApplicationManager.switchScene("RegisterView.fxml", (Node) mouseEvent.getSource());
    }
}
