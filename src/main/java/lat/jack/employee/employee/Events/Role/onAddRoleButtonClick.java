package lat.jack.employee.employee.Events.Role;

import com.j256.ormlite.dao.Dao;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lat.jack.employee.employee.Controllers.AddRoleView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.EmployeeRoles;
import lat.jack.employee.employee.Entities.RoleBenefits;
import lat.jack.employee.employee.Entities.RoleCategories;

public class onAddRoleButtonClick implements EventHandler<MouseEvent> {

    private AddRoleView addRoleView;
    private Scene scene;

    public onAddRoleButtonClick(AddRoleView addRoleView) {
        this.addRoleView = addRoleView;
    }

    @Override
    public void handle(MouseEvent event) {
        this.scene = ((Node) event.getSource()).getScene();

        System.out.println("Add role button clicked!");

        String roleName = addRoleView.getInputRoleName().getText();
        double roleSalary;
        double travelingAllowance;

        try {
            roleSalary = Double.parseDouble(addRoleView.getInputRoleSalary().getText());
            travelingAllowance = Double.parseDouble(addRoleView.getInputTravelingAllowance().getText());
        } catch (NumberFormatException e) {
            System.out.println("Role salary or TravelingAllowance is not a number!");
            invalidSalary();
            return;
        }

        String roleCategory = addRoleView.getInputRoleCategory().getValue().toString();

        System.out.println("Role name: " + roleName);
        System.out.println("Role salary: " + roleSalary);
        System.out.println("Travel allowance: " + travelingAllowance);
        System.out.println("Role category: " + roleCategory);

        if (roleName.isEmpty() || roleName.isBlank()) {
            System.out.println("Role name is empty!");
            inputEmpty();
            return;
        } else if (roleSalary <= 0) {
            System.out.println("Role salary is empty or below 0!");
            invalidSalary();
            return;
        } else if (travelingAllowance <= 0) {
            System.out.println("Traveling allowance is empty or below 0!");
            invalidSalary();
            return;
        } else if (roleCategory.isEmpty() || roleCategory.isBlank()) {
            System.out.println("Role category is empty!");
            inputEmpty();
            return;
        }

        RoleCategories roleCategories = this.addRoleView.getRoleCategoriesHashMap().get(roleCategory);

        System.out.println("Role name, salary and category are not empty!");

        Dao<EmployeeRoles, Integer> employeeRolesDao = Database.getEmployeeRoleDao();

        // Create benefits
        RoleBenefits roleBenefits = new RoleBenefits();
        roleBenefits.setTravelingAllowance(travelingAllowance);
        roleBenefits.setHousingAllowance(roleSalary * 0.05);
        roleBenefits.setHealthAllowance(roleSalary * 0.08);

        Dao<RoleBenefits, Integer> roleBenefitsDao = Database.getRoleBenefitsDao();

        try {
            int response = roleBenefitsDao.create(roleBenefits);

            if (response == 1) {
                System.out.println("Role benefits added successfully!");
            } else {
                System.out.println("Role benefits not added!");
                roleNotAdded();
                return;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            EmployeeRoles employeeRoles = new EmployeeRoles();
            employeeRoles.setRoleName(roleName);
            employeeRoles.setStartingSalary(roleSalary);
            employeeRoles.setRoleCategory(roleCategories);
            employeeRoles.setRoleBenefits(roleBenefits);

            int response = employeeRolesDao.create(employeeRoles);

            if (response == 1) {
                System.out.println("Role added successfully!");
                roleAddedSuccessfully();
            } else {
                System.out.println("Role not added!");
                roleNotAdded();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private void roleNotAdded() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Role not added!");
        alert.setContentText("The role has not been added!");
        alert.showAndWait();
    }

    private void roleAddedSuccessfully() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Role added successfully!");
        alert.setContentText("The role has been added successfully!");
        alert.showAndWait();
    }

    private void inputEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input is empty!");
        alert.setContentText("Please fill in all the fields!");
        alert.showAndWait();
    }

    private void invalidSalary() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid salary!");
        alert.setContentText("Please enter a valid salary that is more than 0!");
        alert.showAndWait();
    }
}
