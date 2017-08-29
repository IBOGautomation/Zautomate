package zautomate.zadoqa.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import zautomate.zadoqa.utils.Platform;
/**
 * Class to configure and get Details for Chrome Browser
 * 
 * @author babu.sathiyaraja
 * 
 */
public class ChromeBrowser implements Browser {

	private WebDriver chromeDriver;

	public ChromeBrowser() {
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS")){
		System.setProperty(ApplicationConstants.CHROME_DRIVER_NAME,
				ApplicationConstants.CHROME_DRIVER_PATH_WINDOWS);
		}
		else if(OSName.contains("MAC")){
			System.setProperty(ApplicationConstants.CHROME_DRIVER_NAME,
					ApplicationConstants.CHROME_DRIVER_PATH_MAC_OS);
		}
		//chromeDriver = new ChromeDriver();	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("disable-infobars");
		options.addArguments("--no-proxy-server");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		chromeDriver = new ChromeDriver(options);
		
	}

	public String getBrowserName() {
		return ((RemoteWebDriver) chromeDriver).getCapabilities()
				.getBrowserName();
	}

	public String getVersion() {
		return ((RemoteWebDriver) chromeDriver).getCapabilities().getVersion();
	}

	public WebDriver getDriver() {
		return chromeDriver;
	}

}
