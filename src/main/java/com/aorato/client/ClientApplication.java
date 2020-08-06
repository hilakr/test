package com.aorato.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@Configuration
@Profile("client")
public class ClientApplication {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        new SpringApplicationBuilder().sources(ClientApplication.class).profiles("client").web(WebApplicationType.NONE).run(args);

        ArrayList<ClientHTTPAttack> clients = new ArrayList<>();
        int numOfClients = Integer.parseInt(args[0]);

        ExecutorService executors = Executors.newFixedThreadPool(numOfClients);

        for (int i = 0; i < numOfClients; i++) {
            ClientHTTPAttack task = new ClientHTTPAttack(getRandomNumber(1, numOfClients));
            clients.add(task);
        }
        while (System.in.available() == 0) {
                for (ClientHTTPAttack client: clients) {
                    client.sendHttpRequest(executors);
                }
            }
        System.out.println("Bye Bye");
    }
    }
