package com.mycompany.VALIDATION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<IsValidPassword, String> {

    private final Pattern passwordPattern = Pattern.compile("((([A-Z]+)([a-z]+)([A-Z]*)([a-z]*))(([1-9]+)|([=+<>.,]+)))|((([a-z]+)([A-Z]+)([a-z]*)([A-Z]*))(([1-9]+)|([=+<>.,]+)))");
    
    @Override
    public void initialize(IsValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        
        Matcher matcher = passwordPattern.matcher(value);
        
        return matcher.matches();
    }
}
