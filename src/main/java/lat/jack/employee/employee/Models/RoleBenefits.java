package lat.jack.employee.employee.Models;

public class RoleBenefits {

    protected int ID;
    protected Float housingAllowance;
    protected Float transportAllowance;
    protected Float healthAllowance;

    public RoleBenefits(int ID, Float housingAllowance, Float transportAllowance, Float healthAllowance) {
        this.ID = ID;
        this.housingAllowance = housingAllowance;
        this.transportAllowance = transportAllowance;
        this.healthAllowance = healthAllowance;
    }

    public int getID() {
        return ID;
    }

    public Float getHousingAllowance() {
        return housingAllowance;
    }

    public void setHousingAllowance(Float housingAllowance) {
        this.housingAllowance = housingAllowance;
    }

    public Float getTransportAllowance() {
        return transportAllowance;
    }

    public void setTransportAllowance(Float transportAllowance) {
        this.transportAllowance = transportAllowance;
    }

    public Float getHealthAllowance() {
        return healthAllowance;
    }

    public void setHealthAllowance(Float healthAllowance) {
        this.healthAllowance = healthAllowance;
    }
}
