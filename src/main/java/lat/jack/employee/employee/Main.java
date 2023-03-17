package lat.jack.employee.employee;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.io.IOException;


public class Main extends Application {

    private Scene scene;
    private Stage stage;
    private GeneralView generalView;

    private ApplicationManager applicationManager;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create ApplicationManager
        this.applicationManager = new ApplicationManager();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneralView.fxml"));
        this.scene = new Scene(loader.load(), 1000, 600);
        this.generalView = loader.getController();

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
}
