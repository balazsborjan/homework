package com.mycompany.EXCEPTIONMAPPER;

import com.mycompany.EXCEPTION.IllegalValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalValidationExceptionMapper implements ExceptionMapper<IllegalValidationException>{

    @Inject
    private Logger logger;
    
    @Override
    public Response toResponse(IllegalValidationException exception) {
        logger.log(Level.SEVERE, "Validation exception", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
