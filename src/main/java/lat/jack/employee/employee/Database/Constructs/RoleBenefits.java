package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleBenefits {

    public RoleBenefits() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS RoleBenefits" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'housingAllowance' INTEGER, " +
                "'travelingAllowance' INTEGER, " +
                "'healthAllowance' INTEGER);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
