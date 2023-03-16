package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Addresses {

    public Addresses() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS Addresses" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'streetName' VARCHAR, " +
                "'addressRegion' VARCHAR, " +
                "'addressCity' VARCHAR, " +
                "'postalCode' VARCHAR, " +
                "'country' VARCHAR);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addAddress(String streetName, String addressRegion, String addressCity, String postalCode, String country) {

        Database.checkConnection();

        String query = "INSERT INTO Addresses('streetName', 'addressRegion', 'addressCity', 'postalCode', 'country') VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, streetName);
            preparedStatement.setString(2, addressRegion);
            preparedStatement.setString(3, addressCity);
            preparedStatement.setString(4, postalCode);
            preparedStatement.setString(5, country);

            preparedStatement.execute();
            // and get the ID of the new address
            ResultSet generateKeys = preparedStatement.getGeneratedKeys();

            return generateKeys.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
