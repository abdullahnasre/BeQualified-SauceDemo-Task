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

        // Erkennt automatisch, ob der Test auf GitHub (Linux) läuft
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux") && !browserName.contains("headless")) {
            browserName = "chrome-headless";
        }

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

            case "edge":
                return new EdgeDriver();

            default: // Das wird lokal bei dir in München genutzt
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