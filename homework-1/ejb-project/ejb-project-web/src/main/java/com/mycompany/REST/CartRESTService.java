package com.mycompany.rest;

import com.mycompany.dto.MobileDTO;
import com.mycompany.dto.UserDTO;
import com.mycompany.exceptions.IllegalRestRequestException;
import com.mycompany.services.CartService;
import com.mycompany.services.InventoryService;
import com.mycompany.services.UserManagementService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/cart")
@Produces("application/json")
@SessionScoped
public class CartRESTService implements Serializable {

    @Inject
    private CartService cartService;
    
    @Inject
    private InventoryService inventory;
    
    @Inject
    private UserManagementService userService;
    
    @GET
    public List<MobileDTO> getRecentProducts(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");        
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            return cartService.getProducts();
        }
    }
    
    @GET
    @Path("/getProduct/{id}")
    public MobileDTO getProductById(@Context HttpServletRequest request, @PathParam("id") String id){
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();
            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            return cartService.getProductById(id);
        }
    }
    
    @GET
    @Path("/buyProducts")
    @Produces("text/plain")
    public String buyAllProducts(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();
            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            return cartService.buyAllProducts();
        }
    }
    
    @POST
    @Path("/addProduct/{id}")
    public MobileDTO addProduct(@Context HttpServletRequest request, @PathParam("id") String id){
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();
            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            return cartService.addProduct(id);
        }
    }
    
    @DELETE
    @Path("/deleteProduct/{id}")
    public MobileDTO deleteProduct(@Context HttpServletRequest request, @PathParam("id") String id){
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();
            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            return cartService.deleteProduct(id);
        }
    }
    
    @POST
    @Path("/checkout")
    public List<MobileDTO> checkout(@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        
        Object loggedInUserCheck = session.getAttribute("user");
        if (loggedInUserCheck == null || userService.getUser((UserDTO)loggedInUserCheck) == null) {
            session.invalidate();
            
            throw new IllegalRestRequestException("Invalid request, User did not logged in!");
        }else{
            List<MobileDTO> returnList = cartService.getProducts();
            cartService.checkout();
            request.getSession().invalidate();
            return returnList;
        }
    }
}
