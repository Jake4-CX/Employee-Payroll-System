package lat.jack.employee.employee.Events.Register;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.RegisterView;
import lat.jack.employee.employee.Managers.ApplicationManager;

public class onBackButtonClick implements EventHandler<MouseEvent> {

    private RegisterView registerView;

    public onBackButtonClick(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("Back button clicked!");
        ApplicationManager.switchScene("LoginView.fxml", (Node) mouseEvent.getSource());
    }
}
