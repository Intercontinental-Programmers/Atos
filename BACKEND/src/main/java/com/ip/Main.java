package com.ip;

import com.ip.data.client.HttpClientManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        HttpClientManager manager = new HttpClientManager();

        String response;
        try {
            response = manager.get("http://localhost:8080/api/items");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
