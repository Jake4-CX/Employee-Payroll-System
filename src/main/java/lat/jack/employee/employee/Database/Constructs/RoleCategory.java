package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleCategory {

    public RoleCategory() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS RoleCategories" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'categoryName' VARCHAR);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
