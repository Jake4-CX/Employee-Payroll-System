package lat.jack.employee.employee.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.PSInfo;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class PayslipView {

    // Basic Salary
    @FXML
    Label labelYearlySalary;
    @FXML
    Label labelDaysWorked;
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

    protected Double calculateUKIncomeTax(Double salary) {
        double personalAllowance = 12500;
        // Limits
        double basicUpperLimit = 50270;
        double higherUpperLimit = 150000;

        // Rates
        double basicRate = 0.2;
        double higherRate = 0.4;
        double additionalRate = 0.45;

        double taxableIncome = salary - personalAllowance;
        double incomeTax = 0;

        if (taxableIncome <= 0) {
            incomeTax = 0;
        } else if (taxableIncome <= basicUpperLimit) {
            incomeTax = taxableIncome * basicRate;
        } else if (taxableIncome <= higherUpperLimit) {
            incomeTax = (basicUpperLimit * basicRate) + ((taxableIncome - basicUpperLimit) * higherRate);
        } else {
            incomeTax = (basicUpperLimit * basicRate) + ((higherUpperLimit - basicUpperLimit) * higherRate) + ((taxableIncome - higherUpperLimit) * additionalRate);
        }

        return incomeTax;

    }
    protected void generatePayslip() {
        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();
        PSInfo psInfo = ApplicationManager.getPSInfo();

        Integer daysWorked = psInfo.getDaysWorked(); //10.00;
        Integer overtimeHours = psInfo.getOvertime();

        Double yearlySalary = selectedEmployee.getEmployeeBenefits().getEmployeeSalary();
        Double baseSalary = ((yearlySalary/12)/20) * daysWorked;

        Double housingAllowance = selectedEmployee.getEmployeeBenefits().getHousingAllowance() / 12;
        Double travelingAllowance = selectedEmployee.getEmployeeBenefits().getTravelingAllowance() / 12;
        Double healthAllowance = selectedEmployee.getEmployeeBenefits().getHealthAllowance() / 12;

        double grossSalary = baseSalary + housingAllowance + travelingAllowance + healthAllowance + (((yearlySalary/12)/20) * overtimeHours);
        double tax = calculateUKIncomeTax(grossSalary * 12) / 12;

        // Basic Salary
        labelYearlySalary.setText(formatNumber(yearlySalary));
        labelDaysWorked.setText(String.valueOf(daysWorked));
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
