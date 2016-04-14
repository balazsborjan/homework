package com.mycompany.REST;

import com.mycompany.DTO.MobileDTO;
import com.mycompany.DTO.UserDTO;
import com.mycompany.EXCEPTION.IllegalRestRequestException;
import com.mycompany.SERVICE.InventoryService;
import com.mycompany.SERVICE.UserManagementService;
import java.util.List;
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

@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryRESTService {

    @Inject
    private InventoryService inventory;
    
    @Inject
    private UserManagementService userService;
    
    @GET
    public List<MobileDTO> getMobiles(){
        return inventory.getMobiles();
    }
    
    @POST
    @Path("/addMobile")
    @Consumes("application/json")
    public MobileDTO addMobile(@Context HttpServletRequest request, MobileDTO mobile){
        HttpSession session = request.getSession();
        
        Object loggedInUser = session.getAttribute("user");
        if ((UserDTO)loggedInUser == null || !userService.getUser((UserDTO)loggedInUser).getAdmin()) {
            session.invalidate();
            
            throw new IllegalRestRequestException("You dont have permission to add new product. Log in as Administrator!");
        }else{
            return inventory.addMobile(mobile);
        }
    }
}
