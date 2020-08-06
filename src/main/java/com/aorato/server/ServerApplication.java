package com.aorato.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@SpringBootApplication
@Profile("server")
public class ServerApplication {

    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext serverApplication = new SpringApplicationBuilder()
                .sources(ServerApplication.class)
                .profiles("server")
                .run(args);

        while (System.in.available() == 0){
        }
        serverApplication.close();
        System.out.println("Bye Bye");
    }
}
