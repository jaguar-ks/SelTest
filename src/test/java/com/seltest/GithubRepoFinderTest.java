package com.seltest;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GithubRepoFinderTest {

    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;

    private final String user;
    private final String repository;

    public GithubRepoFinderTest() {
        this.user = "jaguar-ks";
        this.repository = "SelTest";
    }

    // ✅ Must NOT be static under PER_CLASS
    @BeforeEach
    void setUp() {
        // try {
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        chromeDriver = new ChromeDriver(chromeOptions);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("/usr/bin/firefox-esr");`
        // firefoxOptions.addArguments("--headless");
        firefoxDriver = new FirefoxDriver(firefoxOptions);
        System.out.println(chromeDriver == null);
        // }
        // catch (Exception e) {
        //     System.out.println("HERE: " + e);
        // }
    }

    @AfterEach
    void cleanUp() {
        if (chromeDriver != null)
            chromeDriver.quit();
        if (firefoxDriver != null)
            firefoxDriver.quit();
    }

    @Test
    void chromeTest() {
        Assertions.assertTrue(
            doTest(chromeDriver),
            "❌ Repository " + repository + " NOT found."
        );
    }

    @Test
    void firefoxTest() {
        Assertions.assertTrue(
            doTest(firefoxDriver),
            "❌ Repository " + repository + " NOT found."
        );
    }

    private boolean doTest(WebDriver driver) {
        try {
            driver.get("https://github.com/" + user + "?tab=repositories");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#user-repositories-list")
            ));

            List<WebElement> repos = driver.findElements(
                By.cssSelector("a[itemprop='name codeRepository']")
            );

            return repos.stream()
                    .map(e -> e.getText().trim())
                    .anyMatch(name -> name.equalsIgnoreCase(repository));

        } catch (Exception e) {
            System.out.println("❌ Test interrupted: " + e);
            return false;
        }
    }
}
