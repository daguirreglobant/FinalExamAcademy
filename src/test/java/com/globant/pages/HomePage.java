package com.globant.pages;

import com.globant.helpers.RandomString;
import com.globant.helpers.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class HomePage extends BasePage{

    @FindBy(id = "global-user-trigger")
    WebElement globalAccount;

    @FindBy(css = ".display-user")
    WebElement welcomeMessage;

    @FindBy(css = "[tref='/members/v3_1/login']")
    WebElement loginRef;

    @FindBy(css= "[data-testid=\"login-logo\"]")
    WebElement loginEspnLogo;

    @FindBy(id = "BtnSubmit")
    WebElement loginButton;

    @FindBy(id = "BtnCreateAccount")
    WebElement signUpRef;

    @FindBy(css = "[data-testid=\"Title\"]")
    WebElement signUpTitle;

    @FindBy(css = "[data-testid=\"InputFirstName\"]")
    WebElement inputFirstName;

    @FindBy(css = "[data-testid=\"InputLastName\"]")
    WebElement inputLastName;

    @FindBy(css = "[data-testid=\"InputEmail\"]")
    WebElement inputEmail;

    @FindBy(css = "[data-testid=\"password-new\"]")
    WebElement inputPassword;

    @FindBy(css = "[data-testid=\"BtnSubmit\"]")
    WebElement signUpButton;

    @FindBy(css = "[data-testid=\"REGISTRATION-close\"]")
    WebElement closeSignUpButton;

    @FindBy(css = ".account-management li:last-child a")
    WebElement logOutButton;

    @FindBy(css = "[href=\"http://www.espn.com/watch/\"")
    WebElement watchRef;

    public HomePage(WebDriver driver, String url){
        super(driver);
        driver.get(url);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public void goToLoginModal(){
        globalAccount.click();
        loginRef.click();
        driver.switchTo().frame("oneid-iframe");
    }

    public void goToSignUpSection(){
        // first you have to be in Login Modal
        signUpRef.click();
    }

    public void signUp(User user) {
        inputFirstName.sendKeys(user.getName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        try {Thread.sleep(500);} catch (InterruptedException e) {}
        this.getWait().until(ExpectedConditions.visibilityOf(signUpButton)).click();
    }

    public WatchPage goToWatch(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(this.watchRef));
        watchRef.click();
        return new WatchPage(this.driver);
    }

    public String getUserName(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(globalAccount));
        this.globalAccount.click();
        List<WebElement> userNameList = this.welcomeMessage.findElements(By.tagName("span"));
        if (userNameList.isEmpty()){
            return "";
        }
        String userName = userNameList.get(0).getText();
        if (userName.length()>0 && (userName.charAt(userName.length()-1) == '!')){
            userName = userName.substring(0, userName.length()-1);
        }
        return userName;
    }

    public void logOut(){
        this.getWait().until(ExpectedConditions.elementToBeClickable(globalAccount));
        this.globalAccount.click();
        this.logOutButton.click();
        this.waitUntilPageRefreshed();
    }

    public HashMap<String, Boolean> getElementsLogin(){
        HashMap<String, Boolean> elementsInLogin = new HashMap<String, Boolean>();
        elementsInLogin.put("espn logo", loginEspnLogo.isDisplayed());
        elementsInLogin.put("login button", loginButton.isDisplayed());
        elementsInLogin.put("signup button", signUpRef.isDisplayed());
        return elementsInLogin;
    }

    public HashMap<String, Boolean> getElementsSignUp(){
        HashMap<String, Boolean> elementsInSignUp = new HashMap<String, Boolean>();
        elementsInSignUp.put("title", signUpTitle.isDisplayed());
        elementsInSignUp.put("first name", inputFirstName.isDisplayed());
        elementsInSignUp.put("last name", inputLastName.isDisplayed());
        elementsInSignUp.put("email", inputEmail.isDisplayed());
        elementsInSignUp.put("password",inputPassword.isDisplayed());
        elementsInSignUp.put("signup button",signUpButton.isDisplayed());
        elementsInSignUp.put("close button",closeSignUpButton.isDisplayed());
        return elementsInSignUp;
    }

}
