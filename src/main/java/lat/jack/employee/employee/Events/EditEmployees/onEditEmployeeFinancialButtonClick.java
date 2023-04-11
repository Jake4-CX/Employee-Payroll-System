package lat.jack.employee.employee.Events.EditEmployees;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.EditEmployeeView;

public class onEditEmployeeFinancialButtonClick implements EventHandler<MouseEvent> {

    private EditEmployeeView editEmployeeView;

    public onEditEmployeeFinancialButtonClick(EditEmployeeView editEmployeeView) {
        this.editEmployeeView = editEmployeeView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Edit Employee Financial button clicked!");

    }
}
