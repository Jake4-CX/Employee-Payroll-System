module lat.jack.employee.employee {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires bcrypt;
    requires ormlitebuild;
    requires org.xerial.sqlitejdbc;

    opens lat.jack.employee.employee.Entities to ormlitebuild;

    exports lat.jack.employee.employee.DataStructures;
    opens lat.jack.employee.employee to javafx.fxml;
    exports lat.jack.employee.employee;
    exports lat.jack.employee.employee.Controllers;
    opens lat.jack.employee.employee.Controllers to javafx.fxml;
}