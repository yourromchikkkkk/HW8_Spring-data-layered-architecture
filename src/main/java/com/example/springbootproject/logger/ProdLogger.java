package com.example.springbootproject.logger;

public class ProdLogger implements Logger {
    @Override
    public void logMessage(String message) {
        System.out.println("PROD: " + message);
    }
}
