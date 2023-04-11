package lat.jack.employee.employee.Events.Register;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.RegisterView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Users;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;

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

            try {
                QueryBuilder<Users, Integer> queryBuilder = Database.getUserDao().queryBuilder();
                PreparedQuery<Users> preparedQuery = queryBuilder.where().eq("userName", userName).prepare();
                Users user = Database.getUserDao().queryForFirst(preparedQuery);

                if (user == null) {
                    // User does not exist - create new user

                    // Hash password

                    userPassword = BCrypt.withDefaults().hashToString(12, userPassword.toCharArray());

                    Users newUser = new Users(userName, userPassword);

                    int response  = Database.getUserDao().create(newUser);

                    if (response == 1) {
                        System.out.println("User created successfully");
                        alertAccountCreated();
                        ApplicationManager.switchScene("LoginView.fxml", (Node) mouseEvent.getSource());
                        return;

                    } else {
                        System.out.println("User not created");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            alertUserExists();


        } else {
            System.out.println("Both values are empty");
            bothValuesEmpty();
        }
    }

    private void alertAccountCreated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account created");
        alert.setHeaderText("Account created successfully");
        alert.setContentText("Please login to continue");
        alert.showAndWait();
    }

    private void alertUserExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cannot register");
        alert.setHeaderText("User already exists");
        alert.setContentText("Please enter a different username");
        alert.showAndWait();
    }

    // Abstract function for both values are empty
    public void bothValuesEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cannot register");
        alert.setHeaderText("Value(s) are empty");
        alert.setContentText("Please enter both a username and password");
        alert.showAndWait();
    }
}
