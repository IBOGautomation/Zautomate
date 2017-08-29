package zautomate.zadoqa.CommonMethods;

import java.awt.AWTException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
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
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.enums.LogAs;
import zautomate.zadoqa.reports.CaptureScreen;
import zautomate.zadoqa.reports.CaptureScreen.ScreenshotOf;
import zautomate.zadoqa.utils.Directory;

public class Gem25 extends Manipulation implements OR {

	public static void sshScripts() throws JSchException, IOException {		      
		JSch jsch = new JSch();
		Session session = jsch.getSession(Directory.GEM25_SSH_USERNAME, Directory.GEM25_HOST, 22);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);;
		session.setPassword(Directory.GEM25_SSH_PASSWORD);
		session.connect();             
		Channel channel = session.openChannel("exec");
		((ChannelExec)channel).setCommand(Directory.GEM25_SSH_CLEARSESSION);
		channel.setInputStream(null);
		((ChannelExec)channel).setErrStream(System.err);             
		InputStream input = channel.getInputStream();
		channel.connect();             
	}

	public static void makeLowForAllDigitalInput() {
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio200/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio200/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio201/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio201/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio202/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio202/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio203/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio203/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio208/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio208/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio209/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio209/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio210/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio210/value");
		Gem25_Board_SSH.executeShllscripts("echo out > /sys/class/gpio/gpio211/direction");
		Gem25_Board_SSH.executeShllscripts("echo 0 > /sys/class/gpio/gpio211/value");
	}

	public static void gem25Login(WebDriver driver) throws JSchException, IOException {
		sshScripts();		
		//makeLowForAllDigitalInput();
		driver.get(Directory.GEM25_URL);		
		wait(driver, "3");	
		WebElement password = driver.findElement(By.id(GEM25_PASSWORD));
		sendKeys(password, Directory.GEM25_PASSWORD);
		WebElement loginbutton = driver.findElement(By.name(GEM25_LOGIN_BUTTON));
		loginbutton.submit();
		wait(driver, "3");	
	}
	
	public static String gem25RebootAndLogin(WebDriver driver) throws JSchException, IOException {
		driver.switchTo().defaultContent();
		driver.findElement(By.name("Reboot")).click();
		wait(driver, "2");		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();		
		alert.accept();
		wait(driver, "120");		
		WebElement password = driver.findElement(By.id(GEM25_PASSWORD));
		sendKeys(password, Directory.GEM25_PASSWORD);
		WebElement loginbutton = driver.findElement(By.name(GEM25_LOGIN_BUTTON));
		loginbutton.submit();
		wait(driver, "3");
		return alertText;	
	}

	public static String enableAlarm(WebDriver driver,String AlarmName) {
		String Status = "";
		breakLoop:
			while(true) {
				try	{
					if(driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']/parent::tr//td/input[@type='checkbox']")).isDisplayed())	{
						driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']/parent::tr//td/input[@type='checkbox']")).click();
						//break breakLoop;
						Status = "Alarm is Enabled Successfully";
						wait(driver, "3");						
						break;
					}					
				}
				catch(Exception e) {
					Status = "Alarm is Not Enabled";
				}
				driver.findElement(By.xpath("//div[@class='pagin']//a[contains(text(),'Next')]")).click();	
				wait(driver, "3");
			}
		return Status;
	}

	public static String disabledAlarm(WebDriver driver,String AlarmName) {
		String Status = "";
		breakLoop:
			while(true)	{
				try {
					if(driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']/parent::tr//td/input[@type='checkbox']")).isDisplayed()) {
						if (driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']/parent::tr//td/input[@type='checkbox']")).isSelected()) {
							driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']/parent::tr//td/input[@type='checkbox']")).click();
							Status = "Alarm is Disabled Successfully";
							wait(driver, "3");
							//break breakLoop;
							break;
						} else {
							Status = "Alarm is Not Disabled";
						}						
					}					
				}
				catch(Exception e) {
				}
				driver.findElement(By.xpath("//div[@class='pagin']//a[contains(text(),'Next')]")).click();
				wait(driver, "3");
			}
		return Status;
	}

	public static String editAlarm(WebDriver driver,String AlarmName) {
		String Status = "";
		breakLoop:
			while(true) {
				try	{
					if(driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']")).isDisplayed())	{
						wait(driver, "2");
						driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']")).click();
						wait(driver, "1");
						WebElement element = driver.findElement(By.id("btnAlarmEdit"));
						click(element);	
						wait(driver, "2");
						Status = "Alarm is Edited Successfully";
						//break breakLoop;
						break;
					}					
				}
				catch(Exception e) {
					Status = "Alarm is Not Edited";
				}
				driver.findElement(By.xpath("//div[@class='pagin']//a[contains(text(),'Next')]")).click();
				wait(driver, "3");
			}
		return Status;
	}

	public static String deleteAlarm(WebDriver driver) {
		String Status = "";
		int i = 8;
		for(int j=0;j<=i;j++){
			try	{
				wait(driver, "1");				
				driver.findElement(By.xpath("//html/body/div[1]/fieldset/table/tbody/tr[2]/td[2]")).click();
				wait(driver, "1");
				WebElement element = driver.findElement(By.id("btnAlarmRemove"));
				click(element);	
				wait(driver, "2");
				driver.switchTo().alert().accept();
				wait(driver, "8");
				driver.switchTo().alert().accept();
				wait(driver, "1");
				Status = "Alarm is Deleted Successfully";
				//break breakLoop;						
			} catch(Exception e) {
				Status = "Alarm is Not Deleted";
			}
		}
		return Status;
	}

	public static String SingleAlarmDelete(WebDriver driver,String AlarmName) {
		String Status = "";	
		breakLoop:
			while(true) {
				try	{
					if(driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']")).isDisplayed())	{
						driver.findElement(By.xpath("//td[contains(@id,'tdAlarmAlias') and text()='"+AlarmName+"']")).click();
						wait(driver, "2");						
						driver.findElement(By.id("btnAlarmRemove")).click();					
						wait(driver, "2");
						driver.switchTo().alert().accept();
						wait(driver, "8");
						//driver.switchTo().alert().accept();		
						Alert alert = driver.switchTo().alert();
						String alertText = alert.getText();
						Status = alertText;
						alert.accept();
						wait(driver, "3");						
						break breakLoop;
					}					
				}
				catch(Exception e) {					 
				}
				driver.findElement(By.xpath("//div[@class='pagin']//a[contains(text(),'Next')]")).click();
				wait(driver, "3");
			}
		return Status;		
	}

	public static String selectDashboardAlarm(WebDriver driver,String AlarmName) {
		String dashboardAlarmID = "";
		String Status = "";
		//breakLoop:
		while(true)	{
			try	{
				if(driver.findElement(By.xpath("//td[@title='"+AlarmName+"']/preceding-sibling::td[2]")).isDisplayed())	{
					dashboardAlarmID = driver.findElement(By.xpath("//td[@title='"+AlarmName+"']/preceding-sibling::td[2]")).getText();
					break;					
					//break breakLoop;
				}					
			}
			catch(Exception e)	{
			}
			driver.findElement(By.xpath("//div[@class='pagin']//a[contains(text(),'Next')]")).click();
			wait(driver, "3");
		}
		return dashboardAlarmID;
	}

	public static String verifyDashboardAlarm(WebDriver driver,String AlarmNames) {
		String dashboardAlarm = "";
		try	{
			if(driver.findElement(By.xpath("//td[@title='"+AlarmNames+"']")).isDisplayed())	{
				dashboardAlarm = driver.findElement(By.xpath("//td[@title='"+AlarmNames+"']")).getText();	
				dashboardAlarm = "Alarm is Present in Dashboard";
			}					
		}
		catch(Exception e)	{
			dashboardAlarm = "Alarm is Not Present in the Dashboard Page";
		}
		return dashboardAlarm;
	}

	public static String alertAccept1(WebDriver driver) throws AWTException {		
		String AlertEnable = driver.switchTo().alert().getText();
		//log("Alarm Enabled Success Info : " + AlertEnable);
		if(AlertEnable.trim().equalsIgnoreCase("Enabled Successfully")) {
			driver.switchTo().alert().accept();			 
		}
		return AlertEnable;
	}

	public static String getAlarmlogLoggedDateTime(WebDriver driver) {
		int logsize = driver.findElements(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr[*]/td[1]")).size();
		int count = logsize+1;
		String AlarmLogsLoggedDate = driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2]")).getText();
		return AlarmLogsLoggedDate;
	}

	public static String getAlarmlogLoggedDescription(WebDriver driver) {
		int logsize = driver.findElements(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr[*]/td[1]")).size();
		int count = logsize+1;
		String AlarmLogsLoggedDescriptions = driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[11]")).getText();
		return AlarmLogsLoggedDescriptions;
	}

	public static String verifyLoggedResults(String DashboarLoggedDate, String AlarmLogsLoggedDate, String AlarmLogsDescription) throws ParseException	{
		String Result = "";
		String[] datetime = DashboarLoggedDate.split("\\s+");
		String timesplit = datetime[1];
		String datesplit = datetime[0];
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd"); 
		Date date = dt.parse(datesplit); 
		SimpleDateFormat dt1 = new SimpleDateFormat("mm/dd/yyyy");
		String After = dt1.format(date);
		String ModifiedDateTime = After+" "+timesplit;
		System.out.println("Changed : " +ModifiedDateTime);
		System.out.println(dt1.format(date));		
		if(DashboarLoggedDate.equalsIgnoreCase(AlarmLogsLoggedDate)){
			if(AlarmLogsDescription.contains(ModifiedDateTime)){
				Result = "Dashboard Alarm Triggered Data/Time is Matched to Alarm logs "+ModifiedDateTime+"";				
			}			
		} else {
			Result = "Dashboard Alarm Triggered Data/Time is Not Matched to Alarm logs "+ModifiedDateTime+"";
		}
		return Result;
	}

	public static String verifyLoggedTriggeredOffResults(String DashboarLoggedDate ,String AlarmLogsDescription) throws ParseException	{
		String Result = "";
		String[] datetime = DashboarLoggedDate.split("\\s+");
		String timesplit = datetime[1];
		String datesplit = datetime[0];
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd"); 
		Date date = dt.parse(datesplit); 
		SimpleDateFormat dt1 = new SimpleDateFormat("mm/dd/yyyy");
		String After = dt1.format(date);
		String ModifiedDateTime = After+" "+timesplit;
		System.out.println("Changed : " +ModifiedDateTime);
		System.out.println(dt1.format(date));	
		if(AlarmLogsDescription.contains(ModifiedDateTime)){
			Result = "Dashboard Alarm Triggered Data/Time is Matched to Alarm logs "+ModifiedDateTime+"";				
		}			
		else {
			Result = "Dashboard Alarm Triggered Data/Time is Not Matched to Alarm logs "+ModifiedDateTime+"";
		}
		return Result;
	}

	public static void waitForAlarmLogs(WebDriver driver) {
		//driver.manage().timeouts().implicitlyWait(Integer.parseInt("40"), TimeUnit.SECONDS);
		wait(driver, "25");
	}

	public static String verifyAlarmLoggedDate(WebDriver driver,String LoggedDate) {
		String Result = "";
		String LogsLoggedDate = "";
		String[] datetime = LoggedDate.split("\\s+");
		String timesplit = datetime[1];		
		String datesplit = datetime[0];		
		String[] time = timesplit.split(":");
		String hour = time[0];		
		String minute = time[1];		
		String seconds = time[2];		
		int second = Integer.parseInt(seconds); 
		int minimum = second-1;
		int maximum = second+1;		
		NumberFormat formatter1 = new DecimalFormat("00");  
		String Minimum = formatter1.format(minimum);
		String Maximum = formatter1.format(maximum);		
		String MinimumDate = datesplit+" "+hour+":"+minute+":"+Minimum;
		String SameDate = LoggedDate;
		String MaximumDate = datesplit+" "+hour+":"+minute+":"+Maximum;	
		int logsize = driver.findElements(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr[*]/td[1]")).size();
		int count = logsize+1;
		try	{			
			if(driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+SameDate+"')]")).isDisplayed()) {
				LogsLoggedDate = driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+SameDate+"')]")).getText();
				Result = "Dashboard Alarm Triggered Data/Time is Matched to Alarm logs "+LogsLoggedDate+"";
			}	
			else if(driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+MaximumDate+"')]")).isDisplayed()) {
				LogsLoggedDate = driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+MaximumDate+"')]")).getText();
				Result = "Dashboard Alarm Triggered Data/Time is Matched to Alarm logs "+LogsLoggedDate+"";
			}
			else if(driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+MinimumDate+"')]")).isDisplayed()) {
				LogsLoggedDate = driver.findElement(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr["+count+"]/td[2][contains(text(),'"+MinimumDate+"')]")).getText();
				Result = "Dashboard Alarm Triggered Data/Time is Matched to Alarm logs "+LogsLoggedDate+"";
			}			
		}
		catch(Exception e)	{
			Result = "Dashboard Alarm Triggered Data/Time is Not Matched to Alarm logs "+LogsLoggedDate+"";
		}	
		return Result;
	}

	public static String logCount(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//span[@class='pg-normal' and contains(text(),'Last')]")).click();
			wait(driver, "2");
		}
		catch(Exception e){			
		}
		int logsize = driver.findElements(By.xpath("//html/body/div[1]/fieldset[2]/div[1]/table/tbody/tr[*]/td[1]")).size();
		int count = logsize+1;
		String elementCounts = String.valueOf(count);  
		return elementCounts;
	}

	public static String alarmfieldsverification(WebDriver driver, String Inputdata)  {
		int idcount = Integer.parseInt(Inputdata);
		int fin = idcount-1;
		String aliasname = driver.findElement(By.xpath("//td[contains(@id,'tdAlarmid') and text()='"+fin+"']//following-sibling::td[1]")).getText();
		String aliasdesc = driver.findElement(By.xpath("//td[contains(@id,'tdAlarmid') and text()='"+fin+"']//following-sibling::td[2]")).getText();
		int name = aliasname.length();
		int desc = aliasdesc.length();
		if(name==32){
			if(desc==64){
				Inputdata = "Name Accept only "+name+" & Description Accept only "+desc+"";
			}
		}		
		return Inputdata;	
	}

	public static String Type(WebDriver driver, WebElement element ,String Inputdata)
	{		
		element.clear();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}		
		String currenttime = new SimpleDateFormat("ddHHmmssyyyy").format(Calendar.getInstance().getTime());
		String originalValue = Inputdata;
		String combinedValues = currenttime+originalValue;
		sendKeys(element, combinedValues);	
		String value = currenttime+"32Character32Charact";		
		return value;		
	}
	





















}
