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
     * Nutzt JavaScript-Klicks für maximale Stabilität in der CI/CD-Pipeline.
     */
    public void navigateToCheckoutForm() {
        // 1. Warenkorb-Link suchen und per JavaScript klicken (umgeht Interaktionsfehler)
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);

        // 2. Checkout-Button suchen und ebenfalls per JavaScript klicken
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        // 3. Warten, bis das Formular wirklich geladen ist
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    /**
     * Füllt die Checkout-Details aus und schickt das Formular ab.
     */
    public void enterDetails(String first, String last, String zip) {
        // 1. Felder ausfüllen
        WebElement fName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        fName.clear();
        fName.sendKeys(first);

        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(last);

        driver.findElement(zipCodeField).clear();
        driver.findElement(zipCodeField).sendKeys(zip);

        // 2. STABILISIERUNG: Klick auf 'Continue' via JavaScript
        WebElement contBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", contBtn);
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