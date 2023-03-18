package lat.jack.employee.employee.Events.Register;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.RegisterView;
import lat.jack.employee.employee.Database.Constructs.User;
import lat.jack.employee.employee.Database.Database;

public class onRegisterButtonClick implements EventHandler<MouseEvent> {

    private RegisterView registerView;

    public onRegisterButtonClick(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("Register button clicked!");

        String userName = registerView.getInputUserName().getText();
        String userPassword = registerView.getInputUserPassword().getText();

        if (userName.length() > 0 && userPassword.length() > 0) {
            System.out.println("Both values are not empty");

            if (!(User.existsUser(userName))) {
                // User does not exist
                System.out.println("User does not exist");

            }


        } else {
            System.out.println("Both values are empty");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot register");
            alert.setHeaderText("Value(s) are empty");
            alert.setContentText("Please enter both a username and password");
            alert.showAndWait();
        }
    }
}
