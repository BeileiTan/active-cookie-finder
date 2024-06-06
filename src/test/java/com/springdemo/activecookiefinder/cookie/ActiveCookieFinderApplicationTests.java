package com.springdemo.activecookiefinder.cookie;

import com.springdemo.activecookiefinder.system.exception.InvalidDateFormatException;
import com.springdemo.activecookiefinder.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CookieServiceTests {

    @Autowired
    private CookieService cookieService;

    @BeforeEach
    void setUp(){

    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testFindMostActiveCookies() throws IOException {
        List<String> result = cookieService.findMostActiveCookies("cookie_log.csv", "2018-12-09");
        assertEquals(List.of("AtY0laUfhglK3lC7"), result);

        result = cookieService.findMostActiveCookies("cookie_log.csv", "2018-12-08");
        assertEquals(List.of("fbcn5UAVanZf6UtG", "SAZuXPGUrfbcn5UA", "4sMM2LxV07bPJzwf"), result);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testFileNotFound() {
        ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
            cookieService.findMostActiveCookies("cookie_log1.csv", "2018-12-09");
        });
        assertEquals("Could not find the file: cookie_log1.csv", thrown.getMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testInvalidDateFormat() {
        assertThrows(InvalidDateFormatException.class, () -> {
            cookieService.findMostActiveCookies("cookie_log.csv", "2018-12-007");
        });
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testDateNotFound() {
        ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
            cookieService.findMostActiveCookies("cookie_log.csv", "2020-01-01");
        });
        assertEquals("Could not find the specific date: 2020-01-01", thrown.getMessage());
    }

}