package com.pnt.utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    //Read the System Property Value
    //Based on which, i can create driver instance(iOS or Android)
    String devicePlatform = System.getProperty("run");
    String testTarget = System.getProperty("target");
    AppiumDriver driver;
    DesiredCapabilities capabilities;
    AppiumDriverLocalService appiumLocal;

    public AppiumDriver getDriver() throws Exception{
        if (testTarget.equalsIgnoreCase("saucelabs")){
            if (devicePlatform.equalsIgnoreCase("android")){
                driver = new AndroidDriver(new URL("https://pntsauce:194d0d1d-2b85-4eda-8249-9bcb3eecb16b@ondemand.saucelabs.com:443/wd/hub"),buildDesiredCapabilities());
            }else{
                driver = new IOSDriver(new URL("https://pntsauce:194d0d1d-2b85-4eda-8249-9bcb3eecb16b@ondemand.saucelabs.com:443/wd/hub"),buildDesiredCapabilities());
            }
        }else{
            if (devicePlatform.equalsIgnoreCase("android")){
                driver = new AndroidDriver(startAppiumServer(),buildDesiredCapabilities());
            }else{
                driver = new IOSDriver(startAppiumServer(),buildDesiredCapabilities());
            }
        }
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public DesiredCapabilities buildDesiredCapabilities(){
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout",60);
        if (testTarget.equalsIgnoreCase("saucelabs")){
            if (devicePlatform.equalsIgnoreCase("android")){
                capabilities.setCapability("appiumVersion", "1.5.3");
                capabilities.setCapability("deviceName","Android Emulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("platformVersion","5.1");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app","sauce-storage:samsclub.apk");
                capabilities.setCapability("appPackage","com.rfi.sams.android");
                capabilities.setCapability("appActivity",".main.SplashActivity");
            }else{
                capabilities.setCapability("appiumVersion", "1.6.3");
                capabilities.setCapability("deviceName","iPhone Simulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("platformVersion","10.0");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("app","sauce-storage:samsios.app");
            }
        }else{
            if (devicePlatform.equalsIgnoreCase("android")){
                capabilities.setCapability("automationName","appium");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("platformVersion","6.0.1");
                capabilities.setCapability("deviceName","Galaxy S6");
                capabilities.setCapability("browserName","");
                capabilities.setCapability("fullReset",true);
                capabilities.setCapability("app","/Users/srikanthvejendla/Desktop/samsclub.apk");
//            capabilities.setCapability("appPackage","com.sec.android.app.popupcalculator");
//            capabilities.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
                capabilities.setCapability("appPackage","com.rfi.sams.android");
                capabilities.setCapability("appActivity",".main.SplashActivity");
            }else{
                capabilities.setCapability("automationName","XCUITest");
                capabilities.setCapability("platformName","iOS");
                capabilities.setCapability("platformVersion","10.1");
                capabilities.setCapability("deviceName","iPhone Simulator");
                capabilities.setCapability("browserName","");
                capabilities.setCapability("bundleId","com.samsclub.samsiphone.beta");
                capabilities.setCapability("app","/Users/srikanthvejendla/Desktop/SamsClub.app");
            }
        }
        return capabilities;
    }

    public URL startAppiumServer(){
        appiumLocal = new AppiumServiceBuilder().usingAnyFreePort().build();
        appiumLocal.start();
        return appiumLocal.getUrl();
    }

    public void stopAppiumServer(){
        if (testTarget.equalsIgnoreCase("local")){
            appiumLocal.stop();
        }
    }


    //        srikanth@piit.us

}
