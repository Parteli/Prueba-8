package com.parteli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Prueba8SpringApplication {

    public static void main(String[] args) {
            SpringApplication.run(Prueba8SpringApplication.class, args);
            System.err.println( "Running" );
    }
    
}