package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class EmployeesBenefits {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private Float employeeSalary;

    @DatabaseField()
    private Float employeeBonus;

    @DatabaseField()
    private Float employeeLoan;

    @DatabaseField()
    private Float housingAllowance;

    @DatabaseField()
    private Float travelingAllowance;

    @DatabaseField()
    private Float healthAllowance;

    public EmployeesBenefits(Float employeeSalary, Float employeeBonus, Float employeeLoan, Float housingAllowance, Float travelingAllowance, Float healthAllowance) {
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

    public Float getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Float employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Float getEmployeeBonus() {
        return employeeBonus;
    }

    public void setEmployeeBonus(Float employeeBonus) {
        this.employeeBonus = employeeBonus;
    }

    public Float getEmployeeLoan() {
        return employeeLoan;
    }

    public void setEmployeeLoan(Float employeeLoan) {
        this.employeeLoan = employeeLoan;
    }

    public Float getHousingAllowance() {
        return housingAllowance;
    }

    public void setHousingAllowance(Float housingAllowance) {
        this.housingAllowance = housingAllowance;
    }

    public Float getTravelingAllowance() {
        return travelingAllowance;
    }

    public void setTravelingAllowance(Float travelingAllowance) {
        this.travelingAllowance = travelingAllowance;
    }

    public Float getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(Float healthAllowance) {
        this.healthAllowance = healthAllowance;
    }
}
