package dtoTest;

import com.mycompany.dto.MobileDTO;
import java.util.UUID;
import org.junit.Test;

public class MobileDTOTest {

    @Test
    public void idNotNull(){
        
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getId());
    }
    
    @Test
    public void idShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void typeNotNull(){
        MobileDTO mobile = new MobileDTO(null, "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getType());
    }
    
    @Test
    public void typeShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void typeSizeMinThree(){
        MobileDTO mobile = new MobileDTO("tp", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getType());
    }
    
    @Test
    public void typeSizeShouldMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void manufactNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", null, 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getManufacturer());
    }
    
    @Test
    public void manufactShouldNotNull(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void manufactSizeMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "A", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getManufacturer());
    }
    
    @Test
    public void manufactSizeShouldMinThree(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void priceMinOne(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 0, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getPrice());
    }
    
    @Test
    public void priceShouldMinOne(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 2);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
    
    @Test
    public void pieceMinZero(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, -1);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.notZeroViolations(mobile, mobile.getPiece());
    }
    
    @Test
    public void pieceShouldMinZero(){
        MobileDTO mobile = new MobileDTO("iPhone 6s", "Apple", 2000, 0);
        mobile.setId(UUID.randomUUID().toString());
        
        ValidationTestHelper.zeroViolations(mobile);
    }
}
