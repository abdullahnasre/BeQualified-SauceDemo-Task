package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    // --- Action Methods ---

    /**
     * Gibt den Benutzernamen ein, sobald das Feld bereit ist.
     */
    public void enterUsername(String user) {
        // TODO: Vorheriges Löschen des Feldes implementieren (clear())
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(user);
    }

    /**
     * Gibt das Passwort sicher ein.
     */
    public void enterPassword(String pass) {
        // TODO: Sensitive Daten im Log maskieren
        driver.findElement(passwordField).sendKeys(pass);
    }

    /**
     * Klickt auf den Login-Button.
     */
    public void clickLogin() {
        // Warten, bis der Button klickbar ist (wichtig für Headless)
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Hilfsmethode für einen kompletten Login-Vorgang.
     */
    public void login(String user, String pass) {
        // TODO: Methode nutzen, um Code-Duplizierung in Steps zu vermeiden
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}