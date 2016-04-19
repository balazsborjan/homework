package group.jpa.homework.enity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LicensePlate implements Serializable {

    @Id
    private String licensePlate;
    
    @OneToOne(mappedBy = "licensePlate")
    private Car car;

    public LicensePlate() {
    }

    public LicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "LicensePlate{" + "licensePlate=" + licensePlate + '}';
    }
}
