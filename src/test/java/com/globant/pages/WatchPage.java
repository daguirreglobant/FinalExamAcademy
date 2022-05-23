package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WatchPage extends BasePage{

    @FindBy(className = "Carousel__Inner")
    WebElement carousel;

    @FindBy(css = "[aria-label=\"Close dialog\"]")
    WebElement closeButton;

    public WatchPage(WebDriver driver, String url){
        super(driver);
        driver.get(url);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public WatchPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCarouselWithCards(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(this.carousel));
        WebElement card = this.carousel.findElement(By.cssSelector("[data-carousel-id=\"0\"]"));
        String cardTitle = card.findElement(By.className("WatchTile__Title")).getText();
        String cardStreamingSource = card.findElement(By.className("WatchTile__Meta")).getText();
        return card.isDisplayed() && cardTitle.length()>0 && cardStreamingSource.length() > 0 ? true : false;
    }

    public boolean isCloseButton(){
        WebElement cardRef = this.carousel.findElement(By.cssSelector("[data-carousel-id=\"1\"] a"));
        cardRef.click();
        this.getWait().until(ExpectedConditions.elementToBeClickable(closeButton));
        return closeButton.isDisplayed();
    }

    public void closeModal(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }
}
