package org.example.test1;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.example.extentManager.ExtentManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Author: byron
 * Date: 10/23/2023 10:12 PM
 * Version: 1.0
 * Description:
 */
public class BaseTestClass extends ExtentManager {

    public static WebDriver driver;
    public static Properties pros;


    @BeforeSuite
    public void beforeSuite() {
            pros = new ConfigManager("config.properties").getPros();
            try {
                setExtent();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            driver = new CustomWebDriverManager(pros.getProperty("browserType"), false).getWebDriver();
    }

    @BeforeTest()
    public void beforeTest() {
        driver.get(pros.getProperty("page_url"));
    }

    @AfterTest()
    public void afterTest() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println(method);
        ExtentManager.setTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " is failed", ExtentColor.RED));
            test.log(Status.INFO,MarkupHelper.createLabel(result.getThrowable() + "- Test Case Failed", ExtentColor.RED));

            //test.addScreenCaptureFromPath(getScreenShot(result.getName()), "Picture:");
            //test.fail("Attached picture:",MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
            test.log(Status.INFO, "Attached picture:", MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(Status.PASS, "Test case:" + result.getName() + " is passed");
        } else if (ITestResult.SKIP == result.getStatus()) {
            //node.log(Status.SKIP, "Test case:" + result.getName() + " is skipped");
            test.log(Status.SKIP,MarkupHelper.createLabel(result.getThrowable() + "- Test Case Skipped", ExtentColor.ORANGE));
            test.log(Status.INFO, "Attached picture:",MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
        }

        flushReport();
    }


    public static String getScreenShot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String dateTime = DateTimeClass.getDateTime();
        fileName = fileName + "_" + dateTime + ".png";
        String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName;
        //String imageUrl = "http://127.0.0.1:18080/jenkins/job/ExtentDemo1/ws/screenshots/" + fileName;
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (Exception e) {
            e.getMessage();
        }

        //return imageUrl;
        return destination;
    }
}
