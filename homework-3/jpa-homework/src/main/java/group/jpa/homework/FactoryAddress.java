package group.jpa.homework;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class FactoryAddress implements Serializable {

    private Integer number;
    private String street;
    private String city;
    private String country;

    public FactoryAddress() {
    }

    public FactoryAddress(Integer number, String street, String city, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
