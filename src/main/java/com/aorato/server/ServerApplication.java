package com.aorato.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@SpringBootApplication
@Profile("server")
public class ServerApplication {

    public static void main(String[] args) throws IOException {

        new SpringApplicationBuilder()
                .sources(ServerApplication.class)
                .profiles("server")
                .run(args);

        while (System.in.available() == 0){
            System.out.println("server run until user will do ENTER");

        }
        System.out.println("Bye Bye");
    }
}
