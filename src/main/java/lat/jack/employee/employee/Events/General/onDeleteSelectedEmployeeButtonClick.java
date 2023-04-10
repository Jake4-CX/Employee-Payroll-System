package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.GeneralView;

public class onDeleteSelectedEmployeeButtonClick implements EventHandler<MouseEvent>  {

    private GeneralView generalView;

    public onDeleteSelectedEmployeeButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {

        // Are you sure you want to delete this employee?

        if (checkIfSure() == ButtonType.OK) {
            // Delete employee

            System.out.println("Deleting employee with ID: " + generalView.getSelectedEmployee().getId());

            // TODO: Delete employee from database

            // Update table - potentially use an Algorithm that the assignment wants
            generalView.updateEmployeeTable(generalView.getAllEmployees());
        }

        return;

    }

    private ButtonType checkIfSure() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete employee");
        alert.setHeaderText("Are you sure you want to delete this employee?");
        alert.setContentText("This action cannot be undone!");

        alert.showAndWait();

        return alert.getResult();

    }
}
