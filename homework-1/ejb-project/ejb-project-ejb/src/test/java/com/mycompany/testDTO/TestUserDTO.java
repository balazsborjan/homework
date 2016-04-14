package com.mycompany.testDTO;

import com.mycompany.DTO.UserDTO;
import java.time.LocalDate;
import java.util.Set;
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
        
        notZeroViolations(user);
    }
    
    @Test
    public void userNameShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void userNameSizeMinThree(){
        UserDTO user = new UserDTO("ba", "ASDasd123", "Balázs", "Borján");
        
        notZeroViolations(user);
    }
    
    @Test
    public void userNameSizeShouldMinThree(){
        UserDTO user = new UserDTO("bal", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordNotNullAndNotMachPattern(){
        UserDTO user = new UserDTO("balazsborjan", null, "Balázs", "Borján");
        
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(2, violations.size());
        //A regexes pattern vizsgálat miatt nem 1, hanem 2 hibát észlel a validátor, ezért ezt a 2 esetet egyben vizsgáltatom
    }
    
    @Test
    public void passwordShouldNotNull(){
        UserDTO user = new UserDTO("balazsborjan", "ASDasd123", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordSizeMinSix(){
        UserDTO user = new UserDTO("balazsborjan", "Pwd1", "Balázs", "Borján");
        
        notZeroViolations(user);
    }
    
    @Test
    public void passwordSizeShouldMinSix(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordMatchIsValidPasswordvalidation(){
        UserDTO user = new UserDTO("balazsborjan", "Password1", "Balázs", "Borján");
        
        zeroViolations(user);
    }
    
    @Test
    public void passwordShouldMatchIsValidPasswordValidation(){
        UserDTO user = new UserDTO("balazsborjan", "PASSWORD1", "Balázs", "Borján");
        
        notZeroViolations(user);
    }
    
    @Test
    public void dateOfBirthMatchIsValidDateOfBirth(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.of(2016, 01, 01));
        //registrationDay a konstruktorban LocalDate.now() értéket kap!
        
        zeroViolations(user);
    }
    
    @Test
    public void dateOfBirthShouldMatchIsValidDateOfBirth(){
        UserDTO user = new UserDTO("balazsborjan", "Paswd1", "Balázs", "Borján", LocalDate.of(2030, 01, 01));
        
        notZeroViolations(user);
    }
    
    private static void zeroViolations(UserDTO user){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
    
    private static void notZeroViolations(UserDTO user){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
    }
}
