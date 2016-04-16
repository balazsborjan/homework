package com.mycompany.async.demo;

import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class AsyncService {

    @Resource
    SessionContext ctx;
 
    public Future<Integer> asyncCounter() {
        Integer status = 100000000;
        Integer counter = 0;
        
        while (counter < status) {
            counter += 2;
            counter -= 1;
        }
 
        if(ctx.wasCancelCalled()) {
            return new AsyncResult<>(-1);
        }
        
        while (counter > 1) {
            counter -= 2;
            counter += 1;
        }
        
        return new AsyncResult<>(counter);
    }
}
