package lat.jack.employee.employee.Events.Login;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Database.Constructs.User;

public class onLoginButtonClick implements EventHandler<MouseEvent> {

    private LoginView loginView;

    public onLoginButtonClick(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("Login button clicked!");

        String userName = loginView.getInputUserName().getText();
        String userPassword = loginView.getInputUserPassword().getText();

        int userID;
        if ((userID = User.checkUser(userName, userPassword)) != -1) {
            System.out.println("You have logged in - ID: " + userID);

        } else {
            System.out.println("Invalid username or password");
        }
    }
}
