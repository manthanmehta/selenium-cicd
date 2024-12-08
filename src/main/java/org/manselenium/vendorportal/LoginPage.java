package org.manselenium.vendorportal;

import org.manselenium.AbstractMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractMethods {

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String usernameInput, String passwordInput){
        this.userName.sendKeys(usernameInput);
        this.password.sendKeys(passwordInput);
        this.loginButton.click();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
    }
}
