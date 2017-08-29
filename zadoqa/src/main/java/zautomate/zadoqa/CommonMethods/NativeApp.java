package zautomate.zadoqa.CommonMethods;

import io.appium.java_client.android.AndroidDriver;
 
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import zautomate.zadoqa.utils.Directory;



 

 
//@Test
public class NativeApp 
{
	
	private AndroidDriver driver;
	
	//@Test
	public void setUp() throws Exception
	{
		File classpathRoot = new File(System.getProperty("user.dir"));
	    File appDir = new File(classpathRoot, "./lib");
	    File app = new File(appDir, "ERail.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName","D2202");
		capabilities.setCapability("platformVersion", "4.4.4");
		//if(Directory.MOBILE_APP_TYPE.equalsIgnoreCase("Web")) {
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome"); }
		//else {
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "in.erail.m"); 
		//}
	
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
				
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		File classpathRoot = new File(System.getProperty("user.dir"));
	    File appDir = new File(classpathRoot, "./lib");
	    File app = new File(appDir, "ERail.apk");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "D2202");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.4");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.erail.m");
	//	capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.erail.m");
		 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		    driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);*/
		    System.out.println("Installed");
	}
	
	
}
