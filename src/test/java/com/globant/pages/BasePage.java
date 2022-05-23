package com.globant.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    protected WebDriverWait getWait(){
        return this.wait;
    }

    public void goBackPage(){
        this.driver.navigate().back();
    }

    protected void waitUntilPageRefreshed(){
        try{Thread.sleep(500);}catch(InterruptedException e){};
        this.wait.until(driver->this.js.executeScript("return document.readyState").equals("complete"));
    }


}
