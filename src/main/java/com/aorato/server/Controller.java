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
    ResponseEntity clientRequest(@RequestParam int clientId) throws ExecutionException, InterruptedException {
        ResponseEntity futureRespone = CompletableFuture.supplyAsync(() -> {
            if (service.isAttack(clientId))
                return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
            else return new ResponseEntity(HttpStatus.OK);
        }).get();
        return futureRespone;
    }

}
