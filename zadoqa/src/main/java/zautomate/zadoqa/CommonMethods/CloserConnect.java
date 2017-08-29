package zautomate.zadoqa.CommonMethods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.Navigate;
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.utils.Directory;

public class CloserConnect extends Manipulation implements OR
{
	
	/****
	 * Name : Gobi.E
	 * Purpose : Handled get started button
	 * Date : 26.09.2016
	 * URL : Closer Connect
	 */	
	public static void getStarted(WebDriver driver) {
		try	{
			WebElement Title = driver.findElement(By.xpath(CLOSER_GET_STARTED));
			verifyElementIsPresent(driver, Title);
			WebElement GetStarted = driver.findElement(By.xpath(CLOSER_GET_STARTED));
			click(GetStarted);		
			waitForElement(driver, CLOSER_WELCOME_OK);
			WebElement Yes = driver.findElement(By.xpath(CLOSER_WELCOME_OK));
			click(Yes);				 
		}
		catch(Exception e) {
			System.out.println("Get Started");
		}
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Handled multiple URL
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	public static void getUrl(WebDriver driver) {
		driver.get(Directory.CLOSER_CONNECT_URL);		
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Login Closer Connect Application
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	public static String Login(WebDriver driver) {
		waitForElement(driver, CLOSER_PAGE_LOGO);
		WebElement UserName = driver.findElement(By.name(CLOSER_USER_NAME_TEXTBOX));
		sendKeys(UserName, Directory.CLOSER_CONNECT_USERNAME);
		WebElement PassWord = driver.findElement(By.name(CLOSER_PASS_WORD_TEXTBOX));
		sendKeys(PassWord, Directory.CLOSER_CONNECT_PASSWORD);		
		WebElement LoginButton = driver.findElement(By.xpath(CLOSER_SIGN_IN_BUTTON));
		click(LoginButton);	
		getStarted(driver);
		Navigate.waitTime(driver, "4");
		WebElement LoggedUsername = driver.findElement(By.xpath(CLOSER_LOGGED_USER_NAME));
		verifyElementIsPresent(driver, LoggedUsername);
		String loggedusername = LoggedUsername.getText();
		System.out.println("Name :"+loggedusername);
		return loggedusername;
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Login Closer Connect Application
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	public static String LoginWithUrl(WebDriver driver) {
		driver.get(Directory.CLOSER_CONNECT_URL);
		driver.manage().window().maximize();
		wait(driver, "3");		
		WebElement LoginLink = driver.findElement(By.xpath(CLOSER_LOGIN_LINK));
		verifyElementIsPresent(driver, LoginLink);
		WebElement LoginLink1 = driver.findElement(By.xpath(CLOSER_LOGIN_LINK));
		click(LoginLink1);
		wait(driver, "1");
		waitForElement(driver, CLOSER_PAGE_LOGO);
		WebElement UserName = driver.findElement(By.name(CLOSER_USER_NAME_TEXTBOX));
		sendKeys(UserName, Directory.CLOSER_CONNECT_USERNAME);
		WebElement PassWord = driver.findElement(By.name(CLOSER_PASS_WORD_TEXTBOX));
		sendKeys(PassWord, Directory.CLOSER_CONNECT_PASSWORD);		
		WebElement LoginButton = driver.findElement(By.xpath(CLOSER_SIGN_IN_BUTTON));
		click(LoginButton);	
		getStarted(driver);
		Navigate.waitTime(driver, "4");
		WebElement LoggedUsername = driver.findElement(By.xpath(CLOSER_LOGGED_USER_NAME));
		verifyElementIsPresent(driver, LoggedUsername);
		String loggedusername = LoggedUsername.getText();
		System.out.println("Name :"+loggedusername);
		return loggedusername;
	}

	/****
	 * Name : Gobi.E
	 * Purpose : Logout Closer Connect Application
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	/****
	 * Name : Gobi.E
	 * Purpose : Login Closer Connect Application
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	public static String zohoLogin(WebDriver driver) {
		
		driver.get(Directory.ZOHO_URL);
		driver.manage().window().maximize();
		wait(driver, "3");			
		WebElement loginFrame = driver.findElement(By.xpath(ZOHO_LOGIN_FRAME));
		Navigate.switchToFrame(driver, loginFrame);		
		WebElement UserName = driver.findElement(By.name(ZOHO_USERNAME_TEXTBOX));
		sendKeys(UserName, Directory.ZOHO_USERNAME);
		WebElement PassWord = driver.findElement(By.name(ZOHO_PASSWORD_TEXTBOX));
		sendKeys(PassWord, Directory.ZOHO_PASSWORD);		
		WebElement LoginButton = driver.findElement(By.xpath(ZOHO_SIGN_IN_BUTTON));
		click(LoginButton);			
		wait(driver, "30");		
		Navigate.switchToDefaultFrame(driver);		
		WebElement InboxTab = driver.findElement(By.xpath(ZOHO_INBOX_TAB));
		click(InboxTab);
		//actionClick(driver, InboxTab);
		wait(driver, "3");	
		//Navigate.dismissAlert(driver);
		wait(driver, "1");	
		WebElement Unread = driver.findElement(By.xpath(ZOHO_INBOX_UNREAD_MAIL));
		verifyElementIsPresent(driver, Unread);
		WebElement unreadMail = driver.findElement(By.xpath(ZOHO_INBOX_UNREAD_MAIL));
		click(unreadMail);
		wait(driver, "2");			 
		WebElement publishUrlLink = driver.findElement(By.xpath(PUBLISH_URL_LINK));
		String URL = publishUrlLink.getText();
		Navigate.handlesecondWindow(driver, publishUrlLink);	
		wait(driver, "4");		
		return URL;
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Logout Closer Connect Application
	 * Date : 27.09.2016
	 * URL : Closer Connect
	 */	
	public static void Logout(WebDriver driver) {
		driver.switchTo().defaultContent();
		WebElement LoggedUsername = driver.findElement(By.xpath(CLOSER_LOGGED_USER_NAME));
		verifyElementIsPresent(driver, LoggedUsername);
		jsClickByXPath(driver, CLOSER_LOGGED_USER_NAME);
		wait(driver, "1");
		jsClickByXPath(driver, CLOSER_LOGOUT_BUTTON);		
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Upload multiple type of files and return file name without extention
	 * Date : 26.09.2016
	 * URL : Closer Connect
	 */
	public static String uploadFileSendkeysReturnFileName(WebElement element, String imageName){
		String filepath=Directory.uploadFilePath+imageName;
		File fileName = new File(filepath);
		String absoultePath = fileName.getAbsoluteFile().toString();
		Manipulation.sendKeys(element,absoultePath);	
		String FileName = fileName.getName().toString();		
		int Extention = FileName.lastIndexOf('.');
		FileName = FileName.substring(0, Extention);		
		return FileName;
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose : Clear text field and type values (Handled repeated Values)
	 * Date : 26.09.2016
	 * URL : Closer Connect
	 */
	public static String actionClearAndType(WebDriver driver, WebElement webElement, String keysToSend) {
		try {
			if(webElement.isEnabled()) {		
				Actions act = new Actions(driver);
				act.click(webElement);
				act.sendKeys(webElement, Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).build().perform();
				try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
				act.sendKeys(webElement, keysToSend).build().perform(); 				
				Actions action = new Actions(driver);
				action.click(webElement);
				action.sendKeys(webElement, Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).build().perform();
				try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
				action.sendKeys(webElement, keysToSend).build().perform(); 				
			}    	   
		} catch (StaleElementReferenceException e) { }		
		return keysToSend;
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose :  Delete Sales Project
	 * Date : 30.09.2016
	 * URL : Closer Connect
	 */
	public static void deleteSalesProject(WebDriver driver, String projectName) {
		WebElement mySales = driver.findElement(By.xpath(MY_SALES_PROJECT_TAB));
		click(mySales);	
		wait(driver, "2");
		WebElement ProjectTitle = driver.findElement(By.xpath(MY_SALES_PROJECT_PAGE_TITLE));
		verifyElementIsPresent(driver, ProjectTitle);		
		wait(driver, "1");
		WebElement actionButton = driver.findElement(By.xpath(PROJECT_ACTION_BUTTON+projectName+PROJECT_ACTION_BUTTON_1));
		click(actionButton);	
		wait(driver, "2");
		WebElement deleteButton = driver.findElement(By.xpath(PROJECT_DELETE_BUTTON));
		click(deleteButton);		
		wait(driver, "2");
		WebElement yesButton = driver.findElement(By.xpath(PROJECT_DELETE_YES_BUTTON));
		click(yesButton);		
		wait(driver, "4");		
	}
	
	/****
	 * Name : Gobi.E
	 * Purpose :  Delete Learning Project
	 * Date : 30.09.2016
	 * URL : Closer Connect
	 */
	public static void deleteLearningProject(WebDriver driver, String projectName) {
		WebElement mySales = driver.findElement(By.xpath(MY_LEARNING_PROJECT_TAB));
		click(mySales);	
		wait(driver, "2");
		WebElement ProjectTitle = driver.findElement(By.xpath(MY_LEARNING_PROJECT_PAGE_TITLE));
		verifyElementIsPresent(driver, ProjectTitle);		
		wait(driver, "1");
		WebElement actionButton = driver.findElement(By.xpath(PROJECT_ACTION_BUTTON+projectName+PROJECT_ACTION_BUTTON_1));
		click(actionButton);	
		wait(driver, "2");
		WebElement deleteButton = driver.findElement(By.xpath(PROJECT_DELETE_BUTTON));
		click(deleteButton);		
		wait(driver, "2");
		WebElement yesButton = driver.findElement(By.xpath(PROJECT_DELETE_YES_BUTTON));
		click(yesButton);		
		wait(driver, "4");		
	}
	/****
	 * Name : Gobi.E
	 * Purpose : Delete Sales Project
	 * Date : 28.09.2016
	 * URL : Closer Connect
	 */
	public static String validateViewsandDownloads(String BeforeValue, String AfterValue) {
		int refStepBefore = new Integer(BeforeValue);
		int refStepAfter = new Integer(AfterValue);		
		int finalvalue = refStepBefore + 1;
		String Report = "";
		if(finalvalue==refStepAfter) {
			Report = "Value is Successfully Matched";
		}
		else {
			Report = "Value is Not Matched";
		}
		return Report;		
	}
	
	/**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:05-Oct-2016
	  * Modified date:05-Oct-2016
	  * Purpose: Mouse over on image
	  * @return
	  * @throws InterruptedException 
	 * @throws IOException 
	  */ 
	public static String Before = null;
	public static void mouseOveronImage(WebDriver driver) throws InterruptedException, IOException {
		WebElement Image = driver.findElement(By.id("published"));
		wait(driver, "3");
		Actions builder = new Actions(driver);
		builder.moveToElement(Image, 150, 40).build().perform();
		//builder.clickAndHold(Image).moveByOffset(0,0).moveByOffset(150,40).release().perform();
		wait(driver, "3");
	
		
	}
}
