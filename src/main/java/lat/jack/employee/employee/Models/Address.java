package lat.jack.employee.employee.Models;

public class Address {

    protected int ID;
    protected String streetName;
    protected String addressRegion;
    protected String addressCity;
    protected String postalCode;
    protected String country;

    public Address(int ID, String streetName, String addressRegion, String addressCity, String postalCode, String country) {
        this.ID = ID;
        this.streetName = streetName;
        this.addressRegion = addressRegion;
        this.addressCity = addressCity;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getID() {
        return ID;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
