package zautomate.zadoqa.CommonMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import zautomate.zadoqa.config.FirefoxBrowser;

public class General 
{
	public static WebDriver driver1;

	public static void firefo(AndroidDriver driver,WebElement element) throws InterruptedException
	{
		driver1 = new FirefoxDriver();

		driver1.get("http://erail.in/");

		driver1.manage().window().maximize();

		Thread.sleep(8000);





	}

	public static void click() throws InterruptedException
	{
		String chennai = driver1.findElement(By.xpath("//a[@href='http://chennailocaltrain.info']")).getText();

		System.out.println("value = " + chennai);

		driver1.findElement(By.xpath("//a[@href='http://chennailocaltrain.info']")).click();

		Thread.sleep(5000);
	}


}
