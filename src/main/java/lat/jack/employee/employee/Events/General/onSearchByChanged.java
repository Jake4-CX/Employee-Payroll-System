package lat.jack.employee.employee.Events.General;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lat.jack.employee.employee.Controllers.GeneralView;

import java.util.Arrays;

public class onSearchByChanged implements ChangeListener<String> {

    private GeneralView generalView;

    public onSearchByChanged(GeneralView generalView) {
        this.generalView = generalView;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println("SearchBy changed to: '" + newValue + "'");
        System.out.println("Old value: " + oldValue);

        if (Arrays.asList("ID").contains(newValue)) {
            // Number - Binary search
            System.out.println("is number");
            generalView.comboBoxSearchAlgorithm.getItems().setAll("Binary Search", "Linear Search");
            generalView.comboBoxSearchAlgorithm.getSelectionModel().selectFirst();
        } else {
            // String - Linear search
            generalView.comboBoxSearchAlgorithm.getItems().setAll("Linear Search");
            generalView.comboBoxSearchAlgorithm.getSelectionModel().selectFirst();
        }

    }
}
