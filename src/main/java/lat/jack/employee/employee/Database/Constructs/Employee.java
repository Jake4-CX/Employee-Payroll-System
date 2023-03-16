package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee {

    public Employee() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS Employees" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'firstName' VARCHAR, " +
                "'lastName' VARCHAR, " +
                "'emailAddress' VARCHAR, " +
                "'phoneNumber' VARCHAR, " +
                "'hireDate' DATETIME, " +
                "'addressID' INTEGER, " +
                "'roleID' INTEGER, " +
                "FOREIGN KEY(addressID) REFERENCES Addresses(ID) ON DELETE CASCADE);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(String firstName, String lastName, String emailAddress, String phoneNumber, String hireDate, int addressID, int roleID) {
        Database.checkConnection();

        String query = "INSERT INTO Employees('firstName', 'lastName', 'emailAddress', 'phoneNumber', 'hireDate', 'addressID', 'roleID') VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, emailAddress);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, hireDate);
            preparedStatement.setInt(6, addressID);
            preparedStatement.setInt(7, roleID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
