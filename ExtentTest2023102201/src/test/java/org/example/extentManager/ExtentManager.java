package org.example.extentManager;


import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
//	public static ExtentTest suite;
	public static ExtentTest test;
	
	public static void setExtent() throws IOException {
		reporter = new ExtentSparkReporter("ExtentReport" + File.separator + "MyReport.html");
		reporter.loadXMLConfig("extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
//		extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Tester", "Byron");
		extent.setSystemInfo("Project", "Baidu");
		extent.setSystemInfo("OS", "Win8");
	}

	
	public static void setTest(String testName) {
		test = extent.createTest(testName);
	}

	public static void flushReport() {
		extent.flush();
	}

}
