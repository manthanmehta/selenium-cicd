package org.manselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractMethods {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractMethods(WebDriver driver){
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }

   public abstract boolean isAt();
}
