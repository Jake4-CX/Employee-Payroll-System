package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class PayslipView {

    // Basic Salary
    @FXML
    Label labelYearlySalary;
    @FXML
    Label labelHoursWorked;
    @FXML
    Label labelBaseSalary;

    // Overtime
    @FXML
    Label labelOvertimeHours;

    // Allowances
    @FXML
    Label labelHousingAllowance;
    @FXML
    Label labelTravelingAllowance;
    @FXML
    Label labelHealthAllowance;

    // Gross Salary
    @FXML
    Label labelGrossSalary;

    // Deductions
    @FXML
    Label labelTax;

    // Net Salary
    @FXML
    Label labelNetSalary;

    @FXML
    protected void initialize() {
        System.out.println("PayslipView initialized!");
        generatePayslip();
    }


    protected String formatNumber(Double number) {
        // format the number to a currency format with 2 decimal places (and commas)
        return String.format("%,.2f", number);
    }
    protected void generatePayslip() {
        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();

        Double hoursWorked = 10.00;
        Double overtimeHours = 5.00;

        Double yearlySalary = selectedEmployee.getEmployeeBenefits().getEmployeeSalary();
        Double baseSalary = ((yearlySalary/12)/40) * hoursWorked;

        Double housingAllowance = selectedEmployee.getEmployeeBenefits().getHousingAllowance() / 12;
        Double travelingAllowance = selectedEmployee.getEmployeeBenefits().getTravelingAllowance() / 12;
        Double healthAllowance = selectedEmployee.getEmployeeBenefits().getHealthAllowance() / 12;

        double grossSalary = baseSalary + housingAllowance + travelingAllowance + healthAllowance + (baseSalary/hoursWorked * overtimeHours);
        double tax = (grossSalary / 100) * 20;

        // Basic Salary
        labelYearlySalary.setText(formatNumber(yearlySalary));
        labelHoursWorked.setText(String.valueOf(hoursWorked));
        labelBaseSalary.setText(formatNumber(baseSalary));

        // Overtime
        labelOvertimeHours.setText(String.valueOf(overtimeHours));

        // Allowances
        labelHousingAllowance.setText(formatNumber(housingAllowance));
        labelTravelingAllowance.setText(formatNumber(travelingAllowance));
        labelHealthAllowance.setText(formatNumber(healthAllowance));

        // Gross Salary
        labelGrossSalary.setText(formatNumber(grossSalary));

        // Deductions
        labelTax.setText(formatNumber(tax));

        // Net Salary
        labelNetSalary.setText(formatNumber(grossSalary - tax));
    }
}
