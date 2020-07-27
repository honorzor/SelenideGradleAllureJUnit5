package ru.honorzor.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Listeners;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({ScreenShooter.class})
public class SettingsDriver {

    @BeforeAll
    static void openSession() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = true;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.reportsFolder = "allure-results";
    }

    @AfterAll
    static void closeSession() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }

    @AfterEach
    public void tearDown() throws IOException {
        screenshot();
    }

    @Attachment(value = "Attachment", type = "image/png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
