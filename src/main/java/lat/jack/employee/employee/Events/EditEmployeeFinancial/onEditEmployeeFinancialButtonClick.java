package lat.jack.employee.employee.Events.EditEmployeeFinancial;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.EditEmployeeFinancialView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.EmployeesBenefits;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;

public class onEditEmployeeFinancialButtonClick implements EventHandler<MouseEvent> {

    private EditEmployeeFinancialView editEmployeeFinancialView;

    public onEditEmployeeFinancialButtonClick(EditEmployeeFinancialView editEmployeeFinancialView) {
        this.editEmployeeFinancialView = editEmployeeFinancialView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Edit Employee Financial button clicked!");

        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();
        EmployeesBenefits selectedEmployeeBenefits = selectedEmployee.getEmployeeBenefits();

        selectedEmployeeBenefits.setEmployeeSalary(Double.valueOf(editEmployeeFinancialView.inputSalary.getText()));
        selectedEmployeeBenefits.setEmployeeBonus(Double.valueOf(editEmployeeFinancialView.inputBonus.getText()));
        selectedEmployeeBenefits.setEmployeeLoan(Double.valueOf(editEmployeeFinancialView.inputLoan.getText()));
        selectedEmployeeBenefits.setHousingAllowance(Double.valueOf(editEmployeeFinancialView.inputHousingAllowance.getText()));
        selectedEmployeeBenefits.setTravelingAllowance(Double.valueOf(editEmployeeFinancialView.inputTravelingAllowance.getText()));
        selectedEmployeeBenefits.setHealthAllowance(Double.valueOf(editEmployeeFinancialView.inputHealthAllowance.getText()));

        // employeeBenefitsDAO

        Dao<EmployeesBenefits, Integer> employeesBenefitsDao = Database.getEmployeeBenefitsDao();

        try {
            int response = employeesBenefitsDao.update(selectedEmployeeBenefits);

            if (response == 1) {
                System.out.println("Employee Benefits updated successfully!");
            } else {
                System.out.println("Employee Benefits update failed!");
                alertNotUpdated();
                return;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        alertUpdated();

        // Close this popup window

    }

    private void alertUpdated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Employee Benefits updated!");
        alert.setContentText("Employee Benefits updated successfully!");
        alert.showAndWait();
    }

    private void alertNotUpdated() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Employee Benefits not updated!");
        alert.setContentText("Employee Benefits not updated! Please try again later.");
        alert.showAndWait();
    }
}
