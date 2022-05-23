package com.globant.tests;

import com.globant.driver.Driver;
import com.globant.helpers.User;
import com.globant.pages.HomePage;
import com.globant.pages.WatchPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

public class FinalExamTest {
    private Driver driver;
    private HomePage homePage;
    private WatchPage watchPage;


    @Parameters({"browser", "url"})
    @BeforeMethod
    public void bootstrap(String browser, String url){
        this.driver = new Driver(browser);
        this.homePage = new HomePage(driver.getDriver(), url);
    }

    @Test
    public void validateTest(){
        this.homePage.goToLoginModal();
        HashMap <String, Boolean> elementsLogin = this.homePage.getElementsLogin();
        for (String element: elementsLogin.keySet()){
            Assert.assertTrue(elementsLogin.get(element), element + " in login modal is not displayed");
        }

        this.homePage.goToSignUpSection();
        HashMap <String, Boolean> elementsSignUp = this.homePage.getElementsSignUp();
        for (String element: elementsSignUp.keySet()){
            Assert.assertTrue(elementsSignUp.get(element), element + " in Sign Up section is not displayed");
        }
        User user = User.createRandomUser();
        this.homePage.signUp(user);
        this.watchPage = this.homePage.goToWatch();

        Assert.assertTrue(this.watchPage.isCarouselWithCards(), "Carousel with at least 1 card with title and " +
                "streaming source failed");
        Assert.assertTrue(this.watchPage.isCloseButton(), "Close button doesn't appear");
        this.watchPage.closeModal();
        this.watchPage.goBackPage();
        Assert.assertEquals(user.getName(), this.homePage.getUserName(), "The username doesn't match");
        this.homePage.logOut();
        Assert.assertEquals(this.homePage.getUserName(), "", "The user is still logged in");

    }

    @AfterMethod
    public void closeTest(){
        this.driver.closeDriver();
    }

}