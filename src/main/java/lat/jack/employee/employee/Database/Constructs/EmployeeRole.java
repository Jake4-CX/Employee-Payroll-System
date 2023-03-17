package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRole {

    public EmployeeRole() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS EmployeeRoles" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'roleName' TEXT, " +
                "'startingSalary' INTEGER, " +
                "'categoryID' INTEGER, " +
                "'benefitsID' INTEGER," +
                "FOREIGN KEY(categoryID) REFERENCES RoleCategories(ID) ON DELETE NO ACTION, " +
                "FOREIGN KEY(benefitsID) REFERENCES RoleBenefits(ID) ON DELETE CASCADE);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
