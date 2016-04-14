package com.mycompany.REST;

import com.mycompany.DTO.UserDTO;
import com.mycompany.EXCEPTION.IllegalRestRequestException;
import com.mycompany.INTERCEPTORS.ValidatorInterceptor;
import com.mycompany.SERVICE.UserManagementService;
import java.util.List;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/users")
@Produces("application/json")
public class UserRESTService {

    @EJB
    private UserManagementService userManagement;
    
    @GET
    public List<UserDTO> getUsers(){
        return userManagement.getUsers();
    }
    
    @GET
    @Path("/{userName}")
    public UserDTO getUserByUserName(@PathParam("userName") String userName){
        return userManagement.getUser(userName);
    }
    
    @POST
    @Path("/login/{userName}/{password}")
    public UserDTO login(@Context HttpServletRequest request, @PathParam("userName") String userName, @PathParam("password") String password){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3000);
        
        for (UserDTO acceptedUser : userManagement.getUsers()) {
            if (acceptedUser.getUserName().equals(userName) && acceptedUser.getPassword().equals(password)) {
                session.setMaxInactiveInterval(3000);
                session.setAttribute("user", acceptedUser);
                return acceptedUser;
            }
        }
        
        throw new IllegalRestRequestException("Wrong UserName or Password!");
    }
    
    @POST
    @Path("/logout")
    @Produces("text/plain")
    public String logout(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        
        return "User logged out!";
    }
    
    @POST
    @Path("/addUser")
    @Consumes("application/json")
    @Interceptors(ValidatorInterceptor.class)
    public UserDTO addUser(UserDTO newUser){
        return userManagement.addUser(newUser);
    }
    
    @DELETE
    @Path("/deleteUser/{userName}/{password}")
    public UserDTO deleteUser(@PathParam("userName") String userName, @PathParam("password") String password){
        return userManagement.removeUser(userName, password);
    }
    
    @PUT
    @Consumes("application/json")
    @Path("/editUser/{userName}/{password}")
    @Interceptors(ValidatorInterceptor.class)
    public UserDTO editUser(@PathParam("userName") String userName, @PathParam("password") String password, UserDTO editedUser){
        return userManagement.editUser(userName, password, editedUser);
    }
}
