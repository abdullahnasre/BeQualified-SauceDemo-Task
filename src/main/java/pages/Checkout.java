package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.xpath("//h2[@class='complete-header']");
    private final By itemTotalLabel = By.className("summary_subtotal_label");

    // --- Action Methods ---

    /**
     * Füllt die Checkout-Details aus und navigiert zur Übersicht.
     */
    public void enterDetails(String first, String last, String zip) {
        // TODO: Logging hinzufügen (z.B. log.info("Entering details for: " + first))
        driver.findElement(firstNameField).sendKeys(first);
        driver.findElement(lastNameField).sendKeys(last);
        driver.findElement(zipCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }

    /**
     * Schließt den Kauf ab. Wartet, bis der Button im Headless-Modus klickbar ist.
     */
    public void finishCheckout() {
        // TODO: Überprüfen, ob wir uns auf der richtigen URL befinden (Step Two)
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    /**
     * Holt die Bestätigungsnachricht nach dem Kauf.
     */
    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    /**
     * Extrahiert den Subtotal-Wert aus der Übersicht.
     */
    public double getSubtotal() {
        // Warten auf Sichtbarkeit des Preises
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel));

        String text = element.getText();
        // Regex entfernt alles außer Zahlen und Punkt
        String cleanValue = text.replaceAll("[^0-9.]", "");

        if (cleanValue.isEmpty()) {
            // TODO: Custom Error Handling statt RuntimeException nutzen
            throw new RuntimeException("Preis-Parsing fehlgeschlagen für Text: " + text);
        }

        return Double.parseDouble(cleanValue);
    }
}