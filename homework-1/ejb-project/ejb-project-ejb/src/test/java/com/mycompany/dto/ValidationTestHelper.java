package com.mycompany.dto;

import com.mycompany.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;

public class ValidationTestHelper {
    
    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = vf.getValidator();
        
    public static void zeroViolations(Object DTOparam){
        vf.close();
        
        List<ConstraintViolation<Object>> violations = new ArrayList(validator.validate(DTOparam));
            Assert.assertEquals(0, violations.size());
    }
    public static void onlyOneViolation(Object DTOparam, Object wrongParam){
        vf.close();
        
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validator.validate(DTOparam));
            Assert.assertEquals(1, violations.size());
            Assert.assertEquals(wrongParam,violations.get(0).getInvalidValue());
    }
}
