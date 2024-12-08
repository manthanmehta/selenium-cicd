package org.manselenium.flightreservation;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmation extends AbstractMethods {


    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    public RegistrationConfirmation(WebDriver driver) {
        super(driver);
    }

    public void setGoToFlightSearchButton(){
        this.goToFlightSearchButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }
}
