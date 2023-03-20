package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class EmployeesRoles {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private EmployeeRoles employeeRole;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private EmployeesBenefits employeeBenefits;

    public EmployeesRoles(EmployeeRoles employeeRole, EmployeesBenefits employeeBenefits) {
        this.employeeRole = employeeRole;
        this.employeeBenefits = employeeBenefits;
    }

    public EmployeesRoles() {
    }

    public int getId() {
        return id;
    }

    public EmployeeRoles getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRoles employeeRole) {
        this.employeeRole = employeeRole;
    }

    public EmployeesBenefits getEmployeeBenefits() {
        return employeeBenefits;
    }

    public void setEmployeeBenefits(EmployeesBenefits employeeBenefits) {
        this.employeeBenefits = employeeBenefits;
    }
}
