package lat.jack.employee.employee.Events.EditEmployees;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.EditEmployeeView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.Addresses;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.Employees;
import lat.jack.employee.employee.Managers.ApplicationManager;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

public class onUpdateEmployeeButtonClick implements EventHandler<MouseEvent> {

    private EditEmployeeView editEmployeeView;

    public onUpdateEmployeeButtonClick(EditEmployeeView editEmployeeView) {
        this.editEmployeeView = editEmployeeView;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Update Employee button clicked!");

        Employees selectedEmployee = ApplicationManager.getSelectedEmployee();

        // Get EmployeeRole
        Dao<EmployeeRoles, Integer> employeeRolesDao = Database.getEmployeeRoleDao();
        EmployeeRoles employeeRoles = null;

        String employeeRoleName = editEmployeeView.inputEmployeeRole.getValue();

        try {
            employeeRoles = employeeRolesDao.queryForFirst(employeeRolesDao.queryBuilder().where().eq("roleName", employeeRoleName).prepare());

            if (employeeRoles == null) {
                System.out.println("Employee role not found!");
                roleNotFound();
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (employeeRoles == null) {
            System.out.println("Employee role not found!");
            roleNotFound();
            return;
        }

        selectedEmployee.setFirstName(editEmployeeView.inputFirstName.getText());
        selectedEmployee.setLastName(editEmployeeView.inputLastName.getText());
        selectedEmployee.setEmailAddress(editEmployeeView.inputEmailAddress.getText());
        selectedEmployee.setPhoneNumber(editEmployeeView.inputPhoneNumber.getText());
        selectedEmployee.setHireDate(Date.from(editEmployeeView.inputHireDate.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        selectedEmployee.setEmployeeRole(employeeRoles);

        Addresses address = selectedEmployee.getAddress();
        address.setStreetName(editEmployeeView.inputStreetName.getText());
        address.setAddressRegion(editEmployeeView.inputStreetRegion.getText());
        address.setAddressCity(editEmployeeView.inputStreetCity.getText());
        address.setPostalCode(editEmployeeView.inputStreetPostCode.getText());
        address.setCountry(editEmployeeView.inputStreetCountry.getValue());

        selectedEmployee.setAddress(address);

        Dao<Employees, Integer> employeesDao = Database.getEmployeeDao();
        Dao<Addresses, Integer> addressesDao = Database.getAddressDao();

        try {
            int result = employeesDao.update(selectedEmployee);
            int addressResult = addressesDao.update(address);

            if (result == 1 && addressResult == 1) {
                System.out.println("Employee updated!");
                alertEmployeeUpdated();
                return;
            } else {
                System.out.println("Employee not updated!");
                alertEmployeeFailedUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        alertEmployeeFailedUpdate();
    }

    private void alertEmployeeFailedUpdate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Employee not updated!");
        alert.setContentText("Employee not updated!");
        alert.showAndWait();
    }

    private void alertEmployeeUpdated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Employee updated!");
        alert.setContentText("Employee updated!");
        alert.showAndWait();
    }

    private void roleNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Employee Role not found!");
        alert.setContentText("Employee Role not found!");
        alert.showAndWait();
    }
}
