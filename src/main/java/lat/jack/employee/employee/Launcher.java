package lat.jack.employee.employee;

public class Launcher {

    // Launcher class
    // Due to JavaFX 11+ modularization, we need to launch the application
    // from another class as this is the only way to create a runnable
    // fat JAR file that bundles JavaFX.

    public static void main(String[] args) {
        Main.main(args);
    }
}
