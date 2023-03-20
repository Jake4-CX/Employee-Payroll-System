package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class RoleBenefits {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private Float housingAllowance;

    @DatabaseField()
    private Float travelingAllowance;

    @DatabaseField()
    private Float healthAllowance;

    public RoleBenefits(Float housingAllowance, Float travelingAllowance, Float healthAllowance) {
        this.housingAllowance = housingAllowance;
        this.travelingAllowance = travelingAllowance;
        this.healthAllowance = healthAllowance;
    }

    public RoleBenefits() {
    }

    public int getId() {
        return id;
    }

    public Float getHousingAllowance() {
        return housingAllowance;
    }

    public void setHousingAllowance(Float housingAllowance) {
        this.housingAllowance = housingAllowance;
    }

    public Float getTravelingAllowance() {
        return travelingAllowance;
    }

    public void setTravelingAllowance(Float travelingAllowance) {
        this.travelingAllowance = travelingAllowance;
    }

    public Float getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(Float healthAllowance) {
        this.healthAllowance = healthAllowance;
    }
}
