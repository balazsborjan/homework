package com.mycompany.SERVICE;

import com.mycompany.DTO.UserDTO;
import com.mycompany.EXCEPTION.IllegalRestRequestException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserManagementService {

    private final List<UserDTO> users = new ArrayList<>();
    
    @PostConstruct
    private void init(){
        users.add(new UserDTO("admin", "admin", "Balázs", "Borján"));
        users.add(new UserDTO("user", "user", "Firstname", "Lastname", LocalDate.of(2016, Month.APRIL, 10)));
    }
    
    @Lock(LockType.WRITE)
    public UserDTO addUser(UserDTO newUser){
        for (UserDTO user : users) {
            if (user.getUserName().equals(newUser.getUserName())) {
                throw new IllegalRestRequestException("This username is already used!");
            }
        }
        
        newUser.setAdmin();
        newUser.setRegistrationDate();
        newUser.setCart();
        users.add(newUser);
        return newUser;
    }
    
    @Lock(LockType.WRITE)
    public UserDTO removeUser(String userName, String password){
        UserDTO deletedUser;
        for (UserDTO user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                deletedUser = user;
                users.remove(user);
                return deletedUser;
            }
        }
        
        throw new IllegalRestRequestException("There is no User with this username or password!");
    }
    
    @Lock(LockType.WRITE)
    public UserDTO editUser(String userName, String password, UserDTO editedUser){
        for (UserDTO user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                editedUser.setAdmin();
                editedUser.setCart(user.getCart());
                editedUser.setRegistrationDate(user.getRegistrationDate());
                if (editedUser.getDateOfBirth() == null) {
                    editedUser.setDateOfBirth(user.getDateOfBirth());
                }
                
                users.remove(user);
                users.add(editedUser);
                return editedUser;
            }
        }
        
        throw new IllegalRestRequestException("There is no User with this username or password!");
    }
    
    @Lock(LockType.READ)
    public UserDTO getUser(String userName){
        for (UserDTO user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        
        throw new IllegalRestRequestException("There is no User with this username!");
    }
    
    @Lock(LockType.READ)
    public UserDTO getUser(UserDTO user){
        for (UserDTO u : users) {
            if (u.equals(user)) {
                return user;
            }
        }
        
        throw new IllegalRestRequestException("There is no User with these parameters!");
    }
    
    @Lock(LockType.READ)
    public List<UserDTO> getUsers(){
        return users;
    }
}
