package com.mycompany.interceptors;

import com.mycompany.exceptions.IllegalValidationException;
import com.mycompany.annotation.ValidatorQualifier;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject @ValidatorQualifier
    private Validator validator;
    
    @AroundInvoke
    public Object userValidation(InvocationContext ic) throws Exception{
        Object[] objects = ic.getParameters();
        
        for (Object param : objects) {
            if (param.getClass().isAnnotationPresent(ValidateDTO.class)) {
                validateParam(param);
            }
        }
        
        return ic.proceed();
    }
    
    private void validateParam(Object param){
        String errorMessage;
        Set<ConstraintViolation<Object>> violations = validator.validate(param);
        if (!violations.isEmpty()) {
            errorMessage = violations.stream().map(v -> "Error in Validation! Message: " 
                    + v.getMessage() + "at: "
                    + v.getPropertyPath().toString()).reduce(String::concat).toString();
                    
            throw new IllegalValidationException(errorMessage);
        }
    }
}
