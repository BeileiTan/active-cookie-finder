package com.springdemo.activecookiefinder.cookie;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.*;

@Component
@Command(name = "cookie-log-processor", mixinStandardHelpOptions = true, version = "1.0",
        description = "Processes a cookie log file and returns the most active cookie(s) for a specified date.")
public class CookieLogProcessor implements Runnable {

    @Option(names = {"-f"}, required = true, description = "The cookie log file to process.")
    private String fileName;

    @Option(names = {"-d"}, required = true, description = "The date to find the most active cookie (yyyy-MM-dd).")
    private String date;

    private final CookieService cookieService;

    public CookieLogProcessor(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public void run() {
        List<String> mostActiveCookies = cookieService.findMostActiveCookies(fileName, date);
        mostActiveCookies.forEach(System.out::println);
    }
}
