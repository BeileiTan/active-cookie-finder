package com.springdemo.activecookiefinder.cookie;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CookieService {

    public List<String> findMostActiveCookies(String fileName, String date) throws IOException {
        Map<String, Integer> cookieCount = new HashMap<>();
        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String cookie = parts[0];
                LocalDate timestampDate = LocalDate.parse(parts[1].substring(0, 10));

                if (timestampDate.equals(targetDate)) {
                    cookieCount.put(cookie, cookieCount.getOrDefault(cookie, 0) + 1);
                }
            }
        }

        int maxCount = cookieCount.values().stream().max(Integer::compareTo).orElse(0);
        List<String> mostActiveCookies = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cookieCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostActiveCookies.add(entry.getKey());
            }
        }

        return mostActiveCookies;
    }
}