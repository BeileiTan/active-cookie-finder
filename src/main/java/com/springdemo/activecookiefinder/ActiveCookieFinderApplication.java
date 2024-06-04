package com.springdemo.activecookiefinder;

import com.springdemo.activecookiefinder.cookie.CookieLogProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class ActiveCookieFinderApplication implements CommandLineRunner {

    private final CookieLogProcessor cookieLogProcessor;

    public ActiveCookieFinderApplication(CookieLogProcessor cookieLogProcessor) {
        this.cookieLogProcessor = cookieLogProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ActiveCookieFinderApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CommandLine.run(cookieLogProcessor, args);
    }

}
