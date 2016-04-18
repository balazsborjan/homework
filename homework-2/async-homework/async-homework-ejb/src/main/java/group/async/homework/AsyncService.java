package group.async.homework;

import group.async.homework.dto.AsyncDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class AsyncService {

    @Resource
    private SessionContext ctx;
    
    private final List<AsyncDTO> dtoList = new ArrayList<>();
 
    @Asynchronous
    public void longAsyncMethod(AsyncDTO dto) throws InterruptedException 
    {   
        Thread.sleep(20000);
        dtoList.add(dto);
        
        for (int i = 0; i < 10; i++) 
        {
            AsyncDTO newDto = new AsyncDTO();
            newDto.setId(dto.getId()+ i);
            newDto.setAmount(i);
            
            dtoList.add(newDto);
        }
    }
    
    @Asynchronous
    public void shortAsyncMethod() {
        AsyncDTO newDto = new AsyncDTO();
        newDto.setAmount(100);
        newDto.setId(dtoList.size());
        
        dtoList.add(newDto);
    }
    
    @Asynchronous
    public Future<Integer> getListSize() {
        return new AsyncResult<>(dtoList.size());
    }
}
