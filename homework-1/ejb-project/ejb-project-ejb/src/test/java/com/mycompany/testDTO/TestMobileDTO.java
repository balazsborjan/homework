package com.mycompany.testDTO;

import com.mycompany.DTO.MobileDTO;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestMobileDTO {

    @Test
    public void idNotNull(){
        
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void idShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void typeNotNull(){
        MobileDTO mobile = new MobileDTO(null, "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void typeShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void typeSizeMinThree(){
        MobileDTO mobile = new MobileDTO("tp", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void typeSizeShouldMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void manufactNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", null, 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void manufactShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void manufactSizeMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "A", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void manufactSizeShouldMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void priceMinOne(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 0, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void priceShouldMinOne(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    @Test
    public void pieceMinZero(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, -1);
        mobile.setId(UUID.randomUUID().toString());
        
        notZeroViolations(mobile);
    }
    
    @Test
    public void pieceShouldMinZero(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 0);
        mobile.setId(UUID.randomUUID().toString());
        
        zeroViolations(mobile);
    }
    
    private static void zeroViolations(MobileDTO mobile){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }
    
    private static void notZeroViolations(MobileDTO mobile){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        vf.close();
        
        Set<ConstraintViolation<MobileDTO>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
    }
}
