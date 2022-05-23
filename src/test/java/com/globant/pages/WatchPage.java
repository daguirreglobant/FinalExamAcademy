package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class for interact with the Watch page of ESPN.
 * @author d.aguirre
 */
public class WatchPage extends BasePage{

    @FindBy(className = "Carousel__Inner")
    WebElement carousel;

    @FindBy(css = "[aria-label=\"Close dialog\"]")
    WebElement closeButton;

    /**
     * Constructor
     * @param driver WebDriver a current session
     */
    public WatchPage(WebDriver driver){
        super(driver);
    }

    public boolean isCarouselWithCards(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(this.carousel));
        WebElement card = this.carousel.findElement(By.cssSelector("[data-carousel-id=\"0\"]"));
        String cardTitle = card.findElement(By.className("WatchTile__Title")).getText();
        String cardStreamingSource = card.findElement(By.className("WatchTile__Meta")).getText();
        return card.isDisplayed() && cardTitle.length()>0 && cardStreamingSource.length() > 0;
    }

    /**
     *
     * @return boolean if the close element of the modal is available
     */
    public boolean isCloseButton(){
        WebElement cardRef = this.carousel.findElement(By.cssSelector("[data-carousel-id=\"1\"] a"));
        cardRef.click();
        return this.waitUntilElementIsClickable(this.closeButton).isDisplayed();
    }

    public void closeModal(){
        this.waitUntilElementIsClickable(this.closeButton).click();
    }
}
