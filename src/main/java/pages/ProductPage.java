package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---
    // TODO: Evaluieren, ob CSS-Selector für die Performance vorteilhafter sind
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By productSortContainer = By.className("product_sort_container");

    // --- Action Methods ---

    /**
     * Fügt ein Produkt anhand des Namens zum Warenkorb hinzu.
     */
    public void addProductToCart(String productName) {
        // TODO: Dynamischen XPath in eine separate Locator-Strategie auslagern
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item_description']//button";

        // Warten, bis der Button im Headless-Modus wirklich klickbar ist
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    /**
     * Gibt die Anzahl der Artikel im Warenkorb-Badge zurück.
     */
    public String getCartBadgeCount() {
        // TODO: Fall abfangen, wenn das Badge noch nicht existiert (leerer Warenkorb)
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge)).getText();
    }
}