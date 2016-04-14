package com.mycompany.EXCEPTIONMAPPER;

import com.mycompany.EXCEPTION.IllegalRestRequestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalRestRequestExceptionMapper implements ExceptionMapper<IllegalRestRequestException>{

    @Inject
    private Logger logger;
    
    @Override
    public Response toResponse(IllegalRestRequestException exception) {
        logger.log(Level.SEVERE, "Illegal request", exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
