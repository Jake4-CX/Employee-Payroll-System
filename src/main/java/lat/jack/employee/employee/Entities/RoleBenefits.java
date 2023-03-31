package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class RoleBenefits {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private double housingAllowance;

    @DatabaseField()
    private double travelingAllowance;

    @DatabaseField()
    private double healthAllowance;

    public RoleBenefits(double housingAllowance, double travelingAllowance, double healthAllowance) {
        this.housingAllowance = housingAllowance;
        this.travelingAllowance = travelingAllowance;
        this.healthAllowance = healthAllowance;
    }

    public RoleBenefits() {
    }

    public int getId() {
        return id;
    }

    public double getHousingAllowance() {
        return housingAllowance;
    }

    public void setHousingAllowance(double housingAllowance) {
        this.housingAllowance = housingAllowance;
    }

    public double getTravelingAllowance() {
        return travelingAllowance;
    }

    public void setTravelingAllowance(double travelingAllowance) {
        this.travelingAllowance = travelingAllowance;
    }

    public double getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(double healthAllowance) {
        this.healthAllowance = healthAllowance;
    }
}
