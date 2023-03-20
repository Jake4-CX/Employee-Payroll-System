package lat.jack.employee.employee.Controllers.RoleCategory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lat.jack.employee.employee.Controllers.AddRoleCategoryView;
import lat.jack.employee.employee.Database.Database;
import lat.jack.employee.employee.Entities.RoleCategories;
import lat.jack.employee.employee.Models.RoleCategory;

import java.sql.SQLException;

public class onAddCategoryButtonClick implements EventHandler<MouseEvent> {

    private AddRoleCategoryView addRoleCategoryView;
    private Scene scene;

    public onAddCategoryButtonClick(AddRoleCategoryView addRoleCategoryView) {
        this.addRoleCategoryView = addRoleCategoryView;
    }

    @Override
    public void handle(MouseEvent event) {
        this.scene = ((Node) event.getSource()).getScene();

        System.out.println("Add category button clicked!");

        String categoryName = addRoleCategoryView.getInputCategoryName().getText();

        System.out.println("Category name: " + categoryName);

        if (categoryName.isEmpty() || categoryName.isBlank()) {
            System.out.println("Category name is empty!");
            inputEmpty();
            return;
        }

        System.out.println("Category name is not empty!");

        Dao<RoleCategories, Integer> roleCategoriesDao = Database.getRoleCategoriesDao();

        try {
            QueryBuilder<RoleCategories, Integer> queryBuilder = roleCategoriesDao.queryBuilder();
            PreparedQuery<RoleCategories> preparedQuery = queryBuilder.where().eq("categoryName", categoryName).prepare();
            RoleCategories roleCategories = roleCategoriesDao.queryForFirst(preparedQuery);

            if (roleCategories != null) {
                System.out.println("Category already exists!");
                categoryAlreadyExists();
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            int response = roleCategoriesDao.create(new RoleCategories(
                    categoryName
            ));

            if (response == 1) {
                System.out.println("Category created successfully!");
                categoryCreated();

            } else {
                System.out.println("Category not created!");
                categoryNotCreated();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void categoryAlreadyExists() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Category already exists");
        alert.setHeaderText("Category already exists!");
        alert.setContentText("Category already exists!");
        alert.showAndWait();

        alert.onCloseRequestProperty().setValue((e) -> {
            System.out.println("Closing the alert!");
            System.out.println(((Stage) this.scene.getWindow()).getOwner());
            ((Stage) this.scene.getWindow()).close();
        });
    }

    public void categoryCreated() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Category created");
        alert.setHeaderText("Category created successfully!");
        alert.setContentText("Category created successfully!");
        alert.showAndWait();

        alert.onCloseRequestProperty().setValue((e) -> {
            System.out.println("Closing the alert!");
            ((Stage) this.scene.getWindow()).close();
        });
    }

    public void categoryNotCreated() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Category not created");
        alert.setHeaderText("Failed to create category!");
        alert.setContentText("Failed to create category!");
        alert.showAndWait();

        alert.onCloseRequestProperty().setValue((e) -> {
            System.out.println("Closing the alert!");
            ((Stage) this.scene.getWindow()).close();
        });
    }

    public void inputEmpty() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input empty");
        alert.setHeaderText("Category name is empty!");
        alert.setContentText("Category name is empty!");
        alert.showAndWait();

        alert.onCloseRequestProperty().setValue((e) -> {
            System.out.println("Closing the alert!");
            ((Stage) this.scene.getWindow()).close();
        });
    }
}
