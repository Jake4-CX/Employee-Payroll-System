module lat.jack.employee.employee {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens lat.jack.employee.employee to javafx.fxml;
    exports lat.jack.employee.employee;
}