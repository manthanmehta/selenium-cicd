package org.manselenium.vendorportal;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractMethods {

    private static  final Logger log= LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id = "monthly-earning")
    private WebElement montlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(id="dataTable_info")
    private WebElement dataTableInfo;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searhInput;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modaLogout;

    public String getMontlyEarnings(){
        this.wait.until(ExpectedConditions.visibilityOf(this.montlyEarningElement));
        return this.montlyEarningElement.getText();
    }
    public String getAnnualEarnings(){
        return this.annualEarningElement.getText();
    }
    public String getProfitMargin(){
        return this.profitMarginElement.getText();
    }
    public String getAvailableInventory(){
        return this.availableInventoryElement.getText();
    }
    public void searchOrderHistory(String keyword){
        this.wait.until(ExpectedConditions.visibilityOf(this.searhInput));
        this.searhInput.sendKeys(keyword);
    }
    public int getSearchResultCount(){
        String [] arr= this.dataTableInfo.getText().split(" ");
        int count= Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }
    public void logout(){
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modaLogout));
        this.modaLogout.click();
    }
    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.montlyEarningElement));
        return this.montlyEarningElement.isDisplayed();
    }
}
