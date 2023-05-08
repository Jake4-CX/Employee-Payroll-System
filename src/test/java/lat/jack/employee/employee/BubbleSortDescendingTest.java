package lat.jack.employee.employee;

import lat.jack.employee.employee.Algorithms.EmployeeSort;
import lat.jack.employee.employee.Entities.Addresses;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.EmployeesBenefits;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortDescendingTest {

    @Test
    public void testBubbleSortDescending() {

        Addresses address = new Addresses();
        EmployeeRoles employeeRole = new EmployeeRoles();
        EmployeesBenefits employeeBenefits = new EmployeesBenefits();

        Date hireDate = new Date();

        Employees employee1 = new Employees("John", "Doe", "john@example.com", "0734567890", hireDate, address, employeeRole, employeeBenefits);
        Employees employee2 = new Employees("Adam", "Doe", "adam@example.com", "0737654321", hireDate, address, employeeRole, employeeBenefits);
        Employees employee3 = new Employees("James", "Doe", "james@example.com", "07429384756", hireDate, address, employeeRole, employeeBenefits);
        Employees employee4 = new Employees("Julie", "Doe", "julie@example.com", "0778901234", hireDate, address, employeeRole, employeeBenefits);

        employee1.setId(4);
        employee2.setId(2);
        employee3.setId(1);
        employee4.setId(3);

        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);

        List<Employees> sortedEmployees = EmployeeSort.bubbleSortDescending(employeeList);

        assertEquals(4, sortedEmployees.size());
        assertEquals(4, sortedEmployees.get(0).getId());
        assertEquals(3, sortedEmployees.get(1).getId());
        assertEquals(2, sortedEmployees.get(2).getId());
        assertEquals(1, sortedEmployees.get(3).getId());

    }
}
