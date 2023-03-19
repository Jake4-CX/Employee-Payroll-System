package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class EmployeesRoles {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private EmployeeRoles employeeRole;

    @DatabaseField()
    private EmployeesBenefits employeeBenefits;

    public EmployeesRoles(EmployeeRoles employeeRole, EmployeesBenefits employeeBenefits) {
        this.employeeRole = employeeRole;
        this.employeeBenefits = employeeBenefits;
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
