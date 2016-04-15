package com.mycompany.dto;

import com.mycompany.interceptors.ValidateDTO;
import com.mycompany.validations.IsValidDateOfBirth;
import com.mycompany.dateadapter.LocalDateAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@ValidateDTO
@IsValidDateOfBirth
public class UserDTO {

    @NotNull @Size(min = 3)
    private String userName;    
    @NotNull @Size(min = 6) @Pattern(regexp = "((([A-Z]+)([a-z]+)([A-Z]*)([a-z]*))(([1-9]+)|([=+<>.,]+)))|((([a-z]+)([A-Z]+)([a-z]*)([A-Z]*))(([1-9]+)|([=+<>.,]+)))")
    private String password;
    private String firstname;
    private String lastname;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    @NotNull @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate registrationDate;
    private boolean admin;
    private List<MobileDTO> cart;
    
    public UserDTO(){
        this.cart = new ArrayList<>();
    }

    public UserDTO(String userName, String password, String firstname, String lastname) {
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.registrationDate = LocalDate.now();
        this.admin = (userName != null) && (password != null) && (userName.equals("admin") && password.equals("admin"));
        this.cart = new ArrayList<>();
    }

    public UserDTO(String userName, String password, String firstname, String lastname, LocalDate dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = LocalDate.now();
        this.admin = (userName != null) && (password != null) && (userName.equals("admin") && password.equals("admin"));
        this.cart = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName != null ? userName : null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password != null ? password : null;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname != null ? firstname : null;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname != null ? lastname : null;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth != null ? dateOfBirth : null;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate != null ? registrationDate : null;
    }
    
    public void setRegistrationDate() {
        this.registrationDate = LocalDate.now();
    }
    
    public boolean getAdmin(){
        return (userName != null) && (password != null) && (userName.equals("admin") && password.equals("admin"));
    }

    public void setAdmin() {
        this.admin = (userName != null) && (password != null) && (userName.equals("admin") && password.equals("admin"));
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }
    
    public void setCart() {
        this.cart = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.userName);
        hash = 83 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
}
