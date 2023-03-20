package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class EmployeeRoles {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String roleName;

    @DatabaseField()
    private Float startingSalary;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private RoleCategories roleCategory;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private RoleBenefits roleBenefits;

    public EmployeeRoles(String roleName, Float startingSalary, RoleCategories roleCategory, RoleBenefits roleBenefits) {
        this.roleName = roleName;
        this.startingSalary = startingSalary;
        this.roleCategory = roleCategory;
        this.roleBenefits = roleBenefits;
    }

    public EmployeeRoles() {
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Float getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(Float startingSalary) {
        this.startingSalary = startingSalary;
    }

    public RoleCategories getRoleCategory() {
        return roleCategory;
    }

    public void setRoleCategory(RoleCategories roleCategory) {
        this.roleCategory = roleCategory;
    }

    public RoleBenefits getRoleBenefits() {
        return roleBenefits;
    }

    public void setRoleBenefits(RoleBenefits roleBenefits) {
        this.roleBenefits = roleBenefits;
    }
}
