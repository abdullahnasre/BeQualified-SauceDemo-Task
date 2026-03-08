package stepDefinition;

import drivers.DriverFactory;
import drivers.DriverHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.Utility;

import java.time.Duration;

public class Hooks {

    @Before
    public void setupDriver() {
        String url = Utility.getProperty("url");
        WebDriver driver = DriverFactory.getNewInstance("chrome");
        DriverHolder.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get(url);
    }

    @After
    public void closeDriver() {
        WebDriver driver = DriverHolder.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}