package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class EmployeesBenefits {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private Double employeeSalary;

    @DatabaseField()
    private Double employeeBonus;

    @DatabaseField()
    private Double employeeLoan;

    @DatabaseField()
    private Double housingAllowance;

    @DatabaseField()
    private Double travelingAllowance;

    @DatabaseField()
    private Double healthAllowance;

    public EmployeesBenefits(Double employeeSalary, Double employeeBonus, Double employeeLoan, Double housingAllowance, Double travelingAllowance, Double healthAllowance) {
        this.employeeSalary = employeeSalary;
        this.employeeBonus = employeeBonus;
        this.employeeLoan = employeeLoan;
        this.housingAllowance = housingAllowance;
        this.travelingAllowance = travelingAllowance;
        this.healthAllowance = healthAllowance;
    }

    public EmployeesBenefits() {
    }

    public int getId() {
        return id;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Double getEmployeeBonus() {
        return employeeBonus;
    }

    public void setEmployeeBonus(Double employeeBonus) {
        this.employeeBonus = employeeBonus;
    }

    public Double getEmployeeLoan() {
        return employeeLoan;
    }

    public void setEmployeeLoan(Double employeeLoan) {
        this.employeeLoan = employeeLoan;
    }

    public Double getHousingAllowance() {
        return housingAllowance;
    }

    public void setHousingAllowance(Double housingAllowance) {
        this.housingAllowance = housingAllowance;
    }

    public Double getTravelingAllowance() {
        return travelingAllowance;
    }

    public void setTravelingAllowance(Double travelingAllowance) {
        this.travelingAllowance = travelingAllowance;
    }

    public Double getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(Double healthAllowance) {
        this.healthAllowance = healthAllowance;
    }
}
