package com.mycompany.EXCEPTIONMAPPER;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable>{

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(Throwable exception) {
        logger.log(Level.SEVERE, "General exception", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause()))
                .type(MediaType.APPLICATION_JSON).build();
    }
}
