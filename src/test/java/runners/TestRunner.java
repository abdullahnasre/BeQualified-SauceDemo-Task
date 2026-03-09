package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/Features", // Pfad zu deinen .feature Dateien
        glue = "stepDefinition",                // Paketname deiner Steps und Hooks
        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression-report.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        tags = "@RegressionTest", // Führt nur Szenarien mit diesem Tag aus
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Diese Klasse bleibt leer, sie dient nur als Konfiguration
}