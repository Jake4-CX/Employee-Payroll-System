package lat.jack.employee.employee.Events.General;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.GeneralView;
import lat.jack.employee.employee.Entities.PSInfo;
import lat.jack.employee.employee.Main;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.io.IOException;
import java.util.Objects;

public class onGeneratePayslipButtonClick implements EventHandler<MouseEvent> {

    private GeneralView generalView;

    public onGeneratePayslipButtonClick(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void handle(MouseEvent event) {

        System.out.println("Generate payslip button clicked!");

        ApplicationManager.setPSInfo(new PSInfo(
                Integer.parseInt(generalView.inputDaysWorkedValue.getText()),
                Integer.parseInt(generalView.inputOvertimeHoursValue.getText())
        ));

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PayslipView.fxml")));
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setTitle("Payslip");
            stage.setResizable(false);

            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Apparently opened the new window!");

    }
}
