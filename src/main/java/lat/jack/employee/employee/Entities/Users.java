package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class Users {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String userName;

    @DatabaseField()
    private String userPassword;

    public Users() {
    }

    public Users(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
