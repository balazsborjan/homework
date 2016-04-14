package com.mycompany.testDTO;

import com.mycompany.DTO.UserDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestUserDTO {

    @Test
    public void userNameNotNull(){
        UserDTO user = new UserDTO(null, "ASDasd123", "Balázs", "Borján");
        
        notZeroViolations(user, null);
    }
    
    @Test
    public void userNameShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void userNameSizeMinThree(){
        String usrnm = "ba";
        
        UserDTO user = new UserDTO(usrnm, "ASDasd123", "Balázs", "Borján");
        
        notZeroViolations(user, usrnm);
    }
    
    @Test
    public void userNameSizeShouldMinThree(){
        UserDTO user = new UserDTO("bal", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordNotNull(){
        UserDTO user = new UserDTO("balazsborjan", null, "Balázs", "Borján");
        
        notZeroViolations(user, null);
    }
    
    @Test
    public void passwordShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordMatxhPattern(){
        String pwd = "ASD123";
        UserDTO user = new UserDTO("balazsborjan", pwd, "Balázs", "Borján");
        
        notZeroViolations(user, pwd);
    }
    
    @Test
    public void passwordShouldMatchPattern(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordSizeMinSix(){
        String pwd = "Pwd1";
        UserDTO user = new UserDTO("balazsborjan", pwd, "Balázs", "Borján");
        
        notZeroViolations(user, pwd);
    }
    
    @Test
    public void passwordSizeShouldMinSix(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void dateOfBirthMatchIsValidDateOfBirth(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.of(2016, 01, 01));
        //registrationDay a konstruktorban LocalDate.now() értéket kap!
        
        zeroViolations(user);
    }
    
    @Test
    public void dateOfBirthShouldMatchIsValidDateOfBirth(){
        String dateOfBirth = "2020-01-01";
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.parse(dateOfBirth));
        
        notZeroViolations(user, user);
    }
    
    private static void zeroViolations(UserDTO user){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(user));
        Assert.assertEquals(0, violations.size());
    }
    
    private static void notZeroViolations(UserDTO user, Object wrongParam){        
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(user));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(wrongParam,violations.get(0).getInvalidValue());
    }
}
