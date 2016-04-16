package com.mycompany.async.demo.rest;

import com.mycompany.async.demo.AsyncService;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    public String doSomething(@Context HttpServletRequest request) throws InterruptedException, ExecutionException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3000);
        Future<Integer> counter1 = asyncService.asyncCounter();
        Future<Integer> counter2 = asyncService.asyncCounter();
        counter1.cancel(true);
        Integer int1 = counter1.get();
        Integer int2 = counter2.get();

        return ("(" + int1.toString() + ") - (" + int2.toString() + ")");
    }
}
