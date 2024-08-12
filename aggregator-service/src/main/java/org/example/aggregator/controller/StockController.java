package org.example.aggregator.controller;

import com.google.common.util.concurrent.Uninterruptibles;
import org.example.aggregator.service.PriceUpdateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("stock" )
public class StockController {
    @Autowired
    private PriceUpdateListener listener;
    @GetMapping(value = "updates",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter priceUpdates(){
       /*SseEmitter emitter = new SseEmitter(20000L);
       Runnable runnable =() ->{
           for(int i=0;i<5;i++){
               Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
               try {
                  emitter.send(("hello-" + i));
               }catch (IOException ex){
                   throw  new RuntimeException(ex);
               }
           }
           emitter.complete();
       };
        Thread thread = new Thread(runnable);
        thread.start();
        return emitter;*/

        return listener.createEmiter();

    }
}
