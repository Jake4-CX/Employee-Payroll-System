package lat.jack.employee.employee.Models;

public class RoleCategory {

    protected int ID;
    protected String categoryName;

    public RoleCategory(int ID, String categoryName) {
        this.ID = ID;
        this.categoryName = categoryName;
    }

    public int getID() {
        return ID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
