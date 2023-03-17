module lat.jack.employee.employee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires bcrypt;


    opens lat.jack.employee.employee to javafx.fxml;
    exports lat.jack.employee.employee;
    exports lat.jack.employee.employee.Controllers;
    opens lat.jack.employee.employee.Controllers to javafx.fxml;
}