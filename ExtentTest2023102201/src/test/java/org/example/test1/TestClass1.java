package org.example.test1;

import com.aventstack.extentreports.Status;
import org.example.extentManager.ExtentManager;
import org.openqa.selenium.By;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Author: byron
 * Date: 10/22/2023 9:44 PM
 * Version: 1.0
 * Description:
 */
public class TestClass1 extends BaseTestClass {

    @Test
    public void test1() throws InterruptedException {
        test.log(Status.INFO,"Go to home page");
        driver.findElement(By.id("kw")).sendKeys("hello");
        test.log(Status.INFO,"submit");
        Thread.sleep(3000);
        test.log(Status.PASS,"Success");
    }

    @Test
    public void test2() {
        test.log(Status.INFO,"Go to login page");
        test.log(Status.INFO,"submit");
        test.log(Status.PASS,"Fail");
    }



}
