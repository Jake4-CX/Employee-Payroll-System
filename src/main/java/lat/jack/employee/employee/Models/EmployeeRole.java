package lat.jack.employee.employee.Models;

public class EmployeeRole {

    protected int ID;
    protected int roleName;
    protected Float startingSalary;
    protected RoleCategory roleCategory;
    protected RoleBenefits roleBenefits;

    public EmployeeRole(int ID, int roleName, Float startingSalary, RoleCategory roleCategory, RoleBenefits roleBenefits) {
        this.ID = ID;
        this.roleName = roleName;
        this.startingSalary = startingSalary;
        this.roleCategory = roleCategory;
        this.roleBenefits = roleBenefits;
    }

    public int getID() {
        return ID;
    }

    public int getRoleName() {
        return roleName;
    }

    public void setRoleName(int roleName) {
        this.roleName = roleName;
    }

    public Float getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(Float startingSalary) {
        this.startingSalary = startingSalary;
    }

    public RoleCategory getRoleCategory() {
        return roleCategory;
    }

    public void setRoleCategory(RoleCategory roleCategory) {
        this.roleCategory = roleCategory;
    }

    public RoleBenefits getRoleBenefits() {
        return roleBenefits;
    }

    public void setRoleBenefits(RoleBenefits roleBenefits) {
        this.roleBenefits = roleBenefits;
    }
}
