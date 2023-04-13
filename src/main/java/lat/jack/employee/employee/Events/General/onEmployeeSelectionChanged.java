package lat.jack.employee.employee.Events.General;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class onEmployeeSelectionChanged implements ChangeListener<Employees> {

    private GeneralView generalView;

    public onEmployeeSelectionChanged(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void changed(ObservableValue<? extends Employees> observable, Employees oldValue, Employees newValue) {
        if (newValue != null) {
            System.out.println("Selected: " + newValue.getFirstName() + " " + newValue.getLastName());
            ApplicationManager.setSelectedEmployee(newValue);
            generalView.groupSelectedEmployee.setVisible(true);

            generalView.outputSelectedIDLabel.setText(String.valueOf(newValue.getId()));
            generalView.outputSelectedNameLabel.setText(newValue.getFirstName() + " " + newValue.getLastName());
            generalView.outputSelectedCategoryLabel.setText(newValue.getEmployeeRole().getRoleCategory().getCategoryName());
            generalView.outputSelectedRoleLabel.setText(newValue.getEmployeeRole().getRoleName());
        }

    }
}
