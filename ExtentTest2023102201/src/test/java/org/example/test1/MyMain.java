package org.example.test1;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Author: byron
 * Date: 3/13/2024 10:09 PM
 * Version: 1.0
 * Description:
 */
public class MyMain {
    public static void main(String[] args) {
        ExtentReports reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Spark/Spark1.html");
        reports.attachReporter(spark);
        ExtentTest test = reports.createTest("Class1");
        ExtentTest node = test.createNode("node 1");
        node.info("Start1...");
        node.pass("Success1");
        node.info("End1");
        reports.flush();
        node = test.createNode("node 2");
        node.info("Start2...");
        node.pass("Success2");
        node.info("End2");
        ExtentTest scenario = test.createNode(Scenario.class, "Jeff returns a faulty microwave");
        scenario.createNode(Given.class, "Jeff has bought a microwave for $100").pass("pass");
        scenario.createNode(And.class, "he has a receipt").pass("pass");
        scenario.createNode(When.class, "he returns the microwave").pass("pass");
        scenario.createNode(Then.class, "Jeff should be refunded $100").fail("fail");
//        reports.flush();


        test = reports.createTest("Class2");
        node = test.createNode("node 3");
        node.info("Start3...");
        node.pass("Success3");
        node.info("End3");
        reports.flush();
        node = test.createNode("node 4");
        node.info("Start4..");
        node.pass("Success4");
        node.info("End4");
        reports.flush();

    }
}
