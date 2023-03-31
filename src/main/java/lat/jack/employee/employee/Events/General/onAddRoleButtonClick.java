package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Main;

import java.io.IOException;
import java.util.Objects;

public class onAddRoleButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onAddRoleButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Add role button clicked!");

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("AddRoleView.fxml")));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Add Role");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Apparently opened the new window!");
    }
}
