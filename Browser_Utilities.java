package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser_Utilities 
{
	public static WebDriver driver;
		
	public static WebDriver getBrowser(String browserName)
	{
		switch (browserName)
		{
		case "IE":
			if(driver == null)
			{
				System.setProperty("webdriver.ie.driver", "D:\\Softwares\\Selenium\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
		
				//Log4j_Utilities.info("IE opened successfully");
		
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			break;
		case "Chrome":
			if(driver == null)
			{
				System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
				driver = new ChromeDriver();
			
				//Log4j_Utilities.info("Chorme Browser opened successfully");
			
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			break;
		case "Firefox":
			if(driver == null)
			{
				driver = new FirefoxDriver();
				
				//Log4j_Utilities.info("FireFox Browser opened successfully");
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			break;
		}
		return driver;
		}
}
