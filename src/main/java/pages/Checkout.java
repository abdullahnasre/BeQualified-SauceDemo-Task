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
        // Standard-Wait für normale Operationen
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
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
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