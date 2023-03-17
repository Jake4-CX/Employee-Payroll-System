package lat.jack.employee.employee.Models;

public class User {

    protected int ID;
    protected String userName;
    protected String userPassword;

    public User(int ID, String userName, String userPassword) {
        this.ID = ID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }
}
