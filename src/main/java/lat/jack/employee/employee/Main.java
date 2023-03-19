package lat.jack.employee.employee;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Controllers.LoginView;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.io.IOException;


public class Main extends Application {

    private Scene scene;
    private Stage stage;
    // private GeneralView generalView;
    private LoginView loginView;

    static ApplicationManager applicationManager;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create ApplicationManager
        applicationManager = new ApplicationManager(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        this.scene = new Scene(loader.load(), 520, 820);
        // this.scene = new Scene(loader.load(), 1000, 600);
        this.loginView = loader.getController();

        this.stage = primaryStage;

        primaryStage.setResizable(false);
        primaryStage.setTitle("Employee Payroll System");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public static ApplicationManager getApplicationManager() {
        return applicationManager;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
}
