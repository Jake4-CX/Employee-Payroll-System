package lat.jack.employee.employee.Managers;

import lat.jack.employee.employee.Database.Constructs.*;
import lat.jack.employee.employee.Database.Database;

public class ApplicationManager {

    private Database database;

    public ApplicationManager() {

        this.database = new Database();

        // Create tables if not exists
        new Employee();
        new Addresses();
        new EmployeeRole();
        new RoleCategory();
        new RoleBenefits();
        new User(); // Used for login - not related to Employees
        System.out.println("ApplicationManager initialized!");
    }
}
