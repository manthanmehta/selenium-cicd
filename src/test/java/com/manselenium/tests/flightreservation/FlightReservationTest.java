package com.manselenium.tests.flightreservation;

import com.manselenium.tests.AbstractTests;
import com.manselenium.tests.flightreservation.model.FlightReservationTestData;
import com.manselenium.util.Config;
import com.manselenium.util.Constants;
import com.manselenium.util.JsonUtil;
import org.manselenium.flightreservation.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlightReservationTest extends AbstractTests {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParamters(String testDataPath) throws IOException {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.getRegistrationPageUrl(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCred(testData.email(), testData.password());
        registrationPage.enterUserAddress(testData.street(), testData.city());
        registrationPage.clickToRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmation registrationConfirmation= new RegistrationConfirmation(driver);
        Assert.assertTrue(registrationConfirmation.isAt());
        registrationConfirmation.setGoToFlightSearchButton();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public  void flightSearchTest(){
        FlightSearchPage flightSearchPage= new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectNoOfPassenger(testData.passengersCount());
        flightSearchPage.searchFlightButton();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public  void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage= new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationPageTest(){
        FlightConfirmationPage flightConfirmationPage= new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
