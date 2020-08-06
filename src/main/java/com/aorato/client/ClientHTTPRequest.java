package com.aorato.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class ClientHTTPRequest {
    int clientId;
    String uri = "http://localhost:8080?clientId=";
    Logger logger = LoggerFactory.getLogger(ClientHTTPRequest.class);

    public ClientHTTPRequest(int clientId) {
        this.clientId = clientId;
    }

    public void sendHttpRequest(Executor executor) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.uri + this.clientId)).build();
                TimeUnit.SECONDS.sleep((long) (Math.random() * 5));
                int status = client.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
                logger.info("clientId: " + clientId + ", status code: " + status);
            } catch (InterruptedException | IOException e) {

                throw new IllegalStateException(e);
            }
        }, executor);
        try {
            future.get();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
