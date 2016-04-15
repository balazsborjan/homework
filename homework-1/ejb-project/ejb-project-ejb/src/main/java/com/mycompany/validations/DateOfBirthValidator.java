package com.mycompany.validations;

import com.mycompany.dto.UserDTO;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<IsValidDateOfBirth, UserDTO> {

    @Override
    public void initialize(IsValidDateOfBirth constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        if (null != value.getDateOfBirth()) {
            return IsInThePast(value) && IsBeforeRegistrationDate(value);
        }
        
        return true;
    }
    
    private boolean IsBeforeRegistrationDate(UserDTO user){
        return user.getDateOfBirth().isBefore( user.getRegistrationDate());
    }
    
    private boolean IsInThePast(UserDTO user){
        return user.getDateOfBirth().isBefore(LocalDate.now());
    }
}
