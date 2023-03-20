package lat.jack.employee.employee.Events.General;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Main;

import java.io.IOException;
import java.util.Objects;

public class onAddCategoryButtonClick implements EventHandler<MouseEvent> {
    private GeneralView generalView;

    public onAddCategoryButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Add category button clicked!");

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("AddRoleCategoryView.fxml")));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Add Category");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Apparently opened the new window!");

    }
}
