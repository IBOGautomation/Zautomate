package zautomate.zadoqa.apitesting;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SeleniumGrid 
{
	
	
	  @Test
	     public void mailTest() throws MalformedURLException, InterruptedException{

	              DesiredCapabilities dr=null;
	              System.out.println("CHROME DRIVER");
	              System.setProperty("webdriver.chrome.driver","./Lib/chromedriver.exe");
	              dr=DesiredCapabilities.chrome();

	              dr.setBrowserName("chrome");

	              dr.setPlatform(Platform.WINDOWS);


	              //RemoteWebDriver driver=new RemoteWebDriver(new URL(("http://192.168.3.38:6666/wd/hub")&&("http://192.168.3.18:6666/wd/hub")), dr);
	              RemoteWebDriver driver=new RemoteWebDriver(new URL("http://192.168.3.38:4444/wd/hub"), dr);
	              
	              Thread.sleep(2000);

	              driver.navigate().to("http://gmail.com");
	              Thread.sleep(2000);
	              driver.findElement(By.xpath("//input[@id='Email']")) .sendKeys("ramyapalaniswamy17@gmail.com");
	              driver.findElement(By.id("next")).click();
	              Thread.sleep(2000);
	              driver.findElement(By.xpath("//input[@id='Passwd']")) .sendKeys("krishram");
	              driver.findElement(By.id("signIn")).click(); 
	              Thread.sleep(2000);
	              driver.findElement(By.xpath("//span[@class='gb_3a gbii']")).click();
	              Thread.sleep(2000);
	              driver.findElement(By.xpath("//a[text()='Sign out']")).click();
	              Thread.sleep(1000);
	              
	              driver.close();

	     }

}
