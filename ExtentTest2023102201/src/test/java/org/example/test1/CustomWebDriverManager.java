package org.example.test1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class CustomWebDriverManager {
	
	public WebDriver driver;
	public String driverType;
	public boolean incognito;
	
	public CustomWebDriverManager(String _driverType, boolean _incognito) {
		driverType = _driverType;
		incognito = _incognito;
		initDriver();
	}
	
	public WebDriver initDriver( ) {
		if("chrome".equalsIgnoreCase(driverType)) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(incognito) {
				options.addArguments("-incognito");
			}
			
			driver = new ChromeDriver(options);
		}else if("firefox".equalsIgnoreCase(driverType)) {
			driver = new FirefoxDriver();
		}else if("ie".equalsIgnoreCase(driverType)) {
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("Invalid driver type");
			System.exit(-1);
		}
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	

}
