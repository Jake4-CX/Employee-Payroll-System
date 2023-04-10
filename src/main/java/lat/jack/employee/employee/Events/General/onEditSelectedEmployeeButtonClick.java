package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.GeneralView;

public class onEditSelectedEmployeeButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onEditSelectedEmployeeButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Edit selected employee button clicked!");


    }
}
