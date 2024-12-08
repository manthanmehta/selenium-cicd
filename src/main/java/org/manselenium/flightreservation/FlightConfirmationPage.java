package org.manselenium.flightreservation;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractMethods {
    private  static  final Logger log= LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;


    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.totalPriceElement));
        return this.totalPriceElement.isDisplayed();
    }

    public String getPrice(){
        String confirmation=this.flightConfirmationElement.getText();
        String price=this.totalPriceElement.getText();
        log.info("Flight Confirmation : {}", confirmation);
        log.info("Total Price : {}", price);
        return price;
    }
}
