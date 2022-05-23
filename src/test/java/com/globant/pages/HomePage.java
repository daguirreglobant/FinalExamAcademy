package com.globant.pages;

import com.globant.helpers.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

/**
 * Class for interact with the Home Page of ESPN.
 * @author d.aguirre
 */
public class HomePage extends BasePage{

    @FindBy(css = "[data-testid=\"REGISTRATION-close\"]")
    WebElement closeSignUpButton;

    @FindBy(id = "global-user-trigger")
    WebElement globalAccount;

    @FindBy(css = "[data-testid=\"InputFirstName\"]")
    WebElement inputFirstName;

    @FindBy(css = "[data-testid=\"InputLastName\"]")
    WebElement inputLastName;

    @FindBy(css = "[data-testid=\"InputEmail\"]")
    WebElement inputEmail;

    @FindBy(css = "[data-testid=\"password-new\"]")
    WebElement inputPassword;

    @FindBy(id = "BtnSubmit")
    WebElement loginButton;

    @FindBy(css= "[data-testid=\"login-logo\"]")
    WebElement loginEspnLogo;

    @FindBy(css = "[tref='/members/v3_1/login']")
    WebElement loginRef;

    @FindBy(css = ".account-management li:last-child a")
    WebElement logOutButton;

    @FindBy(css = "[data-testid=\"BtnSubmit\"]")
    WebElement signUpButton;

    @FindBy(id = "BtnCreateAccount")
    WebElement signUpRef;

    @FindBy(css = "[data-testid=\"Title\"]")
    WebElement signUpTitle;

    @FindBy(css = "[href=\"http://www.espn.com/watch/\"")
    WebElement watchRef;

    @FindBy(css = ".display-user")
    WebElement welcomeMessage;

    /**
     * Constructor
     * @param driver WebDriver
     * @param url String
     */
    public HomePage(WebDriver driver, String url){
        super(driver);
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }

    public void goToLoginModal(){
        this.goToGlobalAccount();
        this.loginRef.click();
        this.switchToFrame("oneid-iframe");
    }

    public void goToSignUpSection(){
        // first you have to be in Login Modal
        this.signUpRef.click();
    }

    /**
     *
     * @return WatchPage so the current session uses a different page
     */
    public WatchPage goToWatch(){
        waitUntilElementIsClickable(this.watchRef).click();
        return new WatchPage(this.driver);
    }

    /**
     * Fill the form and signs up a user
     * @param user User object with basic information
     */
    public void signUp(User user) {
        this.inputFirstName.sendKeys(user.getName());
        this.inputLastName.sendKeys(user.getLastName());
        this.inputEmail.sendKeys(user.getEmail());
        this.inputPassword.sendKeys(user.getPassword());
        try {Thread.sleep(500);} catch (InterruptedException e) {}
        waitUntilElementIsClickable(this.signUpButton).click();
    }

    public void logOut(){
        this.goToGlobalAccount();
        this.logOutButton.click();
        this.waitUntilPageRefreshed();
    }

    /**
     *
     * @return String the name of the user if exists, otherwise empty string
     */
    public String getUserName(){
        this.goToGlobalAccount();
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

    /**
     *
     * @return HashMap with the elements of the login Modal
     */
    public HashMap<String, Boolean> getElementsLogin(){
        HashMap<String, Boolean> elementsInLogin = new HashMap<>();
        elementsInLogin.put("espn logo", this.loginEspnLogo.isDisplayed());
        elementsInLogin.put("login button", this.loginButton.isDisplayed());
        elementsInLogin.put("signup button", this.signUpRef.isDisplayed());
        return elementsInLogin;
    }

    /**
     *
     * @return HashMap with the displayed info of the elements on signup form
     */
    public HashMap<String, Boolean> getElementsSignUp(){
        HashMap<String, Boolean> elementsInSignUp = new HashMap<>();
        elementsInSignUp.put("title", this.signUpTitle.isDisplayed());
        elementsInSignUp.put("first name", this.inputFirstName.isDisplayed());
        elementsInSignUp.put("last name", this.inputLastName.isDisplayed());
        elementsInSignUp.put("email", this.inputEmail.isDisplayed());
        elementsInSignUp.put("password", this.inputPassword.isDisplayed());
        elementsInSignUp.put("signup button", this.signUpButton.isDisplayed());
        elementsInSignUp.put("close button", this.closeSignUpButton.isDisplayed());
        return elementsInSignUp;
    }

    private void goToGlobalAccount(){
        waitUntilElementIsClickable(this.globalAccount).click();
    }

}
