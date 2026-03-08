package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver getNewInstance(String browserName) {
        if (browserName == null) browserName = "chrome";

        // Automatische Headless-Aktivierung für GitHub (Linux)
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux") && !browserName.contains("headless")) {
            browserName = "chrome-headless";
        }

        // Variablen müssen vor dem Switch deklariert werden
        ChromeOptions chromeOptions;
        Map<String, Object> prefs;

        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                return new FirefoxDriver();

            case "firefox-headless":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                return new EdgeDriver();

            case "fr":
                chromeOptions = new ChromeOptions();
                prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.addArguments("start-maximized", "--incognito", "--lang=de", "--remote-allow-origins=*");
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions);

            default:
                chromeOptions = new ChromeOptions();
                prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.addArguments("start-maximized", "--incognito", "--remote-allow-origins=*");
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(chromeOptions);
        }
    }
}