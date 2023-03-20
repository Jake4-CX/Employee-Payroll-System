package lat.jack.employee.employee.Entities;

import com.j256.ormlite.field.DatabaseField;

public class Addresses {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String streetName;

    @DatabaseField()
    private String addressRegion;

    @DatabaseField()
    private String addressCity;

    @DatabaseField()
    private String postalCode;

    @DatabaseField()
    private String country;

    public Addresses(String streetName, String addressRegion, String addressCity, String postalCode, String country) {
        this.streetName = streetName;
        this.addressRegion = addressRegion;
        this.addressCity = addressCity;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Addresses() {
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
