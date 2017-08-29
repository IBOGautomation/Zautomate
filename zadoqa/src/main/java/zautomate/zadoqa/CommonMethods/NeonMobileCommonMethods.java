package zautomate.zadoqa.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.Navigate;
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.utils.Directory;

public class NeonMobileCommonMethods extends Manipulation implements OR
{
	
	public static void login(WebDriver driver) {
		WebElement Title = driver.findElement(By.xpath(NEON_LOGIN_PAGE_TITLE));
		verifyElementIsPresent(driver, Title);
		WebElement UserName = driver.findElement(By.id(NEON_USERNAME));
		sendKeys(UserName, Directory.NEON_USER_NAME);
		WebElement PassWord = driver.findElement(By.id(NEON_PASSWORD));
		sendKeys(PassWord, Directory.NEON_PASS_WORD);
		WebElement LoginButton = driver.findElement(By.xpath(NEON_LOGIN_BUTTON));
		click(LoginButton);			
		Navigate.waitTime(driver, "10");		
		selectBusiness(driver, "Zado Test", "Zado Test");
	}
	
	public static void cropImage(WebDriver driver) {
		//div[@class='jcrop-tracker']
		WebElement leftTopCornerOfTheImage = driver.findElement(By.xpath(NEON_CROP_IMAGE_CORNER)); 
		Actions actions = new Actions(driver); 
		actions.clickAndHold(leftTopCornerOfTheImage).moveByOffset(800, 400).build().perform(); 
	}
	
	public static void DeleteMenu(WebDriver driver) {
		try	{
			driver.findElement(By.xpath(NEON_MENU_DELETE)).click();
			wait(driver, "2");
			driver.findElement(By.xpath(NEON_MENU_CONFIRM_DELETE)).click();
			wait(driver, "2");
		}
		catch (Exception e)	{}
	}
	
	public static void selectMenu(WebDriver driver,String inputdata) {
		try	{
			WebElement Menu = driver.findElement(By.xpath(NEON_MENU_ITEM_SELECT+inputdata+NEON_MENU_ITEM_SELECT1));
			verifyElementIsPresent(driver, Menu);	
			wait(driver, "1");
			click(Menu);
			wait(driver, "4");
		}
		catch (Exception e)	{ }
	}
	
	public static void selectBusiness(WebDriver driver,String inputdata, String Location) {
		WebElement CurrentBusiness = driver.findElement(By.xpath(NEON_SELECT_BUSINESS));
		click(CurrentBusiness);	
		wait(driver, "2");
		sendKeys(CurrentBusiness, inputdata);			
		wait(driver, "2");
		WebElement SelectBusiness = driver.findElement(By.xpath(SELECT_BUSINESS));
		click(SelectBusiness);
		wait(driver, "3");
		/*WebElement CurrentBusinesse = driver.findElement(By.xpath(NEON_SELECT_LIST_BOX+inputdata+NEON_SELECT_LIST_BOX1));
		click(CurrentBusinesse);
		wait(driver, "3");*/
		WebElement BusinessLocation = driver.findElement(By.xpath(NEON_SELECT_BUSINESS_LOCATION));
		click(BusinessLocation);	
		wait(driver, "2");
		sendKeys(BusinessLocation, Location);
		wait(driver, "2");
		WebElement SelectBusiness1 = driver.findElement(By.xpath(SELECT_BUSINESS));
		click(SelectBusiness1);	
		wait(driver, "3");
		/*WebElement SelectBusiness1 = driver.findElement(By.xpath(NEON_SELECT_LIST_BOX+Location+NEON_SELECT_LIST_BOX1));
		click(SelectBusiness1);*/
		
	}

}
