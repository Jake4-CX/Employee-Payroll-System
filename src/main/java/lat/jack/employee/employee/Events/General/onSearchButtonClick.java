package lat.jack.employee.employee.Events.General;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Algorithms.EmployeeSearch;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Entities.RoleCategories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class onSearchButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onSearchButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Search button clicked!");

        String searchBy = generalView.comboBoxSearchBy.getSelectionModel().getSelectedItem().toString();
        String searchAlgorithm = generalView.comboBoxSearchAlgorithm.getSelectionModel().getSelectedItem().toString();
        String searchValue = generalView.inputSearchValue.getText();

        if (!(searchBy.equals("ID"))) {
            // alert - only ID search is supported
            alertSearchNotSupported();
            return;
        }

        if (searchValue.isBlank() || searchValue.isEmpty()) {
            // Empty search value = return all employees
            generalView.updateEmployeeTable(generalView.getAllEmployees());
            return;
        }

        if (!searchValue.matches("\\d+")) {
            alertSearchFieldEmpty();
            return;
        }

        System.out.println("Searching by: " + searchBy + " with value: " + searchValue + " using " + searchAlgorithm + " algorithm");

        Dao<Employees, Integer> employeeDao = Database.getEmployeeDao();
        List<Employees> employees;

        try {
            employees = employeeDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int found = -1;

        System.out.println("Employee IDs: " + Arrays.toString(getEmployeeIds(employees)));
        System.out.println("Searching for: " + Integer.parseInt(searchValue));
        System.out.println("Found current val: " + found);

        switch (searchAlgorithm) {
            case "Binary Search":
                System.out.println("Searching by binary search");
                found = EmployeeSearch.binarySearch(getEmployeeIds(employees), Integer.parseInt(searchValue));
                break;
            case "Linear Search":
                System.out.println("Searching by linear search");
                found = EmployeeSearch.linearSearch(getEmployeeIds(employees), Integer.parseInt(searchValue));
                break;
        }

        System.out.println("Found updated val: " + found);

        if (found >= 0) {
            Employees foundEmployee = employees.get(found);
            System.out.println("Found employee: " + foundEmployee.getFirstName() + " " + foundEmployee.getLastName());
            generalView.updateEmployeeTable(List.of(foundEmployee));

        } else {
            System.out.println("No employee found!");
            alertNoEmployeeFound();
        }


    }

    private void alertNoEmployeeFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("No employee found!");

        alert.showAndWait();
    }

    private int[] getEmployeeIds(List<Employees> employees) {
        return employees.stream()
                .map(Employees::getId)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void alertSearchFieldEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a value to search for!");

        alert.showAndWait();
    }

    private void alertSearchNotSupported() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You can only search by ID");

        alert.showAndWait();
    }
}
