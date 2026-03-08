🚀 SauceDemo Automation Project
![SauceDemo Automation Project](https://b1472923.smushcdn.com/1472923/wp-content/uploads/2018/08/Screen-Shot-2019-10-24-at-8.19.56-AM.png?lossy=0&strip=1&webp=1)
Ich habe deine README.md optimiert, um sie noch professioneller zu gestalten. Da du dich bei beQualified bewirbst, habe ich den Fokus auf Qualitätsstandards und Clean Code gelegt.

Hier ist die verbesserte Version (inklusive der Korrektur deines GitHub-Links auf abdullahnasre):

🚀 SauceDemo Regression Automation Suite
📋 Inhaltsverzeichnis
Einführung

Kern-Features

Technologie-Stack

Projektstruktur

Installation & Ausführung

Reporting

📖 Einführung
Dieses Framework dient der automatisierten End-to-End-Validierung des SauceDemo Webshops. Es stellt sicher, dass kritische Geschäftsprozesse – vom Login bis zum erfolgreichen Checkout – stabil und reproduzierbar funktionieren.

Besonderer Fokus: Hohe Teststabilität durch Explicit Waits und volle CI/CD-Kompatibilität durch optimierte Headless-Ausführung.

✨ Kern-Features
Sicherer Authentifizierungspfad: Validierung des Logins und korrekte Weiterleitung zur Inventar-Seite.

Dynamisches Cart-Management: Produkte werden flexibel anhand ihrer Namen identifiziert und dem Warenkorb hinzugefügt.

Checkout-Validierung: Automatisierte Prüfung des Subtotals sowie der erfolgreichen Bestellbestätigung.

UI-Sortierprüfung: Verifizierung der Sortierreihenfolge nach Preis (Low/High) und Alphabet (A-Z/Z-A).

🛠 Technologie-Stack
Sprache: Java 21 & Maven

Browser-Steuerung: Selenium WebDriver

BDD Framework: Cucumber mit Gherkin

Test Runner: TestNG

Design Pattern: Page Object Model (POM)

📁 Projektstruktur
src/main/java/pages: Page Objects mit stabilen Locators und kapselter Logik.

src/test/java/stepDefinition: Java-Implementierung der BDD-Schritte sowie Hooks (Setup/Teardown).

src/main/resources/Features: Gherkin-Dateien für die fachliche Testbeschreibung.

target/cucumber-reports/: Speicherort für die generierten Testergebnisse.

⚙️ Installation & Ausführung
1. Repository klonen
Bash
git clone https://github.com/abdullahnasre/BeQualified-SauceDemo-Task.git
cd BeQualified-SauceDemo-Task
2. Abhängigkeiten laden
Bash
mvn clean install
3. Tests starten
Das Framework unterstützt verschiedene Browser-Modi via CLI-Parameter:

Standard (UI-Mode): mvn test -Dbrowser=chrome

Pipeline (Headless): mvn test -Dbrowser=chrome-headless

📊 Reporting
Nach jedem Testlauf wird ein detaillierter HTML-Report generiert. Dieser bietet eine visuelle Übersicht über bestandene und fehlgeschlagene Szenarien:
📍 target/cucumber-reports/regression-report.html
