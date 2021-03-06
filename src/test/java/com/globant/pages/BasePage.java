package com.globant.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for basic functionalities
 * @author d.aguirre
 */
public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 20);
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;

    }

    public void goBackPage(){
        this.driver.navigate().back();
    }

    protected WebDriverWait getWait(){
        return this.wait;
    }

    protected void switchToFrame(String idFrame){
        this.driver.switchTo().frame(idFrame);
    }

    protected WebElement waitUntilElementIsClickable(WebElement element){
        return this.wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilPageRefreshed(){
        // Although this is a thread sleep, it's being used so the wait until page is refreshed works
        try{Thread.sleep(500);}catch(InterruptedException e){}
        this.wait.until(driver->this.js.executeScript("return document.readyState").equals("complete"));
    }
}
