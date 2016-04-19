package group.jpa.homework.enity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class People implements Serializable {

    @Id 
    @GeneratedValue
    @Column(name = "people_id")
    private Long id;
    
    @NotNull
    private String name;
    
    private Integer age;

    public People() {
    }
    
    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
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
}
