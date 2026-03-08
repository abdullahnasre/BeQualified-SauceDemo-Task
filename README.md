🚀 SauceDemo Automation Project
![SauceDemo Automation Project](https://b1472923.smushcdn.com/1472923/wp-content/uploads/2018/08/Screen-Shot-2019-10-24-at-8.19.56-AM.png?lossy=0&strip=1&webp=1)
Inhaltsverzeichnis
Einführung

Features

Technologien

Installation

Verwendung

Projektstruktur

Einführung
Dieses Projekt dient der automatisierten Qualitätssicherung des SauceDemo-Webshops. Ziel ist es, sicherzustellen, dass kritische Benutzerpfade wie der Login, die Produktauswahl und der Checkout-Prozess fehlerfrei funktionieren. Die Suite wurde so optimiert, dass sie stabil im Headless-Modus läuft.

Features
Sicherer Login: Validierung der Benutzerführung zur Inventar-Seite.

Produkt-Management: Dynamisches Hinzufügen von Produkten zum Warenkorb basierend auf dem Namen.

Checkout-Prozess: Validierung des Subtotals und Abschluss des Kaufs.

Sortier-Logik: Überprüfung der Sortierfunktionen nach Preis und Alphabet.

Technologien
Java 21 & Maven

Selenium WebDriver (Browser-Automatisierung)

Cucumber (BDD) & TestNG (Test-Frameworks)

Page Object Model (POM) (Architektur)

Installation
Um die SauceDemo-Automatisierung lokal einzurichten, folge diesen Schritten:

Repository klonen:

Bash
git clone https://github.com/abdullahkamel/BeQualified-SauceDemo-Task.git
In das Projektverzeichnis wechseln:

Bash
cd BeQualified-SauceDemo-Task
Abhängigkeiten installieren:

Bash
mvn install
Verwendung
Führe die Tests über das Terminal aus, um die gewünschte Browser-Konfiguration zu nutzen:

Normaler Modus (Chrome):

Bash
mvn test -Dbrowser=chrome
Headless Modus (CI/CD):

Bash
mvn test -Dbrowser=chrome-headless
Projektstruktur
src/main/java/pages: Enthält Page Objects mit stabilen Locators und WebDriverWait.

src/test/java/stepDefinition: Java-Logik hinter den Gherkin-Schritten.

src/main/resources/Features: BDD-Feature-Dateien für die Regressionstests.

target/cucumber-reports/: Speicherort der generierten HTML-Reports nach dem Testlauf.