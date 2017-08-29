package zautomate.zadoqa.util;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import zautomate.zadoqa.CommonMethods.CloserConnect;
import zautomate.zadoqa.CommonMethods.CommonMethods;
import zautomate.zadoqa.CommonMethods.CrowdTwist;
import zautomate.zadoqa.CommonMethods.Gem25;
import zautomate.zadoqa.CommonMethods.Gem25_Board_SSH;
import zautomate.zadoqa.CommonMethods.NeonMobileCommonMethods;
import zautomate.zadoqa.CommonMethods.ResolveCommonMethods;
import zautomate.zadoqa.CommonMethods.SSH;
import zautomate.zadoqa.CommonMethods.Telematics;
import zautomate.zadoqa.apitesting.RestAssuredApiCommonMethods;
import zautomate.zadoqa.commands.ElementActions;
import zautomate.zadoqa.commands.LayoutActions;
import zautomate.zadoqa.commands.LocatorBy;
import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.Navigate;
import zautomate.zadoqa.commands.OR;
import zautomate.zadoqa.utils.Directory;

/**
 * Common methods for all kind of actions (Selenium Actions, CrowdTwist specific common methods)
 * @author Babu
 *
 */
public class CommandUtils {

	public WebElement element;
	public String normalXpath;
	public WebElement element1;
	public WebElement element2;
	Object returnObj = null;
	public static String getText = "";
	public static String getSize = "";
	public static HashMap<Integer, String> getTextMap = new HashMap<Integer, String>();
	public static String[] widgetUrlCount=new String[100];
	public static int widgetUrls=0;
	public static String[] splitInputData;
	public static String[] ApplicationValue=new String[100];
	public static WebDriver fireFoxDriver;
	public static WebDriver chromeDriver;
	public static WebDriver ieDriver;	
	/**
	 * Locators
	 * @param driver
	 * @param locateBy
	 * @param orLocator
	 * @return
	 */
	public WebElement findElement(WebDriver driver, String inputData, String locateBy,
			String orLocator, String orLocatorStart, String orLocatorMid,String orLocatorEnd, String referenceStep) {

		switch (locateBy) {
		case "ByID":
			element = LocatorBy.locateById(driver, orLocator);
			break;
		case "ByName":
			element = LocatorBy.locateByName(driver, orLocator);
			break;
		case "ByOrName":
			element = LocatorBy.locateByOrName(driver, orLocator);
			break;
		case "ByXPath":
			element = LocatorBy.locateByXPath(driver, orLocator);
			break;
		case "ByLinkText":
			element = LocatorBy.locateByLinkText(driver, orLocator);
			break;
		case "ByTagName":
			element = LocatorBy.locateByTagName(driver, orLocator);
			break;
		case "ByClassName":
			element = LocatorBy.locateByClassName(driver, orLocator);
			break;
		case "ByCssSelector":
			element = LocatorBy.locateByCssSelector(driver, orLocator);
			break;
		case "ByPartialLinkText":
			element = LocatorBy.locateByPartialLinkText(driver, orLocator);
			break;
		case "Xpath":
			normalXpath = LocatorBy.locateByNormalXpath(driver, orLocator);
			break;
		case "ByXpaths":
			element1 = LocatorBy.locateByXPath(driver, orLocatorStart);
			element2 = LocatorBy.locateByXPath(driver, orLocatorEnd);
			break;
		case "MergeByXpath":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep = new Integer(referenceStep);
				String refText = getTextMap.get(Integer.valueOf(refStep));
				orLocator = orLocatorStart+refText+orLocatorEnd;				
				element = LocatorBy.locateByXPath(driver, orLocator);
			}
			else {
				orLocator = orLocatorStart+inputData+orLocatorEnd;				
				element = LocatorBy.locateByXPath(driver, orLocator);
			}
			break;
		case "MergeBy2Xpath":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String refText1 = getTextMap.get(Integer.valueOf(refStep1));
				String rerText2 = getTextMap.get(Integer.valueOf(refStep2));
				orLocator = orLocatorStart+refText1+orLocatorMid+rerText2+orLocatorEnd;				
				element = LocatorBy.locateByXPath(driver, orLocator);
			}			
			else if (inputData != null && referenceStep != null
					&& !referenceStep.trim().equals("")) {
				int refStep2 = new Integer(referenceStep);
				String rerText2 = getTextMap.get(Integer.valueOf(refStep2));
				orLocator = orLocatorStart+rerText2+orLocatorMid+inputData+orLocatorEnd;				
				element = LocatorBy.locateByXPath(driver, orLocator);
			} else {
				String[] referenceSteps = StringUtils.split(inputData, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String refText1 = getTextMap.get(Integer.valueOf(refStep1));
				String rerText2 = getTextMap.get(Integer.valueOf(refStep2));
				orLocator = orLocatorStart+refText1+orLocatorMid+rerText2+orLocatorEnd;					
				element = LocatorBy.locateByXPath(driver, orLocator);
			}
			break;
		case "MergeXpath":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refSteps = new Integer(referenceStep);
				String refTexts = getTextMap.get(Integer.valueOf(refSteps));
				orLocator = orLocatorStart+refTexts+orLocatorEnd;				
				normalXpath = LocatorBy.locateByNormalXpath(driver, orLocator);
			}
			else {
				orLocator = orLocatorStart+inputData+orLocatorEnd;				
				normalXpath = LocatorBy.locateByNormalXpath(driver, orLocator);
			}
			break;

