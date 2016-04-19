package group.jpa.homework.enity;

import group.jpa.homework.FactoryAddress;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Factory")
public class CarFactory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "factory_id")
    private Long id;
    
    @NotNull
    @Column(name = "factory_name")
    private String name;
    
    @OneToMany
    @JoinColumn(name = "factory_id")
    private List<Car> cars;
    
    @Embedded
    private FactoryAddress address;

    public CarFactory() {
        this.cars = new ArrayList<>();
    }

    public CarFactory(String name, FactoryAddress address) {
        this.name = name;
        this.cars = new ArrayList<>();
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public FactoryAddress getAddress() {
        return address;
    }

    public void setAddress(FactoryAddress address) {
        this.address = address;
    }
}
