package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
        generalView.updateEmployeeTable(bucketSort(employeesList));
    }

    public static List<Employees> bucketSort(List<Employees> employeesList) {
        int maxId = getMaxId(employeesList);
        int bucketCount = (int) Math.sqrt(employeesList.size());

        Map<Integer, List<Employees>> buckets = new HashMap<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.put(i, new ArrayList<>());
        }

        for (Employees employee : employeesList) {
            int bucketIndex = (int) Math.floor((double) employee.getId() * bucketCount / (maxId + 1));
            buckets.get(bucketIndex).add(employee);
        }

        List<Employees> sortedEmployees = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            List<Employees> bucket = buckets.get(i);
            bucket.sort(Comparator.comparingInt(Employees::getId));
            sortedEmployees.addAll(bucket);
        }

        return sortedEmployees;
    }

    private static int getMaxId(List<Employees> employeesList) {
        int maxId = 0;
        for (Employees employee : employeesList) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId;
    }
}
