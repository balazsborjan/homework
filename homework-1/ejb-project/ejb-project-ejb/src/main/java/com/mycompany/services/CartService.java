package com.mycompany.services;

import com.mycompany.dto.MobileDTO;
import com.mycompany.exceptions.IllegalRestRequestException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class CartService implements Serializable{

    @Inject
    private InventoryService inventory;
    
    private final List<MobileDTO> products = new ArrayList<>();
    private static final String NO_MORE_PRODUCT = "There isn't enough available products!";
    private static final String INVALID_ID = "Invalid ID!";
    

    public ArrayList<MobileDTO> getProducts() {
        return (ArrayList)products;
    }
    
    public MobileDTO getProductById(String id){
        for(MobileDTO mobile : products){
            if (mobile.getId().equals(id)) {
                return mobile;
            }
        }
        
        throw new IllegalRestRequestException(INVALID_ID);
    }
    
    public MobileDTO addProduct(String id) {
        for(MobileDTO mobile : inventory.getMobiles()){
            if (mobile.getId().equals(id)) {
                if (mobile.getPiece() > 0) {
                    return newProductIsInTheCartYet(id, mobile);
                }
                
                throw new IllegalRestRequestException(NO_MORE_PRODUCT);
            }
        }
        
        throw new IllegalRestRequestException(INVALID_ID);
    }
    
    public MobileDTO deleteProduct(String id){
        for(MobileDTO mobile : products){
            if (mobile.getId().equals(id)) {
                MobileDTO deleteMobile = mobile;
                products.remove(mobile);                
                return inventory.addMobile(deleteMobile);
            }
        }
        
        throw new IllegalRestRequestException(INVALID_ID);
    }
    
    public String buyAllProducts(){
        if (!products.isEmpty()) {
            products.clear();
            return "Payment done!";
        }
        
        return "There is no products in the cart!";
        
    }
    
    @Remove
    public void checkout() {
        products.stream().forEach((product) -> {
            inventory.returnMobile(product);
        });
        
        products.clear();
    }
    
    private MobileDTO newProductIsInTheCartYet(String id, MobileDTO actMobile){
        actMobile.setPiece(actMobile.getPiece() - 1);
        
        for (MobileDTO product : products) {
            if (product.getId().equals(id)) {
                product.setPiece(product.getPiece() + 1);
                return product;
            }
        }
        
        return setUpNewMobile(actMobile);
    }
    
    private MobileDTO setUpNewMobile(MobileDTO mobile){
        MobileDTO newMobile = new MobileDTO();
        newMobile.setId(mobile.getId());
        newMobile.setManufacturer(mobile.getManufacturer());
        newMobile.setPiece(1);
        newMobile.setPrice(mobile.getPrice());
        newMobile.setType(mobile.getType());
        
        products.add(newMobile);        
        return newMobile;
    }
}
