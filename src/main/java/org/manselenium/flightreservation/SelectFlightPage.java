package org.manselenium.flightreservation;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightPage extends AbstractMethods {

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButtom;

    public SelectFlightPage(WebDriver driver) {
        super(driver);
    }
    public void selectFlights(){
        int random= ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
        this.departureFlightOptions.get(random).click();
        this.arrivalFlightOptions.get(random).click();
    }

    public void confirmButton(){
        this.confirmFlightButtom.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButtom));
        return this.confirmFlightButtom.isDisplayed();
    }


}
