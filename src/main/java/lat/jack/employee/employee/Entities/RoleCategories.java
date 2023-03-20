package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class RoleCategories {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String categoryName;

    public RoleCategories(String categoryName) {
        this.categoryName = categoryName;
    }

    public RoleCategories() {
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
