package lat.jack.employee.employee.Managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Users;
import lat.jack.employee.employee.Main;

import java.io.IOException;
import java.util.Objects;

public class ApplicationManager {

    private Database database;
    private static Main main;

    protected static Users currentUser;

    public ApplicationManager(Main main) {

        this.main = main;
        this.database = new Database();

        // Create tables if not exists
        System.out.println("ApplicationManager initialized!");
    }

    public Database getDatabase() {
        return database;
    }

    public static Main getMain() {
        return main;
    }

    public static void switchScene(String fxmlPath, Node node) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlPath)));
            Stage stage = (Stage) node.getScene().getWindow(); // Get stage from node
            stage.setScene(new Scene(root, 520, 820));

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void switchScene(String fxmlPath, Node node, int width, int height) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlPath)));
            Stage stage = (Stage) node.getScene().getWindow(); // Get stage from node
            stage.setScene(new Scene(root, width, height));

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static void switchScene(String fxmlPath, Stage stage) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlPath)));
            stage.setScene(new Scene(root, 520, 820));

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Users user) {
        System.out.println("UserID: " + user.getId());
        currentUser = user;
    }
}
