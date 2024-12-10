package com.manselenium.tests;

import com.manselenium.listener.TestListener;
import com.manselenium.util.Config;
import com.manselenium.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Listeners({TestListener.class})
public class AbstractTests {
    public WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTests.class);

    @BeforeSuite
    public  void setUpConfig(){Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver =
                Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))
                        ? getRemoteDriver() :
                        getLocalDriver();
        ctx.setAttribute(Constants.DRIVER,this.driver);

    }

    private WebDriver getLocalDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

private WebDriver getRemoteDriver() throws MalformedURLException {
    // Initialize capabilities based on browser type
    Capabilities capabilities = switch (Objects.requireNonNullElse(
            Config.get(Constants.BROWSER), Constants.CHROME).toLowerCase()) {
        case Constants.FIREFOX -> new FirefoxOptions();
        default -> new ChromeOptions();
    };

    // Get grid URL details
    String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
    String hubHost = Config.get(Constants.GRID_HUB_HOST);
    String urlString = String.format(urlFormat, hubHost);

    // Log grid URL
    log.info("Grid URL: " + urlString);

    // Construct URL object and initialize RemoteWebDriver
    URL gridUrl = new URL(urlString);
    return new RemoteWebDriver(gridUrl, capabilities);
}

    @AfterTest
    public void quitDriver(){
        if(driver!=null) {
            driver.quit();
        }
    }
}
