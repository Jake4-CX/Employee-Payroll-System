package lat.jack.employee.employee.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lat.jack.employee.employee.Entities.*;

import java.sql.SQLException;

public class Database {

    private static ConnectionSource connectionSource;
    private static final String databaseURL = "jdbc:sqlite:database.db";
    private static Dao<Users, Integer> userDao;
    private static Dao<Employees, Integer> employeeDao;
    private static Dao<Addresses, Integer> addressDao;
    private static Dao<EmployeeRoles, Integer> employeeRoleDao;
    private static Dao<EmployeesRoles, Integer> employeeRolesDao;
    private static Dao<EmployeesBenefits, Integer> employeeBenefitsDao;
    private static Dao<RoleBenefits, Integer> roleBenefitsDao;
    private static Dao<RoleCategories, Integer> roleCategoriesDao;

    public Database() {
        getNewConnectionSource();

    }

    private void getNewConnectionSource() {

        try {
            Class.forName("com.j256.ormlite.jdbc.db.SqliteDatabaseType");
            connectionSource = new JdbcConnectionSource(databaseURL);

            userDao = DaoManager.createDao(connectionSource, Users.class);
            TableUtils.createTableIfNotExists(connectionSource, Users.class);

            employeeDao = DaoManager.createDao(connectionSource, Employees.class);
            TableUtils.createTableIfNotExists(connectionSource, Employees.class);

            addressDao = DaoManager.createDao(connectionSource, Addresses.class);
            TableUtils.createTableIfNotExists(connectionSource, Addresses.class);

            employeeRoleDao = DaoManager.createDao(connectionSource, EmployeeRoles.class);
            TableUtils.createTableIfNotExists(connectionSource, EmployeeRoles.class);

            employeeRolesDao = DaoManager.createDao(connectionSource, EmployeesRoles.class);
            TableUtils.createTableIfNotExists(connectionSource, EmployeesRoles.class);

            employeeBenefitsDao = DaoManager.createDao(connectionSource, EmployeesBenefits.class);
            TableUtils.createTableIfNotExists(connectionSource, EmployeesBenefits.class);

            roleBenefitsDao = DaoManager.createDao(connectionSource, RoleBenefits.class);
            TableUtils.createTableIfNotExists(connectionSource, RoleBenefits.class);

            roleCategoriesDao = DaoManager.createDao(connectionSource, RoleCategories.class);
            TableUtils.createTableIfNotExists(connectionSource, RoleCategories.class);

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

    public static Dao<Employees, Integer> getEmployeeDao() {

        if (employeeDao == null) {
            try {
                employeeDao = DaoManager.createDao(connectionSource, Employees.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeDao;
    }

    public static Dao<Addresses, Integer> getAddressDao() {

        if (addressDao == null) {
            try {
                addressDao = DaoManager.createDao(connectionSource, Addresses.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return addressDao;
    }

    public static Dao<EmployeeRoles, Integer> getEmployeeRoleDao() {

        if (employeeRoleDao == null) {
            try {
                employeeRoleDao = DaoManager.createDao(connectionSource, EmployeeRoles.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeRoleDao;
    }

    public static Dao<EmployeesRoles, Integer> getEmployeeRolesDao() {

        if (employeeRolesDao == null) {
            try {
                employeeRolesDao = DaoManager.createDao(connectionSource, EmployeesRoles.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeRolesDao;
    }

    public static Dao<EmployeesBenefits, Integer> getEmployeeBenefitsDao() {

        if (employeeBenefitsDao == null) {
            try {
                employeeBenefitsDao = DaoManager.createDao(connectionSource, EmployeesBenefits.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeBenefitsDao;
    }

    public static Dao<RoleBenefits, Integer> getRoleBenefitsDao() {

        if (roleBenefitsDao == null) {
            try {
                roleBenefitsDao = DaoManager.createDao(connectionSource, RoleBenefits.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roleBenefitsDao;
    }

    public static Dao<RoleCategories, Integer> getRoleCategoriesDao() {

        if (roleCategoriesDao == null) {
            try {
                roleCategoriesDao = DaoManager.createDao(connectionSource, RoleCategories.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roleCategoriesDao;
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
