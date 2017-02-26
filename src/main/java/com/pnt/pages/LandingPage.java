package com.pnt.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage{

    AppiumDriver driver;

    public LandingPage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.rfi.sams.android:id/sign_in_button")
    @iOSFindBy(accessibility = "Sign In")
    private MobileElement btnSignIn;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sam's Club\")")
    @iOSFindBy(accessibility = "logo-sams")
    private MobileElement btnMenu;

    public SignInPage clickSignInBtn(){
        btnSignIn.click();
        return new SignInPage(driver);
    }

    public MenuOptionsPage clickMenuBtn(){
        btnMenu.click();
        return new MenuOptionsPage(driver);
    }





}
