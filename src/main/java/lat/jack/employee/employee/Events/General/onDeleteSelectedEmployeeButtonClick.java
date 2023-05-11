package lat.jack.employee.employee.Events.General;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Managers.ApplicationManager;

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

            // Delete employee from database

            Employees selectedEmployee = ApplicationManager.getSelectedEmployee();

            Dao<Employees, Integer> employeesDao = Database.getEmployeeDao();

            try {
                if (employeesDao.delete(selectedEmployee) == 1) {
                    deletedEmployee();
                } else {
                    couldNotDeleteEmployee();
                }
            } catch (Exception e) {
                e.printStackTrace();
                couldNotDeleteEmployee();
            }

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

    private void couldNotDeleteEmployee() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not delete employee!");
        alert.setContentText("Please try again later.");

        alert.showAndWait();
    }

    private void deletedEmployee() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Employee deleted!");
        alert.setContentText("Employee has been deleted, please close this window.");

        alert.showAndWait();
    }
}
