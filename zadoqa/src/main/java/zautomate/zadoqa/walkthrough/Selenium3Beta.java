package zautomate.zadoqa.walkthrough;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class Selenium3Beta 
{
	
	@Test
	public static void setup() throws InterruptedException
	{
		System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		WebDriver driver = new InternetExplorerDriver(ieCapabilities);
		
		
		driver.get("www.google.com");
		Thread.sleep(3000);
		
	}
	

}
