package stepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.Sortierung;
import drivers.DriverHolder;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SortierungSteps {
    Sortierung sortPage = new Sortierung(DriverHolder.getDriver());

    @When("user selects sort option {string}")
    public void user_selects_sort_option(String option) {
        sortPage.selectSortOption(option);
    }

    @Then("products should be sorted correctly by {string}")
    public void products_should_be_sorted_correctly(String option) {
        if (option.contains("Price")) {
            List<Double> actualPrices = sortPage.getProductPricesAsList();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);

            if (option.equals("Price (low to high)")) {
                Collections.sort(expectedPrices);
            } else {
                expectedPrices.sort(Collections.reverseOrder());
            }
            Assert.assertEquals(actualPrices, expectedPrices, "Die Preise sind nicht korrekt sortiert!");
        }
    }
}