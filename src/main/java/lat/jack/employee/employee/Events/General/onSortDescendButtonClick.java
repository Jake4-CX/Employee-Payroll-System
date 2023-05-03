package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Entities.Employees;

import java.util.List;

public class onSortDescendButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onSortDescendButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Sort Descend button clicked!");

        List<Employees> employeesList = generalView.getAllEmployees();
        generalView.updateEmployeeTable(bubbleSortDescending(employeesList));

    }

    public static List<Employees> bubbleSortDescending(List<Employees> employeesList) {
        int n = employeesList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (employeesList.get(j).getId() < employeesList.get(j + 1).getId()) {

                    Employees temp = employeesList.get(j);
                    employeesList.set(j, employeesList.get(j + 1));
                    employeesList.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return employeesList;
    }
}
