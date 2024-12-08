package org.manselenium.flightreservation;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;

public class FlightSearchPage extends AbstractMethods {

    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    //    @FindBy(id = "search-flights")
//    private  WebElement searchFlightButton;
    private final WebElement searchFlightButton = driver.findElement(By.id("search-flights"));

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchFlightButton));
        return this.searchFlightButton.isDisplayed();

    }

    public void selectNoOfPassenger(String noOfPassengers) {
        Select passengers = new Select(this.passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlightButton() {
            try {
                System.out.println("Initiating search flight button click...");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Wait for the search button to be clickable
                WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-flights")));

                // Scroll into view using JavaScript and attempt to click
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchButton);

                int attempts = 0;
                while (attempts < 3) {
                    try {
                        searchButton.click(); // Try clicking the button
                        System.out.println("Search button clicked successfully.");
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


    }

