package lat.jack.employee.employee.Entities;

public class PSInfo {

    private int daysWorked;
    private int overtime;

    public PSInfo(int daysWorked, int overtime) {
        this.daysWorked = daysWorked;
        this.overtime = overtime;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }
}
