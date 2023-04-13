package lat.jack.employee.employee.Events.EditEmployees;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.EditEmployeeView;
import lat.jack.employee.employee.Main;

import java.io.IOException;
import java.util.Objects;

public class onEditEmployeeFinancialButtonClick implements EventHandler<MouseEvent> {

    private EditEmployeeView editEmployeeView;

    public onEditEmployeeFinancialButtonClick(EditEmployeeView editEmployeeView) {
        this.editEmployeeView = editEmployeeView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Edit Employee Financial button clicked!");

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("EditEmployeeFinancialView.fxml")));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Edit Employee Financial Data");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