		default:
			break;
		}
		return element;
	}

	/**
	 * Common selenium Actions and CrowdTwist actions
	 * @param driver
	 * @param element
	 * @param action
	 * @param inputData
	 * @param stepNo
	 * @param referenceStep
	 * @return
	 * @throws Exception
	 */

	public Object executeAction(WebDriver driver, WebElement element,
			String action, String inputData, int stepNo, String referenceStep ) throws Exception {
		//Object returnObj = null;

		switch (action) {

		case "GetUrl":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			Navigate.get(driver, inputData);
			break;
		case "NavigateToURL":
			Navigate.navigateUrl(driver,inputData);
			break;	
		case "Wait":
			Manipulation.wait(driver, inputData);
			break;
		case "WaitTime":
			Navigate.waitTime(driver, inputData);
			break;
		case "Maximize":
			Navigate.maximize(driver);
			break;
		case "Click":
			Manipulation.click(element);
			break;
		case "ActionClick":
			Manipulation.actionClick(driver,element);
			break;
		case "JsClick":
			Manipulation.jsClickByXPath(driver, normalXpath);
			break;
		case "JsType":
			Manipulation.jsTypeByXPath(driver, normalXpath,inputData);
			break;
		case "DoubleClick":
			Manipulation.doubleClick(driver, element);
			break;
		case "ClickAt":
			String[] coordinates = StringUtils.split(inputData, ",");
			int x = new Integer(coordinates[0]);
			int y = new Integer(coordinates[1]);
			Manipulation.clickAt(driver, element, x, y);
			break;
		case "ClickAndHold":
			Manipulation.clickAndHold(driver, element);
			break; 
		case "Clear":
			Manipulation.clear(element);
			break;
		case "Type":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj=Manipulation.sendKeys(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ClearAndType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj=Manipulation.clearAndType(element,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ClickAndType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep1 = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep1));
			}
			returnObj=Manipulation.clickAndType(element,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ActionType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj=Manipulation.actionType(driver,element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "TypeDynamicValue":
			returnObj = Manipulation.dynamicSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "TypeDynamicMinimumValue":
			returnObj = Manipulation.dynamicMinimumSendkeys(driver ,inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "Submit":
			Manipulation.submit(element);
			break;
		case "MouseOver":
			Manipulation.mouseOver(driver, element);
			break;
		case "MouseOverAndClick":
			Manipulation.mouseOverAndClick(driver, element);
			break;
		case "GetText":
			returnObj = ElementActions.getText(element);
			getText = returnObj.toString();	
			getTextMap.put(stepNo, returnObj.toString());				
			break;
		case "GetAttribute":
			returnObj = ElementActions.getAttribute(element, inputData);
			getText = returnObj.toString();	
			getTextMap.put(stepNo, returnObj.toString());
			break;		
		case "GetCount":
			returnObj = Manipulation.linkCounts(driver, normalXpath);			
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetCurrentURL":
			returnObj = Manipulation.getCurrentURL(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SelectCheckBox":
			Manipulation.selectCheckBox(element);
			break;		
		case "SelectByIndex":
			Manipulation.selectByIndex(element, inputData);
			break;
		case "SelectByValue":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				returnObj = Manipulation.selectByValue(element, getText1);
				getTextMap.put(stepNo, returnObj.toString());
			} else {				
				returnObj = Manipulation.selectByValue(element,inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}
			//returnObj = Manipulation.selectByValue(element, inputData);
			//getTextMap.put(stepNo, returnObj.toString());
			break;
		case "SelectByVisibleText":		
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) {
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				returnObj = Manipulation.selectByVisibletext(element,getText1);
				getTextMap.put(stepNo, returnObj.toString());
			} else {				
				returnObj = Manipulation.selectByVisibletext(element,inputData);
				getTextMap.put(stepNo, returnObj.toString());
			}
			break;
		case "DeSelectCheckBox":
			Manipulation.deSelectCheckBox(element);
			break;
		case "DeSelectByIndex":
			Manipulation.deSelectByIndex(element, inputData);
			break;
		case "DeSelectByValue":
			Manipulation.deSelectByValue(element, inputData);
			break;
		case "DeSelectByVisibleText":
			if (inputData == null && referenceStep != null && !referenceStep.trim().equals("")) 
			{
				int refStep1 = new Integer(referenceStep);			
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));				
				Manipulation.deSelectByVisibletext(element, getText1);
			} 
			else {
				Manipulation.deSelectByVisibletext(element, inputData);
			}
			break;			
		case "SwitchFrameByName":
			Navigate.switchToFrame(driver, inputData);
			break;
		case "SwitchFrameByIndex":
			int index = new Integer(inputData);
			Navigate.switchToFrame(driver, index);
			break;
		case "SwitchFrameByXpath":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchFrame":
			Navigate.switchToFrame(driver, element);
			break;
		case "SwitchToDefaultFrame":			
			Navigate.switchToDefaultFrame(driver);
			break;			
		case "Refresh":
			Navigate.refreshPage(driver);
			break;
		case "Back":
			Navigate.goBack(driver);
			break;
		case "Forward":
			Navigate.goForward(driver);
			break;
		case "AlertOk":
			returnObj = Navigate.alertOk(driver, element);
			break;	    
		case "DismissAlert":
			returnObj = Navigate.dismissAlert(driver);
			break;  	    
		case "AlertDismiss":
			returnObj = Navigate.alertDismiss(driver, element);
			break;
		case "PromptBox":
			returnObj = Navigate.promptBox(driver, element, inputData);
			break;		
		case "GenerateAlert":
			Navigate.alertGenerate(driver,inputData);
			break;			
		case "Close":
			Navigate.close(driver);
			break;		
		default:
			break;
		case "GetWindowHandle":
			Manipulation.getWindow(driver, element);
			break;
		case "SwitchToDefaultWindow":
			Manipulation.switchWindow(driver);
			break;	
		case "SwitchToDefaultContent":
			Manipulation.switchDefaultContent(driver);
			break;
		case "GetAutoIt":
			Manipulation.getAutoit(driver, inputData);
			break;
		case "ScrollDown":
			Navigate.pageDown(driver);
			break;
		case "ScrollUp":
			Navigate.pageUp(driver);
			break;
		case "ScrollBottom":
			Navigate.scrollBottom(driver);
			break;      
		case "KeyboardPageUp":
			Navigate.keyboardPageUp(driver);
			break;
		case "KeyboardPageDown":
			Navigate.keyboardPageDown(driver);
			break;
		case "KeyboardEnd":
			Navigate.keyboardEnd(driver);
			break;	
		case "KeyboardTab":
			Navigate.keyboardTab(driver);
			break;		
		case "PageMaximize":
			Navigate.pageMaximize(driver);
			break;	
		case "Enter":
			Navigate.enter(driver);
			break;	
		case "KeyboardArrowUp":
			Navigate.keyboardArrowUp(driver);
			break;	
		case "KeyboardArrowDown":
			Navigate.keyboardArrowDown(driver);
			break;	
		case "KeyboardArrowLeft":
			Navigate.keyboardArrowLeft(driver);
			break;	
		case "KeyboardArrowRight":
			Navigate.keyboardArrowRight(driver);
			break;			
		case "Drag":
			Manipulation.dragElement(driver, element);
			break;
		case "Drop":
			Manipulation.dropElement(driver, element);
			break;		
		case "VerifyElementIsSelected":
			Manipulation.elementIsSelected(driver, element);
			break;
		case "VerifyElementIsPresent":
			Manipulation.verifyElementIsPresent(driver, element);
			break;
		case "VerifyElementIsNotPresent":
			returnObj = Manipulation.verifyElementIsNotPresent(driver, element);
			break;
		case "VerifyElementIsEnable":
			Manipulation.elementIsEnable(driver, element);
			break;		
		case "WaitUntilVisibilityOfElement":
			Manipulation.visibilityElement(driver, element);
			break;	
		case "WaitUntilInvisibilityOfElement":
			Manipulation.inVisibilityElement(driver, normalXpath);
			break;			
		case "VerifyTextIsPresent":
			Manipulation.testIsPresent(driver,element, inputData);
			break;	
		case "WaitUntilTextToBeNotPresent":
			Manipulation.testIsNotPresent(driver,normalXpath, inputData);
			break;		
		case "WaitUntilTextToBePresent":
			Manipulation.textTobePresent(driver,element, inputData);
			break;	
		case "WaitUntilElementToBeClickable":
			Manipulation.elementTobeClickable(driver,element);
			break;	
		case "WaitUntilElementToBeSelected":
			Manipulation.elementToBeSelected(driver,element);
			break;	
		case "TextToBePresentInElementValue":
			Manipulation.textElementPresentValue(driver,element,inputData);
			break;		
		case "WaitForElementPresent":
			Manipulation.waitForElement(driver, normalXpath);
			break;
		case "WaitForElementNotPresent":
			Manipulation.waitForElementNotpresent(driver,normalXpath);
			break;	
		case "CheckTwoString":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));
				String getText2 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionMatch(getText1, getText2);
			}
			else /*if(inputData != null && referenceStep != null && !referenceStep.trim().equals(""))*/
			{
				int refStep2 = new Integer(referenceStep);
				String getText1 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.condtionMatch(inputData,getText1);		
			}
			break;		
		case "DeleteAllCookies":
			Navigate.deleteAllCookies(driver);
			break;			
		case "TakeScreeShot":
			Navigate.screenShot(driver,inputData);
			break;					
		case "Highlight":
			Navigate.highLightElement(driver,element);
			break;	
		case "NewTab":
			Navigate.newTab(driver);
			break;
		case "CloseTab":
			Navigate.closeTab(driver);
			break;	
		case "WaitForAjaxQuery":
			Manipulation.waitForAjax(driver);
			break;	
		case "SendHttpPost":
			returnObj=Navigate.sendHttpPost(inputData);
			break;
		case "SplitAndOpenURL":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int refStep = new Integer(referenceStep);
				String getText=getTextMap.get(Integer.valueOf(refStep));				
				String[] openURL = getText.split("https://www.google.de/");				
				driver.get(openURL[0]);
			}
			break;			

		case "ConcatStrings":
			String concat="";
			if (inputData == null && referenceStep != null
					&& !referenceStep.trim().equals("")){
				String[] splitReference=referenceStep.split(",");
				int size=splitReference.length;
				for(int i=0;i<size;i++){
					String getText12=getTextMap.get(Integer.valueOf(splitReference[i]));
					concat=concat+getText12;
				}
			}
			if (inputData != null && referenceStep == null
					&& !inputData.trim().equals("")) {
				splitInputData=inputData.split(",");
				int size=splitInputData.length;
				for(int i=0;i<size;i++)
				{
					concat=concat+splitInputData[i];
				}	
			}
			System.out.println(concat);
			returnObj=concat;
			break;
		case "Concat2String":
			String[] splitreference=referenceStep.split(",");
			int refStep12 = new Integer(splitreference[0]);
			int refStep13 = new Integer(splitreference[1]);
			String getText12=getTextMap.get(Integer.valueOf(refStep12));
			String getText13=getTextMap.get(Integer.valueOf(refStep13));
			String con = getText12.concat(getText13);
			returnObj=con;

		case "DynamicSendKeys":
			returnObj=Manipulation.dynamicSendkeysNumbers(driver, inputData, element);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "UploadFileUsingSendkeys":
			returnObj=Manipulation.uploadFileSendkeys(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "UploadFileReturnFileName":
			returnObj=Manipulation.uploadFileSendkeysReturnFileName1(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
			///////////    Steris      //////////
		case "CloseSterisTabs":
			returnObj=CommonMethods.sterisCloseTab(driver, inputData);
			break;				
		case "SterisURL":
			CommonMethods.URL(driver);
			break;
		case "SterisLogin":
			CommonMethods.sterisLogin(driver);
			break;
		case "SterisCaseColorVerification":
			CommonMethods.colorVerification(driver);
			break;		
		case "CSVFileCreation":
			returnObj=CommonMethods.writeCSVFile();
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ValidateUserDetails":
			returnObj=CommonMethods.verifyImportUserDetails(driver);
			break;
		case"ZoomOut":
			Manipulation.zoomout(driver);
			break;
		case"ReplaceSpecialCharacters":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")){				
				int input = new Integer(referenceStep);				
				String value=getTextMap.get(Integer.valueOf(input));
				String splitReference=value.replace(";", "");
				returnObj=splitReference;
				getTextMap.put(stepNo, returnObj.toString());
			}			
			break;
		case "DrawRectangle":
			returnObj=CommonMethods.drawrectangle(driver);	 
			break;
		case "DrawCircle":
			returnObj=CommonMethods.drawCircle(driver);
			break;
		case "InsertText":
			returnObj= CommonMethods.addtext(driver);
			break;
		case "SterisCaseColorVerification1":
			CommonMethods.colorVerification1(driver);
			break;
		case "SelectImage":
			CommonMethods.selectImage(driver);
			break;
		case "ImageComparision":
			returnObj=CommonMethods.testImageComparison(driver);
			break;
			///////// Resolve ///////////////////////////////////////////
		case "ResolveUserLogin":
			returnObj=ResolveCommonMethods.Login(driver,inputData);
			break;
		case "ResolveUserLogout":
			ResolveCommonMethods.Logout(driver);
			break;
		case "ResolveCloseCurrentTab":
			returnObj=ResolveCommonMethods.closeCurrentTab(driver);
			break;	
		case "SwitchToTab":
			//String count = inputData;
			//int inputwindow = Integer.parseInt(count);
			Navigate.switchtotab(driver,1);
			break;	
		case "VerifyPdfFile":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");    
				int size = referenceSteps.length;    
				widgetUrls=referenceSteps.length;
				for(int i=0;i<size;i++) {
					int referenceStepNumbers=new Integer(referenceSteps[i]);     
					ApplicationValue[i]=getTextMap.get(Integer.valueOf(referenceStepNumbers));
					System.out.println("Before Value : " +ApplicationValue[i]);
				} 			   
			}
			returnObj=ResolveCommonMethods.verifyPdfFile(driver);
			break;
		case "VerifyDownloadedFile":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int input = new Integer(referenceStep);    
				String inputValue=getTextMap.get(Integer.valueOf(input));
				returnObj=ResolveCommonMethods.verifydownloadedWordFile(driver,inputValue);    
			}    
			break;
		case "MoveDownloadFile":		
			ResolveCommonMethods.download(); 			   
			break;
		case "DownloadFile":		
			ResolveCommonMethods.DownloadFile(); 			   
			break;
		case "KeyboardHide":		
			Manipulation.HideKeboard(); 			   
			break;
			///////////////////////
		case "RestAssuredGETMethod":		
			RestAssuredApiCommonMethods.GET(inputData); 			   
			break;
		case "RestAssuredPOSTMethod":		
			RestAssuredApiCommonMethods.POST(inputData); 			   
			break;
		case "AddParameters":		
			RestAssuredApiCommonMethods.Parameter(inputData); 			   
			break;
		case "Login":
			NeonMobileCommonMethods.login(driver);
			break;
		case "CropImage":
			NeonMobileCommonMethods.cropImage(driver);
			break;
		case "DeleteMenu":
			NeonMobileCommonMethods.DeleteMenu(driver);
			break;
		case"SelectMenuItem":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")){				
				int input = new Integer(referenceStep);				
				String value=getTextMap.get(Integer.valueOf(input));			
				NeonMobileCommonMethods.selectMenu(driver, value); 
			}			
			break;
		case "GetCurrentWindow":
			Manipulation.getCurrentWindow(driver);
			break;
		case "GetSecondWindow":
			Manipulation.getSecondWindow(driver);
			break;
		case "SwitchWindow":
			Manipulation.SwitchTab(driver, inputData);	
			break;
		case "SelectBusiness":
			if (inputData != null && referenceStep != null
			&& !referenceStep.trim().equals("")){				
				int input = new Integer(referenceStep);				
				String Location=getTextMap.get(Integer.valueOf(input));
				NeonMobileCommonMethods.selectBusiness(driver, inputData,Location);				 
			}			
			break;
		case "ChromeBrowser":
			Manipulation.GetChromeBrowser(chromeDriver);	
			break;
		case "FireFoxBrowser":
			Manipulation.GetFireFoxBrowser(fireFoxDriver);	
			break;
		case "IEBrowser":
			Manipulation.GetIEBrowser(ieDriver);	
			break;
		case "SwitchChromeBrowser":
			Manipulation.SwitchChromeBrowser(fireFoxDriver,chromeDriver,ieDriver);	
			break;
		case "SwitchFireFoxBrowser":
			Manipulation.SwitchFireFoxBrowser(fireFoxDriver,chromeDriver,ieDriver);	
			break;
		case "SwitchIEBrowser":
			Manipulation.SwitchIEBrowser(fireFoxDriver,chromeDriver,ieDriver);	
			break;
		case "CheckStringValues":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				String[] referenceSteps = StringUtils.split(referenceStep, ",");
				int refStep1 = new Integer(referenceSteps[0]);
				int refStep2 = new Integer(referenceSteps[1]);
				String getText1 = getTextMap.get(Integer.valueOf(refStep1));
				String getText2 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.stringCondtionMatch(getText1, getText2);
			}
			else /*if(inputData != null && referenceStep != null && !referenceStep.trim().equals(""))*/
			{
				int refStep2 = new Integer(referenceStep);
				String getText1 = getTextMap.get(Integer.valueOf(refStep2));
				returnObj = Manipulation.stringCondtionMatch(inputData,getText1);		
			}
			break;		
			/////////////////////////////////
		case "CheckProduct":
			String[] splitReferenceNum=referenceStep.split(",");
			int product1RefStep = new Integer(splitReferenceNum[0]);
			int productRefStep = new Integer(splitReferenceNum[1]);
			String product1=getTextMap.get(Integer.valueOf(product1RefStep));
			String product2=getTextMap.get(Integer.valueOf(productRefStep));
			returnObj = Manipulation.checkTwoProduct(product1, product2);
			break;
		case "CheckTwoStringValues":
			String[] splitReferenceNum1=referenceStep.split(",");
			int product1RefStep1 = new Integer(splitReferenceNum1[0]);
			int productRefStep1 = new Integer(splitReferenceNum1[1]);
			String product11=getTextMap.get(Integer.valueOf(product1RefStep1));
			String product21=getTextMap.get(Integer.valueOf(productRefStep1));
			returnObj = Manipulation.checkTwoValues(product11, product21);
			break;
		case "GETFAIRURL":
			Navigate.getFair(driver);
			break;	
		case "GETANLURL":
			Navigate.getANL(driver);
			break;
		case "GetScreenResolution":
			returnObj=LayoutActions.screenResolution(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "CompareScreenResolution":
			returnObj=LayoutActions.compareScreenResolution(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetElementResolution":
			returnObj=LayoutActions.elementResolution(driver,element);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyElementsHorizontally":
			String[] splitreference123=referenceStep.split(",");
			int refStep123 = new Integer(splitreference123[0]);
			int refStep23 = new Integer(splitreference123[1]);
			String getText123=getTextMap.get(Integer.valueOf(refStep123));
			String getText23=getTextMap.get(Integer.valueOf(refStep23));
			returnObj=LayoutActions.verifyElementsHorizontally(getText123, getText23);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyElementsVertically":
			String[] splitreference1234=referenceStep.split(",");
			int rrefStep123 = new Integer(splitreference1234[0]);
			int rrefStep23 = new Integer(splitreference1234[1]);
			String rgetText123=getTextMap.get(Integer.valueOf(rrefStep123));
			String rgetText23=getTextMap.get(Integer.valueOf(rrefStep23));
			returnObj=LayoutActions.verifyElementsVertically(rgetText123, rgetText23);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyInnerOutterElements":
			returnObj=LayoutActions.verifyInnerOutterElements(driver, element1, element2);
			break;
		case "CompareImage":
			returnObj=LayoutActions.compareImage(driver, element, inputData);
			break;
			//////////// Closer Connect Common Methods ///////////
		case "GetStarted":
			CloserConnect.getStarted(driver);
			break;
		case "GetCloserConnectURL":
			CloserConnect.getUrl(driver);
			break;
		case "GetCloserConnectLogin":
			returnObj=CloserConnect.Login(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "GetCloserConnectLogout":
			CloserConnect.Logout(driver);
			break;
		case "ActionClearAndType":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			returnObj=CloserConnect.actionClearAndType(driver,element,inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "UploadFile":
			returnObj=CloserConnect.uploadFileSendkeysReturnFileName(element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "ZohoLoginAndVerifyPublishShareLink":
			returnObj=CloserConnect.zohoLogin(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "HandleSecondWindow":
			Navigate.handlesecondWindow(driver,element);		
			break;
		case "SwitchSecondWindow":
			Navigate.switchSecondWindow(driver);		
			break;
		case "SwitchMainWindow":
			Navigate.MainWindow(driver);
			break;
		case "ScrollToView":
			Navigate.scrollToView(driver, element);
			break;	
		case "ValidateViewsAndDownloads":
			String[] steps = referenceStep.split(",");
			int refStepBefore = new Integer(steps[0]);
			String BeforeinputData= getTextMap.get(Integer.valueOf(refStepBefore));
			int refStepAfter = new Integer(steps[1]);
			String AfterinputData= getTextMap.get(Integer.valueOf(refStepAfter));
			returnObj=CloserConnect.validateViewsandDownloads(BeforeinputData, AfterinputData);
			getTextMap.put(stepNo, returnObj.toString());					
			break;
		case "DeleteSalesProject":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			CloserConnect.deleteSalesProject(driver,inputData);			
			break;
		case "DeleteLearningProject":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals(""))
			{
				int refStep = new Integer(referenceStep);
				inputData= getTextMap.get(Integer.valueOf(refStep));
			}
			CloserConnect.deleteLearningProject(driver,inputData);			
			break;
		case "MouseOverOnImage":
			CloserConnect.mouseOveronImage(driver);
			break;
		case "SplitStringValues":
			int refStep = new Integer(referenceStep);
			String inputValue= getTextMap.get(Integer.valueOf(refStep));
			returnObj=Navigate.splitValues(inputValue, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyDownloadedFile1":
			if (inputData == null && referenceStep != null
			&& !referenceStep.trim().equals("")) {
				int input = new Integer(referenceStep);    
				String inputValue1=getTextMap.get(Integer.valueOf(input));
				returnObj=Telematics.verifydownloadedWordFile(driver,inputValue1);    
			}    
			break;
		case "MoveDownloadFile1":		
			Telematics.download(); 			   
			break;
		case "DownloadFile1":		
			Telematics.DownloadFile(); 			   
			break;
		case "CancelFile":		
			Telematics.Cancel(); 			   
			break;

			///////////Gem25
		case "Gem25Login":		
			Gem25.gem25Login(driver); 			   
			break;
		case "EnableAlarm":		
			try	{				
				int input = new Integer(referenceStep);    
				String inputValue1=getTextMap.get(Integer.valueOf(input));
				returnObj = Gem25.enableAlarm(driver, inputValue1);					
			}catch(Exception e)	{				
			}
			break;
		case "VerifyDashboardAlarm":		
			try {
				int input1 = new Integer(referenceStep);    
				String inputValue11=getTextMap.get(Integer.valueOf(input1));
				returnObj = Gem25.selectDashboardAlarm(driver, inputValue11);					
			}catch(Exception e)	{				
			}
			break;
		case "AlertAccept":
			returnObj = Navigate.alertAccept(driver);
			break;	
		case "ExecuteSSHCommands":
			SSH.executeShllscripts(inputData);
			break;	
		case "AlarmLogsLoggedDateTime":
			returnObj=Gem25.getAlarmlogLoggedDateTime(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;	
		case "AlarmLogsLoggedDescription":
			returnObj=Gem25.getAlarmlogLoggedDescription(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "VerifyAlarmLogs":
			String[] logdatas=referenceStep.split(",");
			int date = new Integer(logdatas[0]);
			int logdate = new Integer(logdatas[1]);
			int logdescription = new Integer(logdatas[2]);
			String DashboarLoggedDate=getTextMap.get(Integer.valueOf(date));
			String AlarmLogsLoggedDate=getTextMap.get(Integer.valueOf(logdate));
			String AlarmLogsDescription=getTextMap.get(Integer.valueOf(logdescription));
			returnObj=Gem25.verifyLoggedResults(DashboarLoggedDate, AlarmLogsLoggedDate, AlarmLogsDescription);		
			break;
		case "VerifyAlarmLogsTriggeredOff":
			String[] log=referenceStep.split(",");
			int date1 = new Integer(log[0]);			
			int logdescription1 = new Integer(log[1]);
			String DashboarLoggedDate1=getTextMap.get(Integer.valueOf(date1));			
			String AlarmLogsDescription1=getTextMap.get(Integer.valueOf(logdescription1));
			returnObj=Gem25.verifyLoggedTriggeredOffResults(DashboarLoggedDate1, AlarmLogsDescription1);
			break;
		case "WaitForAlarmLogs":
			Gem25.waitForAlarmLogs(driver);			
			break;
		case "ExecuteGem25BoardsshCommands":
			Gem25_Board_SSH.executeShllscripts(inputData);
			break;	
		case "DisableAlarm":		
			try {
				int inputdisable = new Integer(referenceStep);    
				String inputValuedisable=getTextMap.get(Integer.valueOf(inputdisable));
				returnObj = Gem25.disabledAlarm(driver, inputValuedisable);	
			}catch(Exception e)	{				
			}
			break;
		case "EditAlarm":	
			try {
				int edit = new Integer(referenceStep);    
				String alarmedit=getTextMap.get(Integer.valueOf(edit));
				returnObj = Gem25.editAlarm(driver, alarmedit);		
			}catch(Exception e)	{				
			}
			break;
		case "VerifyAlarmLoggedDateTime":		
			int log1 = new Integer(referenceStep);    
			String logged=getTextMap.get(Integer.valueOf(log1));
			returnObj = Gem25.verifyAlarmLoggedDate(driver, logged);		   
			break;
		case "VerifyAlarmPresntInDashboardPage":		
			try	{
				int input11 = new Integer(referenceStep);    
				String inputValue111=getTextMap.get(Integer.valueOf(input11));
				returnObj = Gem25.verifyDashboardAlarm(driver, inputValue111);		
			}catch(Exception e)	{				
			}
			break;
		case "LogCount":		
			returnObj = Gem25.logCount(driver);
			getTextMap.put(stepNo, returnObj.toString());
			break;
		case "DeleteAlarm":	
			try {
				returnObj = Gem25.deleteAlarm(driver);	
			}catch(Exception e)	{				
			}
			break;
		case "DeleteSingleAlarm":	
			try{
				int edit1 = new Integer(referenceStep);    
				String alarmenames=getTextMap.get(Integer.valueOf(edit1));
				returnObj = Gem25.SingleAlarmDelete(driver, alarmenames);
			}catch(Exception e)	{				
			}
			break;
		case "RebootAndLogin":	
			try {
				returnObj = Gem25.gem25RebootAndLogin(driver);
			}catch(Exception e)	{				
			}
			break;
		case "AlertVerification":	
			try {
				returnObj = Navigate.verifyalertAccept(driver, inputData);
			}catch(Exception e)	{				
			}
			break;
		case "AlarmFieldsVerification":	
			try {
				int edit1 = new Integer(referenceStep);    
				String alarmenames=getTextMap.get(Integer.valueOf(edit1));
				returnObj = Gem25.alarmfieldsverification(driver,alarmenames);
			}catch(Exception e)	{				
			}
			break;
		case "TypeAliasName":		
			returnObj = Gem25.Type(driver, element, inputData);
			getTextMap.put(stepNo, returnObj.toString());
			break;

		}
		return returnObj;
	}
}
