package zautomate.zadoqa.CommonMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction.Button;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.Navigate;
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.enums.LogAs;
import zautomate.zadoqa.reports.CaptureScreen;
import zautomate.zadoqa.reports.CaptureScreen.ScreenshotOf;
import zautomate.zadoqa.util.CommandUtils;
import zautomate.zadoqa.utils.Directory;

public class CommonMethods extends Manipulation implements OR	{

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:02-May-2016
	 * Modified date:03-May-2016
	 * Purpose: Close multiple tabs in steris application
	 * @return
	 */	
	public static String sterisCloseTab(WebDriver driver,String inputData) 	{		
		int TabCount = driver.findElements(By.xpath(CLOSE_TAB+"*"+CLOSE_TAB1)).size();
		int TotalTabs = TabCount+1;
		for(int i=1;i<=TabCount;i++) {
			int CurrentTabs = TotalTabs-i;
			WebElement CloseTab = driver.findElement(By.xpath(CLOSE_TAB+CurrentTabs+CLOSE_TAB1));
			click(CloseTab);
			wait(driver, "1");
		}		
		return TabCount+" tab is closed";		
	}
	
	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:03-May-2016
	 * Modified date:03-May-2016
	 * Purpose: Launch steris url
	 * @return
	 */	
	public static void URL(WebDriver driver){
		driver.get(Directory.STERIS_URL);		
	}
	
