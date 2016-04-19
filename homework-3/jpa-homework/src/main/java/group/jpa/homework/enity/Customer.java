package group.jpa.homework.enity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Customer extends People {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "people_id")
    @OrderBy(value = "dateOfPurchased DESC")
    private List<Car> cars;

    public Customer() {
        super();
        this.cars = new ArrayList<>();
    }

    public Customer(String name, Integer age) {
        super(name, age);
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
