package lat.jack.employee.employee.Database.Constructs;

import lat.jack.employee.employee.Database.Database;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    public User() {

        Database.checkConnection();

        String query = "CREATE TABLE IF NOT EXISTS Users" +
                "('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'userName' TEXT, " +
                "'userPassword' TEXT);";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String userName, String userPassword) {
        Database.checkConnection();

        String query = "INSERT INTO Users(userName, userPassword) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            userPassword = BCrypt.withDefaults().hashToString(12, userPassword.toCharArray());

            preparedStatement.setString(2, userPassword);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Object checkUser(String userName, String userPassword) { // Used for user login
        Database.checkConnection();

        String query = "SELECT * FROM Users WHERE userName = ? LIMIT 1";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("userPassword");

                if (BCrypt.verifyer().verify(userPassword.toCharArray(), hashedPassword).verified) {
                    return resultSet.getInt("ID");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultSet getUsers() {

        Database.checkConnection();

        String query = "SELECT ID, userName FROM Users";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Boolean existsUser(String userName) {

        Database.checkConnection();

        String query = "SELECT * FROM Users WHERE userName = ? LIMIT 1";

        try {
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            return preparedStatement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
