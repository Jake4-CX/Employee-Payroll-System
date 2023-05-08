package lat.jack.employee.employee.Events.Login;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Users;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;

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

        try {
            QueryBuilder<Users, Integer> queryBuilder = Database.getUserDao().queryBuilder();
            PreparedQuery<Users> preparedQuery = queryBuilder.where().eq("userName", userName).prepare();
            Users user = Database.getUserDao().queryForFirst(preparedQuery);

            if (user != null) {
                // Check password bcrypt hash

                if (BCrypt.verifyer().verify(userPassword.toCharArray(), user.getUserPassword()).verified) {
                    // User successfully logged in
                    System.out.println("You have successfully logged in");
                    loginView.getTaskQueue().addToQueue("Login Success!");
                    ApplicationManager.setCurrentUser(user);

                    ApplicationManager.switchScene("GeneralView.fxml", (Node) mouseEvent.getSource(), 1000, 600);
                } else {
                    System.out.println("User exists in database - but wrong password");
                    loginView.getTaskQueue().addToQueue("Invalid User/Pass");
                }
            } else {
                // User does not exist - display error message alert - 'Wrong username or password'
                System.out.println("User does not exist in database");
                loginView.getTaskQueue().addToQueue("Invalid User/Pass");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    Replaced with a toaster w/ FIFO Queue
//    public void invalidLogin() {
//        // Display error message alert - 'Wrong username or password'
//
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Invalid Login");
//        alert.setHeaderText("Wrong username or password");
//        alert.setContentText("Please enter a valid username and password");
//        alert.showAndWait();
//    }
}
