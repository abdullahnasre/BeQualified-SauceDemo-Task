package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Checkout {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // --- Locators ---
    private final By cartLink = By.className("shopping_cart_link");
    private final By checkoutButton = By.id("checkout");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.xpath("//h2[@class='complete-header']");
    private final By itemTotalLabel = By.className("summary_subtotal_label");

    // --- Action Methods ---

    /**
     * Navigiert zum Warenkorb und klickt auf Check-out.
     * Hilft gegen Timing-Probleme in der CI/CD-Pipeline.
     */
    public void navigateToCheckoutForm() {
        // 1. Warten, bis der Warenkorb-Link im DOM und sichtbar ist
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartLink));

        // 2. Klick ausführen (mit einer kleinen Absicherung, falls der normale Klick hakt)
        try {
            cart.click();
        } catch (Exception e) {
            // Falls der normale Klick in der Cloud blockiert wird, nutzen wir JavaScript
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", cart);
        }

        // 3. Warten, bis der Checkout-Button erscheint (Bestätigung des Seitenwechsels)
        Objects.requireNonNull(wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton))).click();
    }

    /**
     * Füllt die Checkout-Details aus.
     */
    public void enterDetails(String first, String last, String zip) {
        // Explizites Warten auf das erste Feld des Formulars
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(first);
        driver.findElement(lastNameField).sendKeys(last);
        driver.findElement(zipCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public double getSubtotal() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel));
        String text = element.getText();
        String cleanValue = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanValue);
    }
}