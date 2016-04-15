package com.mycompany.dto;

import java.time.LocalDate;
import org.junit.Test;

public class UserDTOTest {
    
    @Test
    public void userNameNotNull(){
        UserDTO user = new UserDTO(null, "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.onlyOneViolation(user, user.getUserName());
    }
    
    @Test
    public void userNameShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void userNameSizeMinThree(){
        String usrnm = "ba";
        
        UserDTO user = new UserDTO(usrnm, "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.onlyOneViolation(user, usrnm);
    }
    
    @Test
    public void userNameSizeShouldMinThree(){
        UserDTO user = new UserDTO("bal", "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void passwordNotNull(){
        UserDTO user = new UserDTO("balazsborjan", null, "Balázs", "Borján");
        
        ValidationTestHelper.onlyOneViolation(user, user.getPassword());
    }
    
    @Test
    public void passwordShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void passwordMatchPattern(){
        String pwd = "ASD123";
        UserDTO user = new UserDTO("balazsborjan", pwd, "Balázs", "Borján");
        
        ValidationTestHelper.onlyOneViolation(user, pwd);
    }
    
    @Test
    public void passwordShouldMatchPattern(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void passwordSizeMinSix(){
        String pwd = "Pwd1";
        UserDTO user = new UserDTO("balazsborjan", pwd, "Balázs", "Borján");
        
        ValidationTestHelper.onlyOneViolation(user, pwd);
    }
    
    @Test
    public void passwordSizeShouldMinSix(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján");
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void dateOfBirthMatchIsValidDateOfBirth(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.of(2016, 01, 01));
        
        ValidationTestHelper.zeroViolations(user);
    }
    
    @Test
    public void dateOfBirthShouldMatchIsValidDateOfBirth(){
        String dateOfBirth = "2020-01-01";
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.parse(dateOfBirth));
        
        ValidationTestHelper.onlyOneViolation(user, user);
    }
}
