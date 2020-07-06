package Tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {

	
	public static WebDriver StartBrowser(WebDriver driver) {
		
		switch (Settings.browser){
			case "Chrome" :
				driver = StartChrome(driver);
				break;
			
			case "FireFox" :
				driver = StartFirefox(driver);
				break;
				
		}
		
		return driver;
	}
	
	

	private static WebDriver StartChrome(WebDriver driver) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Temp\\79.0.3945.36 chromedriver_win32\\chromedriver.exe");
		//Set Chrome options
		ChromeOptions options = new ChromeOptions();
		
		//Set Capbilties
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		//Start WebDriver
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	}
	
	private static WebDriver StartFirefox(WebDriver driver) {
		
		System.setProperty("webdriver.gecko.driver", "I:\\Internet Banking\\IB__Pilot\\Testing\\Regression Testing\\Selenium Webdriver\\Firefox driver\\geckodriver-v0.26.0-win64/geckodriver.exe");
		
		//Start WebDriver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		return driver;
	}
	
}
