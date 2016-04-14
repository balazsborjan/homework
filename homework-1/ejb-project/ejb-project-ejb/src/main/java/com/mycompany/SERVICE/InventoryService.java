package com.mycompany.SERVICE;

import com.mycompany.DTO.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class InventoryService {

    private final List<MobileDTO> mobiles = new ArrayList<>();

    @PostConstruct
    private void init(){
        mobiles.add(new MobileDTO("iPhone 5s", "Apple", 800, 3));
        mobiles.add(new MobileDTO("iPhone 6", "Apple", 1200, 12));
        mobiles.add(new MobileDTO("iPhone 6s", "Apple", 1500, 5));
        mobiles.add(new MobileDTO("iPhone 7", "Apple", 2000, 0));
        
        mobiles.stream().forEach((mobile) -> {
            mobile.setId(UUID.randomUUID().toString());
        });
    }
    
    public List<MobileDTO> getMobiles() {
        return mobiles;
    }
    
    public MobileDTO addMobile(MobileDTO newMobile){
        for (MobileDTO mobile : mobiles) {
            if (mobile.getManufacturer().equals(newMobile.getManufacturer()) && mobile.getType().equals(newMobile.getType())) {
                mobile.setPiece(mobile.getPiece() + newMobile.getPiece());
                mobile.setPrice(newMobile.getPrice());
                
                return mobile;
            }
        }
        
        newMobile.setId(UUID.randomUUID().toString());
        mobiles.add(newMobile);
        return newMobile;
    }
    
    public void returnMobile(MobileDTO returnMobile){
        mobiles.stream().filter((mobile) -> (mobile.getId().equals(returnMobile.getId()))).forEach((mobile) -> {
            mobile.setPiece(mobile.getPiece() + returnMobile.getPiece());
        });
    }
}
