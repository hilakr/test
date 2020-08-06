package com.aorato.server;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.LoggerFactory;

@org.springframework.web.bind.annotation.RestController

public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    DDoSProtectionService service = new DDoSProtectionService();

    @GetMapping()
    ResponseEntity clientRequest(@RequestParam int clientId) throws InterruptedException, ExecutionException{
        CompletableFuture<ResponseEntity> futureResponse = CompletableFuture.supplyAsync(() -> {
            if (service.isAttack(clientId)){
                logger.debug("server got more than 5 requests per 5 secs, will return Service Unavailable");
                return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
            }
            else return new ResponseEntity(HttpStatus.OK);
        });
        try {
           return futureResponse.get();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
           throw e;
        }
    }

}
