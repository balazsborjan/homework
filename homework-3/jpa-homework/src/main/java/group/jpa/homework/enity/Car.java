package group.jpa.homework.enity;

import group.jpa.homework.CarType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "findCarWithPlateParam", query = "Select c from Car c where c.licensePlate.licensePlate = :plate"),
    @NamedQuery(name = "listCarsPurchasedLessThenParameterAgoById", query = "Select c.id from Car c where c.dateOfPurchased > :date"),
    @NamedQuery(name = "countCarsByType", query = "select Count(c) from Car c where c.type = :type"),
    @NamedQuery(name = "groupByCarType", query = "select c.type from Car c group by c.type having count(c) > 3"),
    @NamedQuery(name = "getSumPrice", query = "select c.type, Sum(c.price) from Car c group by c.type")
})
public class Car implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "license_plate_fk")
    private LicensePlate licensePlate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private CarType type;
    
    private Integer numberOfPassenger;
    
    private Integer price;
    
    @Column(name = "purchased_date")
    @Temporal(TemporalType.DATE)
    private Date dateOfPurchased;
    
    @ElementCollection
    @CollectionTable(name = "colors")
    @Column(name = "available_colors")
    private List<String> availableColors;

    public Car() {
        availableColors = new ArrayList<>();
    }

    public Car(CarType type, Integer numberOfPassenger, Integer price, Date dateOfPurchased, LicensePlate licensePlate) {
        this.type = type;
        this.numberOfPassenger = numberOfPassenger;
        this.price = price;
        this.dateOfPurchased = dateOfPurchased;
        this.licensePlate = licensePlate;
        availableColors = new ArrayList<>();
    }
    
    public Car(CarType type, Integer numberOfPassenger, Integer price, Date dateOfPurchased) {
        this.type = type;
        this.numberOfPassenger = numberOfPassenger;
        this.price = price;
        this.dateOfPurchased = dateOfPurchased;
        availableColors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Integer getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(Integer numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Date getDateOfPurchased() {
        return dateOfPurchased;
    }

    public void setDateOfPurchased(Date dateOfPurchased) {
        this.dateOfPurchased = dateOfPurchased;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<String> availableColors) {
        this.availableColors = availableColors;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", type=" + type + ", licensePlate=" + licensePlate + ", numberOfPassenger=" + numberOfPassenger + ", price=" + price + ", dateOfPurchased=" + dateOfPurchased + '}';
    }
}