	/**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:03-May-2016
	  * Modified date:03-May-2016
	  * Purpose: Login into the steris application
	  * @return
	  * @throws InterruptedException 
	  */ 
	public static void sterisLogin(WebDriver driver) {
		driver.get(Directory.STERIS_URL);
		driver.manage().window().maximize();
		wait(driver, "3");
		WebElement Logo = driver.findElement(By.xpath(STERIS_LOGO));
		verifyElementIsPresent(driver, Logo);
		WebElement UserName = driver.findElement(By.name(USER_NAME));
		sendKeys(UserName, Directory.STERIS_USER_NAME);
		WebElement PassWord = driver.findElement(By.name(PASS_WORD));
		sendKeys(PassWord, Directory.STERIS_PASS_WORD);
		WebElement LoginButton = driver.findElement(By.xpath(LOGIN_BUTTON));
		//click(LoginButton);	
		jsClickByXPath(driver, LOGIN_BUTTON);
		Navigate.waitTime(driver, "8");
	}
	
	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:04-May-2016
	 * Modified date:04-May-2016
	 * Purpose: Validate color variations and select procedure 
	 * @return
	 */	
	public static String OR = null; 
	public static String Stills = null; 
	public static String Videos = null; 
	public static String MRN = null;
	public static String procedureName = null; 
	public static String colorVerification(WebDriver driver) {
		int size = driver.findElements(By.xpath("//html//table[*]/tbody//tr/td[13]/div")).size();			
		for(int i=1;i<=size;i++) {
			String Status = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[4]/div")).getText();			
			if(Status.equalsIgnoreCase("Proc Ends")) {
				OR = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[3]/div")).getText();				
				MRN = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[6]/div")).getText();				
				Stills = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[17]/div")).getText();			
				Videos = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[18]/div")).getText();						
				WebElement procedure = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[13]/div/a"));
				procedureName = procedure.getText();				
				click(procedure);	
				wait(driver, "3");
				Navigate.waitTime(driver, "5");
				break;
			}			
		}
		WebElement CaseDeatailsOR = driver.findElement(By.xpath(CASE_DETAILS_OR+OR+CASE_DETAILS_OR1));
		verifyElementIsPresent(driver, CaseDeatailsOR);
		WebElement CaseDetailsMRN = driver.findElement(By.xpath(CASE_DETAILS_MRN+MRN+CASE_DETAILS_MRN1));
		verifyElementIsPresent(driver, CaseDetailsMRN);		
		return procedureName;	
	}
	
	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:04-May-2016
	 * Modified date:04-May-2016
	 * Purpose: Create CSV file
	 * @return
	 */	
	public static String UserName = null;
	public static String FirsName = null;
	public static String LastName = null;
	public static String PassWord = null;
	public static String PrimaryEmail = null;
	public static String Role = null;
	public static String Notification = null;
	public static String writeCSVFile() throws IOException {		
		String UploadFolder = Directory.uploadFilePath;		
		PrintWriter pw = new PrintWriter(new File(UploadFolder+"users.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("username");
        sb.append(',');
        sb.append(" first name");
        sb.append(',');
        sb.append(" last name");
        sb.append(',');
        sb.append(" password");
        sb.append(',');
        sb.append(" primary email");
        sb.append(',');
        sb.append(" role");
        sb.append(',');
        sb.append("  notification");
        sb.append('\n');        
        String currenttime = new SimpleDateFormat("EyyMMddHHmmssa").format(Calendar.getInstance().getTime());	
    	UserName = "USER"+currenttime;
    	FirsName = "USER"+currenttime;
    	LastName = "USER"+currenttime;
    	PassWord = "Steris1234";
    	PrimaryEmail = "USER"+currenttime+"@steris.com";
    	Role = "1";
    	Notification = "Email";        
        sb.append(UserName);
        sb.append(',');
        sb.append(FirsName);
        sb.append(',');
        sb.append(LastName);
        sb.append(',');
        sb.append(PassWord);
        sb.append(',');
        sb.append(PrimaryEmail);
        sb.append(',');
        sb.append(Role);
        sb.append(',');
        sb.append(Notification);
        sb.append('\n');
        pw.write(sb.toString());
        pw.close();
		return UserName;       
	}
	
	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:04-May-2016
	 * Modified date:04-May-2016
	 * Purpose: Validate new user details
	 * @return
	 */	
	public static String verifyImportUserDetails(WebDriver driver) {
		WebElement username = driver.findElement(By.xpath(USER_USER_NAME+UserName+USER_USER_NAME1));
		click(username);
		verifyElementIsPresent(driver, username);
		WebElement firstname = driver.findElement(By.xpath(USER_FIRST_NAME+FirsName+USER_FIRST_NAME1));
		verifyElementIsPresent(driver, firstname);
		WebElement lastname = driver.findElement(By.xpath(USER_LAST_NAME+LastName+USER_LAST_NAME1));
		verifyElementIsPresent(driver, lastname);
		WebElement primaryemail = driver.findElement(By.xpath(USER_PRIMARY_EMAIL+PrimaryEmail+USER_PRIMARY_EMAIL1));
		verifyElementIsPresent(driver, primaryemail);
		WebElement role = driver.findElement(By.xpath(USER_ROLE+"SuperAdmin"+USER_ROLE1));
		verifyElementIsPresent(driver, role);
		WebElement notification = driver.findElement(By.xpath(USER_NOTIFICATION+Notification+USER_NOTIFICATION1));
		verifyElementIsPresent(driver, notification);		
		return "User Detailes are Successfully Validated";		
	}
	
	
	/**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:05-May-2016
	  * Modified date:05-May-2016
	  * Purpose: Draw rectangle on image
	  * @return
	  * @throws InterruptedException 
	 * @throws IOException 
	  */ 
	public static String Before = null;
	public static String drawrectangle(WebDriver driver) throws InterruptedException, IOException {
		WebElement Image = driver.findElement(By.id("myViewer_annotationCanvas"));
		WebElement Rectangle = driver.findElement(By.xpath("//a[@title='Rectangle']"));
		actionClick(driver,Rectangle);		
		wait(driver, "2");
		Actions builder = new Actions(driver);
		builder.clickAndHold(Image).moveByOffset(255,51).moveByOffset(510,103).release().perform();
		wait(driver, "2");
		actionClick(driver,Rectangle);		
		wait(driver, "2");	
		Actions builder1 = new Actions(driver);
		builder1.clickAndHold(Image).moveByOffset(100,50).moveByOffset(105,55).release().perform();
		wait(driver, "2");		
		String filepath=Directory.uploadFilePath+"BeforePic.png";		
		File fileName = new File(filepath);
		String absoultePath = fileName.getAbsoluteFile().toString();		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(absoultePath));
		//ZadoReports.add("After ScreenShot","Should take screeshot After edit image","", "Screenshot should be taken",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		return absoultePath;
	}
	
	/**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:05-May-2016
	  * Modified date:05-May-2016
	  * Purpose: Add text on image
	  * @return
	  * @throws InterruptedException 
	  */ 
	public static String After = null;
	public static String addtext(WebDriver driver) throws InterruptedException, IOException {
		WebElement Image = driver.findElement(By.id("myViewer_annotationCanvas"));
		WebElement Menu = driver.findElement(By.xpath("//div[contains(@id,'assetEditWindow')]/div[2]/div/div/div/div/div/div/div/div/div[1]/div/div/div[1]/a[contains(@id,'menu-trigger')]"));
		actionClick(driver,Menu);
		WebElement insertText = driver.findElement(By.xpath("//a[@data-qtip='Text']"));
		actionClick(driver,insertText);		 
		wait(driver, "2");
		Actions builder = new Actions(driver);
		builder.clickAndHold(Image).moveByOffset(60,40).moveByOffset(65,45).release().perform();
		wait(driver, "2");
		WebElement textcolor = driver.findElement(By.name("ForeGroundColor"));
		clearAndType(textcolor,"Red");
		wait(driver, "1");
		WebElement Text = driver.findElement(By.name("text"));
		clearAndType(Text, "This is for Demo");
		wait(driver, "1");
		WebElement Size = driver.findElement(By.name("SizeId"));
		clearAndType(Size, "50");
		wait(driver, "2");
		WebElement GroundColor = driver.findElement(By.name("BackGroundColor"));
		clearAndType(GroundColor,"Transparent");
		wait(driver, "2");
		Navigate.enter(driver);
		WebElement FillColor1 = driver.findElement(By.xpath("//span[contains(text(),'Fill Color')]/parent::label/following-sibling::div/div/div/input[@name='ColorId']"));
		clearAndType(FillColor1, "Transparent");
		wait(driver, "2"); 
		WebElement Rectangle = driver.findElement(By.xpath("//a[@title='Circle']"));
		doubleClick(driver, Rectangle);
		wait(driver, "3");	
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filepath=Directory.uploadFilePath+"AfterPic.png";
		File fileName = new File(filepath);
		String absoultePath = fileName.getAbsoluteFile().toString();
		FileUtils.copyFile(scrFile, new File(absoultePath));
		//ZadoReports.add("After ScreenShot","Should take screeshot After edit image","", "Screenshot should be taken",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		return absoultePath;
	}
	
	/**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:05-May-2016
	  * Modified date:05-May-2016
	  * Purpose: Draw circle on image
	  * @return
	  * @throws InterruptedException 
	 * @throws AWTException 
	  */ 
	 public static String drawCircle(WebDriver driver) throws InterruptedException, IOException, AWTException
	 {
		 WebElement Image = driver.findElement(By.id("myViewer_annotationCanvas"));
		 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 String filepath=Directory.uploadFilePath+"BeforePic.png";
		 wait(driver, "4");
		 File fileName = new File(filepath);
		 String absoultePath = fileName.getAbsoluteFile().toString();	 
		 FileUtils.copyFile(scrFile, new File(absoultePath));		 
		 //ZadoReports.add("Before ScreenShot","Should take screeshot before edit image","", "Screenshot should be taken",Objects.toString("", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		 WebElement Rectangle = driver.findElement(By.xpath("//a[@title='Circle']"));
		 actionClick(driver,Rectangle);		
		 wait(driver, "2");
		 Actions builder = new Actions(driver);
		 builder.clickAndHold(Image).moveByOffset(255,51).moveByOffset(510,103).release().perform();
		 wait(driver, "2");
		 actionClick(driver,Rectangle);		
		 wait(driver, "2");	
		 Actions builder1 = new Actions(driver);
		 builder1.clickAndHold(Image).moveByOffset(-10,-50).moveByOffset(-60,-70).release().perform();
		 wait(driver, "3");
		 Actions builder2 = new Actions(driver);   
		 builder2.moveToElement(Image).moveByOffset(-10,-50).click().build().perform();
		 builder2.moveToElement(Image).moveByOffset(-10,-50).doubleClick().build().perform();
		 wait(driver, "5");		
		 WebElement LineColor = driver.findElement(By.xpath("//span[contains(text(),'Line Color')]/parent::label/following-sibling::div/div/div/input[@name='ColorId']"));
		 clearAndType(LineColor, "Red");
		 wait(driver, "1");   
		 WebElement Thickness = driver.findElement(By.xpath("//span[contains(text(),'Thickness')]/parent::label/following-sibling::div/div/div/input"));
		 clearAndType(Thickness, "10");
		 wait(driver, "1"); 
		 WebElement FillColor1 = driver.findElement(By.xpath("//span[contains(text(),'Fill Color')]/parent::label/following-sibling::div/div/div/input[@name='ColorId']"));
		 clearAndType(FillColor1, "Transparent");
		 wait(driver, "2"); 		
		 Navigate.enter(driver);   
		 wait(driver, "5");	
		 return absoultePath;
	 }
	 
	 /**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:06-May-2016
	  * Modified date:06-May-2016
	  * Purpose: Validate color in particular patient
	  * @return
	  * @throws InterruptedException 
	  */ 
	 public static String colorVerification1(WebDriver driver) {
		 int size = driver.findElements(By.xpath("//html//table[*]/tbody//tr/td[13]/div")).size();			
		 for(int i=1;i<=size;i++) {
			 String text = driver.findElement(By.xpath("//html//table["+i+"]/tbody//tr/td[4]/div")).getText();			
			 if(text.equalsIgnoreCase("Proc Ends")) {
				 int n=i+1;
				 System.out.println("Row Count : "+n);
				 OR = driver.findElement(By.xpath("//html//table["+n+"]/tbody//tr/td[3]/div")).getText();				
				 MRN = driver.findElement(By.xpath("//html//table["+n+"]/tbody//tr/td[6]/div")).getText();				
				 Stills = driver.findElement(By.xpath("//html//table["+n+"]/tbody//tr/td[17]/div")).getText();			
				 Videos = driver.findElement(By.xpath("//html//table["+n+"]/tbody//tr/td[18]/div")).getText();						
				 WebElement element = driver.findElement(By.xpath("//html//table["+n+"]/tbody//tr/td[13]/div/a"));
				 procedureName = element.getText();					
				 click(element);					
				 Navigate.waitTime(driver, "5");
				 break;
			 }			
		 }
		 WebElement OR1 = driver.findElement(By.xpath(CASE_DETAILS_OR+OR+CASE_DETAILS_OR1));
		 verifyElementIsPresent(driver, OR1);
		 WebElement MRN1 = driver.findElement(By.xpath(CASE_DETAILS_MRN+MRN+CASE_DETAILS_MRN1));
		 verifyElementIsPresent(driver, MRN1);		
		 return procedureName;	
	 }

	 /**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:06-May-2016
	  * Modified date:06-May-2016
	  * Purpose: Select new image
	  * @return
	  * @throws InterruptedException 
	  */ 
	 public static void selectImage(WebDriver driver) {		
		 int size = driver.findElements(By.xpath("//div//table[*]/tbody/tr/td/div/div[@class='itemContainer']/div/div[@class='itemTitleText']")).size();			
		 for(int i=1;i<=size;i++) {
			 String imageName = driver.findElement(By.xpath("//div//table["+i+"]/tbody/tr/td/div/div[@class='itemContainer']/div/div[@class='itemTitleText']")).getText();			
			 if(imageName.contains("Still")) {										
				 WebElement image = driver.findElement(By.xpath("//div//table["+i+"]/tbody/tr/td/div/div[@class='itemContainer']//div[@class='image-container']/img"));
				 System.out.println("Procedure : "+imageName);
				 click(image);						
				 Navigate.waitTime(driver, "8");
				 break;
			 }			
		 } 
	 }
	 
	 /**
	  * Name:Gobi
	  * @param driver
	  * @param inputData
	  * @param webElement
	  * Created date:06-May-2016
	  * Modified date:06-May-2016
	  * Purpose: Compare between Before edit and After edit images
	  * @return
	  * @throws InterruptedException 
	  */ 	 
	 public static String testImageComparison(WebDriver driver)throws IOException, InterruptedException
	 {
		 File First = new File("C:/workspace/Zautomate/ZA/testcases/UploadFiles/BeforePic.png");
		 File Second = new File("C:/workspace/Zautomate/ZA/testcases/UploadFiles/AfterPic.png");
		 File Compare = new File("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Changes.png");
		 BufferedImage bufileInput = ImageIO.read(First);
		 DataBuffer dafileInput = bufileInput.getData().getDataBuffer();
		 int sizefileInput = dafileInput.getSize();                     
		 BufferedImage bufileOutPut = ImageIO.read(Second);
		 DataBuffer dafileOutPut = bufileOutPut.getData().getDataBuffer();
		 int sizefileOutPut = dafileOutPut.getSize();
		 Boolean matchFlag = true;
		 if(sizefileInput == sizefileOutPut) {                         
			 for(int j=0; j<sizefileInput; j++) {
				 if(dafileInput.getElem(j) != dafileOutPut.getElem(j)) {
					 System.out.println("Fine : "+dafileInput.getElem(j));
					 matchFlag = false;					
					 break;	                       
				 }
			 }			
		 } else {
			 matchFlag = false;			 
		 }
		 Assert.assertFalse(matchFlag, "Images are not same");		 
		/* if(First.exists()) {
			 First.delete();	
			 Second.delete();	
		 }	*/	
		 String line = "compare "+First+" "+Second+" "+Compare+"";
		 Process p = Runtime.getRuntime().exec(line);
		 p.waitFor();
		// ZadoReports.add("After ScreenShot","Should take screeshot After edit image","", "Screenshot should be taken",Objects.toString("", ""),LogAs.PASSED, Compare);
		 return "Images are validated";

	 }

}

