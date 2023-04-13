package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.EmployeesBenefits;
import lat.jack.employee.employee.Events.EditEmployeeFinancial.onEditEmployeeFinancialButtonClick;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class EditEmployeeFinancialView {

    @FXML
    public
    TextField inputSalary;
    @FXML
    public
    TextField inputBonus;
    @FXML
    public
    TextField inputLoan;
    @FXML
    public
    TextField inputHousingAllowance;
    @FXML
    public
    TextField inputTravelingAllowance;
    @FXML
    public
    TextField inputHealthAllowance;

    // Button
    @FXML
    Button buttonSubmitChanges;

    @FXML
    protected void initialize() {
        System.out.println("EditEmployeeFinancialView initialized!");

        populateFields();

        inputSalary.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                inputSalary.setText("0.00");
                return;
            }
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                inputSalary.setText(oldValue);
                return;
            }

            double salary = Double.parseDouble(inputSalary.getText());
            double housingAllowance = (salary / 100) * 5;
            double healthAllowance = (salary / 100) * 8;

            inputHousingAllowance.setText(String.valueOf(housingAllowance));
            inputHealthAllowance.setText(String.valueOf(healthAllowance));
        });

        doubleNumberFormat(inputBonus);
        doubleNumberFormat(inputLoan);
        doubleNumberFormat(inputTravelingAllowance);

        // Buttons
        buttonSubmitChanges.addEventFilter(MouseEvent.MOUSE_PRESSED, new onEditEmployeeFinancialButtonClick(this));


    }

    public void doubleNumberFormat(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                textField.setText("0.00");
                return;
            }
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            }
        });
    }

    public void populateFields() {
        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();
        EmployeesBenefits selectedEmployeeBenefits = selectedEmployee.getEmployeeBenefits();

        inputSalary.setText(selectedEmployeeBenefits.getEmployeeSalary().toString());
        inputBonus.setText(selectedEmployeeBenefits.getEmployeeBonus().toString());
        inputLoan.setText(selectedEmployeeBenefits.getEmployeeLoan().toString());
        inputHousingAllowance.setText(selectedEmployeeBenefits.getHousingAllowance().toString());
        inputTravelingAllowance.setText(selectedEmployeeBenefits.getTravelingAllowance().toString());
        inputHealthAllowance.setText(selectedEmployeeBenefits.getHealthAllowance().toString());
    }
}
