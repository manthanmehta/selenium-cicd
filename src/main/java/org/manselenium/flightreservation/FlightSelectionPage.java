package org.manselenium.flightreservation;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractMethods {
    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }
    public void selectFlights(){
        int random= ThreadLocalRandom.current().nextInt(0,departureFlightOptions.size());
        this.departureFlightOptions.get(random).click();
        this.arrivalFlightOptions.get(random).click();
    }

    public void confirmFlight(){

        try {
            System.out.println("Initiating search flight button click...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the search button to be clickable
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm-flights")));

            // Scroll into view using JavaScript and attempt to click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchButton);

            int attempts = 0;
            while (attempts < 3) {
                try {
                    searchButton.click(); // Try clicking the button
                    System.out.println("Confirm button clicked successfully.");
                    break; // Exit the loop on successful click
                } catch (ElementNotInteractableException e) {
                    System.out.println("Attempt " + (attempts + 1) + " failed. Retrying...");
                    Thread.sleep(1000); // Add delay before retrying
                    attempts++;
                }
            }

            if (attempts == 3) {
                throw new ElementNotInteractableException("Failed to interact with the search button after 3 attempts.");
            }
        } catch (ElementNotInteractableException | InterruptedException e) {
            System.out.println("An error occurred while clicking the search button: " + e.getMessage());

        }
    }
       // this.confirmFlightButton.click();


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }
}
