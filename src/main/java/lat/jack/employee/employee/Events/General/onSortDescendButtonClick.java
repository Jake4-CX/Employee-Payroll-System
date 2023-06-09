package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Algorithms.EmployeeSort;
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
        generalView.updateEmployeeTable(EmployeeSort.quickSortDescending(employeesList));

    }

}
