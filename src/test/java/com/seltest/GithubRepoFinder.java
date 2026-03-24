package com.seltest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GithubRepoFinder {

    private static WebDriver chromeDriver;
    private static WebDriver firefoxDriver;
    private String repository;
    private String user;

    @BeforeAll
    static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeDriver = new ChromeDriver(chromeOptions);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxDriver = new FirefoxDriver(firefoxOptions);
    }

    @AfterAll
    static void cleanUp() {
        chromeDriver.quit();
        firefoxDriver.quit();
    }

    @Test
    void chromeTest() {
        // TODO
        chromeDriver.get("https://github.com/jaguar-ks?tab=repositories");
    }

    @Test
    void fireFoxTest() {
        // TODO
        firefoxDriver.get("https://github.com/jaguar-ks?tab=repositories");
    }
}
