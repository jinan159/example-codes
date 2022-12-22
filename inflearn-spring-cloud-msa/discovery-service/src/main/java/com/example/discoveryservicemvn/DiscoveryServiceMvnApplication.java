package com.example.discoveryservicemvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceMvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceMvnApplication.class, args);
    }

}
