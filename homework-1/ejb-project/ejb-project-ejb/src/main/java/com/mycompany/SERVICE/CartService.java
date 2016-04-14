package com.mycompany.SERVICE;

import com.mycompany.DTO.MobileDTO;
import com.mycompany.EXCEPTION.IllegalRestRequestException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class CartService {

    @Inject
    private InventoryService inventory;
    
    private final List<MobileDTO> products = new ArrayList<>();

    public ArrayList<MobileDTO> getProducts() {
        return (ArrayList)products;
    }
    
    public MobileDTO getProductById(String id){
        for(MobileDTO mobile : products){
            if (mobile.getId().equals(id)) {
                return mobile;
            }
        }
        
        throw new IllegalRestRequestException("There is no product in the cart with an ID like this!");
    }
    
    public MobileDTO addProduct(String id) {
        for(MobileDTO mobile : inventory.getMobiles()){
            if (mobile.getId().equals(id)) {
                if (mobile.getPiece() > 0) {
                    mobile.setPiece(mobile.getPiece() - 1);
                    
                    for (MobileDTO product : products) {
                        if (newProductIsInTheCartYet(product, id)) {
                            return product;
                        }
                    }
                    
                    MobileDTO newMobile = new MobileDTO();
                    setUpNewMobile(mobile, newMobile);
                    
                    products.add(newMobile);
                    return newMobile;
                }
                
                throw new IllegalRestRequestException("There is no more available piece from this product!");
            }
        }
        
        throw new IllegalRestRequestException("There is no product in the cart with an ID like this!");
    }
    
    public MobileDTO deleteProduct(String id){
        MobileDTO deleteMobile;
        for(MobileDTO mobile : products){
            if (mobile.getId().equals(id)) {
                inventory.addMobile(mobile);
                deleteMobile = mobile;
                products.remove(mobile);
                return deleteMobile;
            }
        }
        
        throw new IllegalRestRequestException("There is no product in the cart with an ID like this!");
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
    
    private boolean newProductIsInTheCartYet(MobileDTO product, String id){
        if (product.getId().equals(id)) {
                product.setPiece(product.getPiece() + 1);
                return true;
            }
        
        return false;
    }
    
    private void setUpNewMobile(MobileDTO mobile, MobileDTO newMobile){
        newMobile.setId(mobile.getId());
        newMobile.setManufacturer(mobile.getManufacturer());
        newMobile.setPiece(1);
        newMobile.setPrice(mobile.getPrice());
        newMobile.setType(mobile.getType());
    }
    
    /*for (MobileDTO product : products) {
                        if (product.getId().equals(id)) {
                            product.setPiece(product.getPiece() + 1);
                            return product;
                        }
                    }*/
}
