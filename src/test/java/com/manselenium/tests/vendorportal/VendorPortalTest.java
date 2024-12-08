package com.manselenium.tests.vendorportal;
import com.manselenium.tests.AbstractTests;
import com.manselenium.tests.vendorportal.model.VendorPortalTestData;
import com.manselenium.util.Config;
import com.manselenium.util.Constants;
import com.manselenium.util.JsonUtil;
import org.manselenium.vendorportal.DashboardPage;
import org.manselenium.vendorportal.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.IOException;

public class VendorPortalTest extends AbstractTests {

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private VendorPortalTestData testData;

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    @BeforeTest
    @Parameters("testDataPath") // Parameter from TestNG XML
    public void setPageObject(String testDataPath) {
        try {
            if (driver == null) {
                throw new IllegalStateException("WebDriver is not initialized!");
            }
            this.dashboardPage = new DashboardPage(driver);
            this.loginPage = new LoginPage(driver);
            this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
        } catch (IOException e) {
            log.error("Failed to load test data from path: {}", testDataPath, e);
            throw new RuntimeException("Unable to set up test data", e);
        } catch (Exception e) {
            log.error("Unexpected error during setup", e);
            throw e;
        }
    }

    @Test
    public  void vendorLoginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "vendorLoginTest")
    public void verifyVendorDashBoardTest(){
        Assert.assertEquals(dashboardPage.getMontlyEarnings(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarnings(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "verifyVendorDashBoardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
