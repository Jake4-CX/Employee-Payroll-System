package lat.jack.employee.employee.Managers;

import lat.jack.employee.employee.Database.Constructs.Addresses;
import lat.jack.employee.employee.Database.Constructs.Employee;
import lat.jack.employee.employee.Database.Database;

public class ApplicationManager {

    private Database database;

    public ApplicationManager() {

        this.database = new Database();

        // Create tables if not exists
        new Employee();
        new Addresses();
        System.out.println("ApplicationManager initialized!");
    }
}
