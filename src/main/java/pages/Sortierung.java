package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Sortierung {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Sortierung(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Locators ---
    // TODO: Überprüfen, ob CSS-Selektoren für die Preisliste stabiler sind
    private final By sortDropdown = By.className("product_sort_container");
    private final By productPrices = By.className("inventory_item_price");
    private final By productNames = By.className("inventory_item_name");

    // --- Action Methods ---

    /**
     * Wählt eine Sortieroption aus dem Dropdown aus.
     */
    public void selectSortOption(String optionText) {
        // Warten, bis das Dropdown bereit ist
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
    }

    /**
     * Extrahiert alle Produktpreise als numerische Liste.
     */
    public List<Double> getProductPricesAsList() {
        // TODO: Warten, bis die Liste nach der Sortierung stabil ist
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            // Bereinigt den Text (entfernt $) und konvertiert zu Double
            prices.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return prices;
    }

    /**
     * Extrahiert alle Produktnamen als Liste.
     */
    public List<String> getProductNamesAsList() {
        // TODO: Hilfreich für A-Z / Z-A Sortierungstests
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement element : nameElements) {
            names.add(element.getText());
        }
        return names;
    }
}