package lat.jack.employee.employee.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lat.jack.employee.employee.Entities.Users;

import java.sql.SQLException;

public class Database {

    private static ConnectionSource connectionSource;
    private static final String databaseURL = "jdbc:sqlite:database.db";
    private static Dao<Users, Integer> userDao;

    public Database() {
        getNewConnectionSource();

    }

    private void getNewConnectionSource() {

        try {
            Class.forName("com.j256.ormlite.jdbc.db.SqliteDatabaseType");
            connectionSource = new JdbcConnectionSource(databaseURL);
            userDao = DaoManager.createDao(connectionSource, Users.class);
            TableUtils.createTableIfNotExists(connectionSource, Users.class);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static Dao<Users, Integer> getUserDao() {

        if (userDao == null) {
            try {
                userDao = DaoManager.createDao(connectionSource, Users.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userDao;
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
