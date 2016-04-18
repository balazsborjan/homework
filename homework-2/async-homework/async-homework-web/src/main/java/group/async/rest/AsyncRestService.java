package group.async.rest;

import group.async.homework.AsyncService;
import group.async.homework.dto.AsyncDTO;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/rest")
@Produces(MediaType.TEXT_PLAIN)
@SessionScoped
public class AsyncRestService implements Serializable {

    @Inject
    private AsyncService asyncService;

    @GET
    public Integer getAsyncDTOList(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(100000);
        
        Future<Integer> count = asyncService.getListSize();
        return count.get();
    }
    
    @POST
    @Path("/async1")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToAsyncDTOList(AsyncDTO newDto) throws InterruptedException {
        asyncService.longAsyncMethod(newDto);
    }
    
    @POST
    @Path("/async2")
    public void shortAddToAsyncDTOList() {
        asyncService.shortAsyncMethod();
    }
}
