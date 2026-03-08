package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver getNewInstance(String browserName) {
        ChromeOptions chromeOptions;
        Map<String, Object> prefs;
        DesiredCapabilities capabilities;

        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                return new FirefoxDriver();

            case "firefox-headless":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                return new EdgeDriver();

            case "fr": // Spezial-Konfiguration (z.B. für deutsche Sprache)
                chromeOptions = new ChromeOptions();
                prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--lang=de");
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions);

            default:
                chromeOptions = new ChromeOptions();
                prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions);
        }
    }
}