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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
    private final By itemTotalLabel = By.cssSelector("[data-test='subtotal-label']");

    // --- Action Methods ---

    public void navigateToCheckoutForm() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    public void enterDetails(String first, String last, String zip) {
        WebElement fName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        fName.clear();
        fName.sendKeys(first);

        driver.findElement(lastNameField).sendKeys(last);
        driver.findElement(zipCodeField).sendKeys(zip);

        // OPTIMIERUNG: Explizites Warten auf Klickbarkeit und JS-Executor Fallback
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public double getSubtotal() {
        // OPTIMIERUNG: Nutze presenceOfElementLocated vor visibilityOf für CI-Latenzen
        wait.until(ExpectedConditions.presenceOfElementLocated(itemTotalLabel));
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalLabel));

        // Sicherstellen, dass der Text geladen ist
        wait.until(d -> label.getText().contains("$"));

        String text = label.getText();
        String cleanValue = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanValue);
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}