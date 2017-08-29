package zautomate.zadoqa.CommonMethods;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.Navigate;
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.utils.Directory;

public class ResolveCommonMethods extends Manipulation implements OR
{	
	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:13-May-2016
	 * Modified date:13-May-2016
	 * Purpose: Login into the Resolve application with different user's
	 * Requirement : User Type
	 * @return
	 * @throws InterruptedException 
	 */ 
	public static String Login(WebDriver driver, String User)
	{
		driver.get(Directory.RESOLVE_URL);
		driver.manage().window().maximize();
		wait(driver, "3");		
		WebElement Logo = driver.findElement(By.xpath(RESOLVE_LOGO));
		verifyElementIsPresent(driver, Logo);

		if(User.equalsIgnoreCase("RCU Admin"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_RCU_ADMIN_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_RCU_ADMIN_PASSWORD);			
		}
		else if(User.equalsIgnoreCase("RCU Manager"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_RCU_MANAGER_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_RCU_MANAGER_PASSWORD);			
		}
		else if(User.equalsIgnoreCase("RCU Officer"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_RCU_OFFICER_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_RCU_OFFICER_PASSWORD);			
		}
		else if(User.equalsIgnoreCase("PCEP Admin"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_PCEP_ADMIN_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_PCEP_ADMIN_PASSWORD);			
		}
		else if(User.equalsIgnoreCase("PCEP Manager"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_PCEP_MANAGER_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_PCEP_MANAGER_PASSWORD);			
		}
		else if(User.equalsIgnoreCase("PCEP Officer"))
		{
			WebElement UserName = driver.findElement(By.name(RESOLVE_USER_NAME));
			sendKeys(UserName, Directory.RESOLVE_PCEP_OFFICER_USERNAME);
			WebElement PassWord = driver.findElement(By.name(RESOLVE_PASS_WORD));
			sendKeys(PassWord, Directory.RESOLVE_PCEP_OFFICER_PASSWORD);			
		}
		WebElement LoginButton = driver.findElement(By.xpath(RESOLVE_LOGIN_BUTTON));
		click(LoginButton);	
		Navigate.waitTime(driver, "4");
		WebElement LoggedUsername = driver.findElement(By.xpath(LOGGED_USER+User+LOGGED_USER1));
		verifyElementIsPresent(driver, LoggedUsername);
		String loggedusername = LoggedUsername.getText();
		System.out.println("Name :"+loggedusername);
		return loggedusername;
	}

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:13-May-2016
	 * Modified date:13-May-2016
	 * Purpose: Logout to the Resolve application
	 * @return
	 * @throws InterruptedException 
	 */ 
	public static void Logout(WebDriver driver)
	{
		WebElement LogoutButton = driver.findElement(By.xpath(LOGOUT_BUTTON));
		verifyElementIsPresent(driver, LogoutButton);
		click(LogoutButton);
		wait(driver, "2");
		WebElement LogoutInformation = driver.findElement(By.xpath(LOGOUT_INFORMATION));
		verifyElementIsPresent(driver, LogoutInformation);
		WebElement LogoutYesButton = driver.findElement(By.xpath(LOGOUT_YES_BUTTON));
		verifyElementIsPresent(driver, LogoutYesButton);
		click(LogoutYesButton);
		Navigate.waitTime(driver, "3");
	}

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:14-May-2016
	 * Modified date:14-May-2016
	 * Purpose: Close Current tab in Resolve application
	 * @return
	 * @throws InterruptedException 
	 */ 
	public static String closeCurrentTab(WebDriver driver)
	{
		int size = driver.findElements(By.xpath("//html/body/div[3]/div[1]/div[1]/ul/li[*]/a[1]")).size();

		for(int i=1;i<=size;i++) {			
			WebElement CloseTab = driver.findElement(By.xpath("//html/body/div[3]/div[1]/div[1]/ul/li["+size+"]/a[1]"));
			click(CloseTab);
			wait(driver, "1");
			break;
		}		
		return "Current tab is closed";

	}

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:17-May-2016
	 * Modified date:20-May-2016
	 * Purpose: Verify Multiple values in PDF file using URL
	 * Requirement : Values 
	 * @return
	 * @throws InterruptedException 
	 */ 
	public static String verifyPdfFile(WebDriver driver) throws IOException 
	{
		URL url = new URL(driver.getCurrentUrl());
		BufferedInputStream inputPdfFile = new BufferedInputStream(url.openStream());
		PDFParser pdfParser = new PDFParser(inputPdfFile);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser.getPDDocument());
		pdfParser.getPDDocument().close();		
		int i;
		String results = "";
		for (i = 0; i < widgetUrls; i++) {			
			if (pdfContent.contains(ApplicationValue[i])) {				
				System.out.println((ApplicationValue[i] + "  is present in the PDF file"));
				results = ApplicationValue[i] + " is present in the PDF file"; 

			} else {
				System.out.println((ApplicationValue[i] + "  is not present in the PDF file"));
				results = ApplicationValue[i] + " is not present in the PDF file"; 
			}
		}
		return results;
	}	

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:19-May-2016
	 * Modified date:20-May-2016
	 * Purpose: Verify Single value in PDF file using 'Downloaded File Path'
	 * @return
	 * @throws Exception 
	 * @throws InterruptedException 
	 */ 
	public static File Path = null;
	public static File download() throws Exception
	{
		String down = "C:/Users/Ibog Gobi/Downloads/";
		File dir = new File(down);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {      
			//System.out.println("File : "+files);      
		}
		File lastModifiedFile = files[0];
		File First = null ;
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];            
				First = lastModifiedFile;			
			}
		} 		     
		File downloaded = First;     
		String filename = downloaded.getName();		    
		String extension = FilenameUtils.getExtension(filename);		  
		File NewFolder = new File("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/"+filename+""); 
		FileUtils.copyFile(First, NewFolder);		
		Path = NewFolder;		
		return NewFolder;

	}

	/**
	 * Name:Gobi
	 * @param driver
	 * @param inputData
	 * @param webElement
	 * Created date:19-May-2016
	 * Modified date:20-May-2016
	 * Purpose: Verify Single value in PDF file using 'Downloaded File Path'
	 * @return
	 * @throws InterruptedException 
	 */ 
	public static String verifydownloadedPdfFile(WebDriver driver) throws IOException 
	{
		String Results="";
		File file = Path;
		FileInputStream inputPdfFile = new FileInputStream(file); 
		PDFParser pdfParser = new PDFParser(inputPdfFile);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser.getPDDocument());
		pdfParser.getPDDocument().close();		
		for (int i = 0; i < widgetUrls; i++) {		
			if (pdfContent.contains(ApplicationValue[i])) {					
				Results = ApplicationValue[i] + "  is present in the PDF file";
			} else {			
				Results = ApplicationValue[i] + "  is not present in the PDF file";
			}
		}
		return Results;
	}

	public static String verifydownloadedWordFile(WebDriver driver,String inputData) throws Exception 
	{
		String Results=""; 
		File NewFolder = Path;	
		FileInputStream fis=new FileInputStream(NewFolder);
		XWPFDocument docx=new XWPFDocument(fis);
		List<XWPFParagraph> paragraphlist=docx.getParagraphs();  
		for(XWPFParagraph paragraph:paragraphlist) { 
			if (paragraph.getText().contains(inputData)) {				
				Results = inputData + "  is present in the word file";
			}  
			else {
				Results = inputData + "  is present in the word file";
			}
		}		
		return Results;

	}


	public static void DownloadFile() throws Exception
	{
		try	{
			Screen screen = new Screen();		
			try	{			 
				Pattern Save = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/Save.png");
				screen.wait(Save, 1);
				screen.click(Save);
			} catch(Exception e)	{
				Pattern SaveAs = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/SaveAs.png");
				screen.wait(SaveAs, 1);
				screen.click(SaveAs);
			}
			Pattern Ok = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/OK.png");
			screen.wait(Ok, 1);
			screen.click(Ok);
		}
		catch(Exception e) {			
		}

	}

}
