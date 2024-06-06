package com.springdemo.activecookiefinder.cookie;

import com.springdemo.activecookiefinder.system.exception.InvalidLogFormatException;
import com.springdemo.activecookiefinder.system.exception.ObjectNotFoundException;
import com.springdemo.activecookiefinder.system.exception.InvalidDateFormatException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CookieService {
    private final Map<String, List<String>> cache = new ConcurrentHashMap<>();

    public List<String> findMostActiveCookies(String fileName, String date) {
        return cache.computeIfAbsent(date, d -> {
            LocalDate targetDate = parseDate(date);
            Map<String, Integer> cookieCount = new HashMap<>();

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
                for (String line : reader.lines().collect(Collectors.toList())) {
                    if (line.trim().isEmpty() || line.startsWith("cookie,timestamp")) {
                        continue; // Skip empty lines and header
                    }

                    if (!processLine(line, targetDate, cookieCount)) {
                        break;
                    }

                }
            } catch (IOException e) {
                throw new ObjectNotFoundException("file", fileName);
            }

            return findMaxCookies(cookieCount, date);
        });
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(date, e);
        }
    }

    private boolean processLine(String line, LocalDate targetDate, Map<String, Integer> cookieCount) {
        String[] parts = line.split(",");
        if (parts.length < 2) return true;

        String cookie = parts[0];
        LocalDate timestampDate = parseTimestampDate(parts[1]);

        if (timestampDate != null) {
            if (timestampDate.isBefore(targetDate)) {
                return false;
            }
            if (timestampDate.equals(targetDate)) {
                cookieCount.put(cookie, cookieCount.getOrDefault(cookie, 0) + 1);
            }
        }

        return true;
    }

    private LocalDate parseTimestampDate(String timestamp) {
        try {
            return LocalDate.parse(timestamp.substring(0, 10));
        } catch (DateTimeParseException e) {
            throw new InvalidLogFormatException(timestamp, e);
        }
    }

    private List<String> findMaxCookies(Map<String, Integer> cookieCount, String date) {
        int maxCount = cookieCount.values().stream().max(Integer::compareTo).orElseThrow(() -> new ObjectNotFoundException("specific date", date));
        List<String> mostActiveCookies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cookieCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostActiveCookies.add(entry.getKey());
            }
        }
        return mostActiveCookies;
    }
}
