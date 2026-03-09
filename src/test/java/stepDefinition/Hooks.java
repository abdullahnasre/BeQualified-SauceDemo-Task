package stepDefinition;

import drivers.DriverFactory;
import drivers.DriverHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.Utility;

import java.time.Duration;

import static drivers.DriverHolder.driver;

public class Hooks {

    @Before
    public void setupDriver() {
        String browser = System.getProperty("browser", "chrome");

        // 1. Erstelle die Instanz über deine Factory
        WebDriver webDriver = DriverFactory.getNewInstance(browser);

        // 2. WICHTIG: Nutze direkt den webDriver für Einstellungen (behebt ThreadLocal Fehler)
        webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(15));
        webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(20));

        // 3. Speichere die Instanz im DriverHolder
        DriverHolder.setDriver(webDriver);
    }

    @After
    public void closeDriver() {
        WebDriver driver = DriverHolder.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}