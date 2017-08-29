package zautomate.zadoqa.datadriver;

/**
 * Executing and add results in reports
 */
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.util.AndroidCommandUtils;
import zautomate.zadoqa.enums.LogAs;
import zautomate.zadoqa.reports.CaptureScreen;
import zautomate.zadoqa.reports.CaptureScreen.ScreenshotOf;
import zautomate.zadoqa.util.CommandUtils;
import zautomate.zadoqa.utils.Directory;

public class TestCaseRunner {

	public static void exectuteTestCase(AndroidDriver adriver,IOSDriver idriver,WebDriver driver, List<CaseStep> steps) throws Exception {
		Iterator<CaseStep> stepIterator = steps.iterator();

		while (stepIterator.hasNext()) {
			CaseStep eachStep = stepIterator.next();			
			CommandUtils util = new CommandUtils();
			AndroidCommandUtils autil = new AndroidCommandUtils(); 
			WebElement element = null;
			if (eachStep.getOrLocator()!=null) {		
				try
				{
					if(Directory.browser.equalsIgnoreCase("android")) {
						element = autil.findElement(adriver, eachStep.getInputData(),eachStep.getLocateBy(),
								eachStep.getOrLocator(),eachStep.getOrLocatorStart(),eachStep.getOrLocatorEnd(),eachStep.getReferenceStep());
					}
					else if(Directory.browser.equalsIgnoreCase("IOS")) {
						element = util.findElement(idriver, eachStep.getInputData(),eachStep.getLocateBy(),
								eachStep.getOrLocator(),eachStep.getOrLocatorStart(),eachStep.getOrLocatorMid(),eachStep.getOrLocatorEnd(),eachStep.getReferenceStep());
					}
					else {
						element = util.findElement(driver, eachStep.getInputData(),eachStep.getLocateBy(),
								eachStep.getOrLocator(),eachStep.getOrLocatorStart(),eachStep.getOrLocatorMid(),eachStep.getOrLocatorEnd(),eachStep.getReferenceStep());
					}
				}
				catch(NoSuchElementException exception) {
					throw exception;
				}
			}
			if(Directory.browser.equalsIgnoreCase("android")) {
				if(eachStep.getAction().contentEquals("ScreenShot"))
				{
					//ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString("ScreenShot Added Successfully", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString("ScreenShot Added Successfully", ""),LogAs.PASSED, null);
				}
				else
				{
					Object returnObj = autil.executeAction(adriver, element, eachStep.getAction(),
							eachStep.getInputData(),eachStep.getStepNo(),eachStep.getReferenceStep());	    
							//ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, null);
				}
			}
			else if(Directory.browser.equalsIgnoreCase("IOS")) {
				Object returnObj = util.executeAction(idriver, element, eachStep.getAction(),
						eachStep.getInputData(),eachStep.getStepNo(),eachStep.getReferenceStep());	    
				//ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
								ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, null);

			}
			else if(eachStep.getAction().contentEquals("ScreenShot"))
			{
				//ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString("ScreenShot Added Successfully", ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString("", ""),LogAs.PASSED, null);

			}
			else {
				Object returnObj = util.executeAction(driver, element, eachStep.getAction(),
						eachStep.getInputData(),eachStep.getStepNo(),eachStep.getReferenceStep());	    
				//ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				ZadoReports.add(eachStep.getAction(),eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, null);
			}
		}
	}
}