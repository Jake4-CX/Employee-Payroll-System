package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Algorithms.EmployeeSort;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Entities.Employees;

import java.util.*;

public class onSortAscendButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onSortAscendButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Sort Ascend button clicked!");

        List<Employees> employeesList = generalView.getAllEmployees();
        generalView.updateEmployeeTable(EmployeeSort.bucketSort(employeesList));
    }

}
