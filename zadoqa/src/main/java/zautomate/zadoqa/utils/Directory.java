package zautomate.zadoqa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.enums.ReportLabels;
import zautomate.zadoqa.exceptions.ZadoReporterException;
import zautomate.zadoqa.writers.HTMLDesignFilesWriter;
/**
 * Configurations
 * @author Babu 
 *
 */
public class Directory {

	static Logger log = Logger.getLogger(Directory.class.getName());
	public static final String CURRENTDir = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static String REPORTSDIRName = "Zado Reports";
	public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
	public static String RESULTSDir = REPORTSDir + SEP + "Results";
	public static String HTMLDESIGNDIRName = "HTML_Design_Files";
	public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
	public static String CSSDIRName = "CSS";
	public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
	public static String IMGDIRName = "IMG";
	public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
	public static String JSDIRName = "JS";
	public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
	public static String RUNName = "Test Execution_1"; // Babu
	public static String RUNDir = RESULTSDir + SEP + RUNName;
	public static String SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
	public static final char JS_SETTINGS_DELIM = ';';
	public static final String REPO_DELIM = "##@##@##";
	public static final char JS_FILE_DELIM = ',';
	public static final String MENU_LINK_NAME = "Run ";
	public static final String SCREENSHOT_TYPE = "PNG";
	public static final String SCREENSHOT_EXTENSION = ".PNG";
	private static String logo = null;
	public static String SCREENSHOT_DIRName = "img";
	public static boolean generateConfigReports = true;
	public static boolean generateExcelReports = false;
	public static boolean takeScreenshot = false;
	public static boolean continueExecutionAfterStepFailed = false;
	public static boolean recordExecutionAvailable = false;
	public static boolean recordSuiteExecution = false;
	public static boolean recordTestMethodExecution = false;
	public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";
	public static String userName=null;
	public static String password=null;
	public static String smtpHost=null;
	public static String fromAddress=null;
	public static String toAddress=null;
	public static String ccAddress=null;
	//public static String bccAddress=null;
	public static String testCasePath=null;
	public static String ORSheetPath=null;
	public static String browser=null;
	public static String Subject=null;
	public static String Reports_Path=null;
	public static String Zipfolder_Path=null;
	public static String WIDGET_HTML_FILE=null;
	public static String WaitFor=null;
	public static String uploadFilePath=null;
	public static String CMS_NOC_URL=null;
	public static String DEMO_URL=null;
	public static String CMS_Write_URL=null;
	public static String CMS_Read_URL=null;
	public static String CMS_NA_URL=null;
	public static String URL_PROPERTIES=null;
	public static boolean reexecution=true;
	//Mobile Configuration
	public static String MOBILE_APPPATH=null;
	public static String MOBILEAPP_APK_NAME=null;
	public static String MOBILE_DEVICE_NAME=null;
	public static String MOBILE_DEVICE_VERSION=null;
	public static String MOBILE_APK_APPPACKAGE=null;
	public static String MOBILE_DEVICE_TYPE=null;
	public static String MOBILE_IOSDEVICE_UDID= null;
	public static String MOBILE_APP_TYPE=null;
	public static String MOBILE_WEB_BROWSER_NAME=null;
	public static String MOBILE_WEB_URL=null;
	//Resolution
	public static String MOBILE_SCREEN_RESOLUTION_SIZE=null;
	public static String WEB_SCREEN_RESOLUTION_SIZE=null;
	//POC
	//////////////////////////////////////////////
	public static String STERIS_URL = null;
	public static String STERIS_USER_NAME = null;
	public static String STERIS_PASS_WORD = null;
	public static String RESOLVE_URL =null;
	public static String RESOLVE_USER = null;
	public static String RESOLVE_RCU_ADMIN_USERNAME = null;
	public static String RESOLVE_RCU_ADMIN_PASSWORD = null;	
	public static String RESOLVE_RCU_MANAGER_USERNAME = null;
	public static String RESOLVE_RCU_MANAGER_PASSWORD = null;	
	public static String RESOLVE_RCU_OFFICER_USERNAME = null;
	public static String RESOLVE_RCU_OFFICER_PASSWORD = null;	
	public static String RESOLVE_PCEP_ADMIN_USERNAME = null;
	public static String RESOLVE_PCEP_ADMIN_PASSWORD = null;	
	public static String RESOLVE_PCEP_MANAGER_USERNAME = null;
	public static String RESOLVE_PCEP_MANAGER_PASSWORD = null;	
	public static String RESOLVE_PCEP_OFFICER_USERNAME = null;
	public static String RESOLVE_PCEP_OFFICER_PASSWORD = null;
	public static String NEON_USER_NAME = null;
	public static String NEON_PASS_WORD = null;
	public static String FAIR_URL = null;
	public static String ANL_URL = null;
	public static String CLOSER_CONNECT_URL = null;
	public static String CLOSER_CONNECT_USERNAME = null;
	public static String CLOSER_CONNECT_PASSWORD = null;	
	public static String CLOSER_CONNECT_PUBLISH_MAIL = null;	
	public static String ZOHO_URL = null;
	public static String ZOHO_USERNAME = null;
	public static String ZOHO_PASSWORD = null;	
	///GEM25
	public static String GEM25_URL = null;
	public static String GEM25_HOST = null;
	public static String GEM25_USERNAME = null;
	public static String GEM25_PASSWORD = null;
	public static String GEM25_SSH_USERNAME = null;
	public static String GEM25_SSH_PASSWORD = null;
	public static String GEM25_SSH_CLEARSESSION = null;
	public static String GEM25_SIMULATOR_HOST = null;
	public static String GEM25_SIMULATOR_USERNAME = null;
	public static String GEM25_SIMULATOR_PASSWORD = null;
	
	/**
	 * Retrieve values from custom properties
	 * @throws ZadoReporterException
	 * @throws Exception 
	 */
	public static void init() throws ZadoReporterException, Exception {
		if (getCustomProperties() != null) {
			log.info("Reading from custom properties");
			Properties localProperties = new Properties();

			try {
				localProperties.load(new FileReader(getCustomProperties()));
				String reportsDir = localProperties.getProperty("zado.reports.dir")			.trim();
				String headerText = localProperties.getProperty(			"zado.proj.header.text").trim();
				logo = localProperties.getProperty("zado.proj.header.logo")			.trim();
				String projectDescription = localProperties.getProperty(			"zado.proj.description").trim();
				String takeScreenShot = localProperties.getProperty(			"zado.reports.takescreenshot").trim();
				String configReports = localProperties.getProperty(			"zado.reports.configurationreports").trim();
				String excelReport = localProperties.getProperty("zado.reports.excel")			.trim();
				String contExectution = localProperties.getProperty(			"zado.reports.continueExecutionAfterStepFailed").trim();
				String reExecution = localProperties.getProperty(			"zado.failures.reexecution").trim();

				userName = localProperties.getProperty(			"zado.mail.username").trim();
				password = localProperties.getProperty(			"zado.mail.password").trim();
				smtpHost = localProperties.getProperty(			"zado.mail.smtp.host").trim();
				fromAddress = localProperties.getProperty(			"zado.mail.from.address").trim();
				toAddress = localProperties.getProperty(			"zado.mail.to.address").trim();
				ccAddress = localProperties.getProperty(				"zado.mail.cc.address").trim();	
				Subject = localProperties.getProperty(				"zado.mail.subject").trim();
				Reports_Path = localProperties.getProperty(				"zado.reports.dir").trim();
				Zipfolder_Path = localProperties.getProperty(				"zado.mail.zipfolder").trim();
				testCasePath = localProperties.getProperty(			"zado.proj.testcasePath").trim();
				ORSheetPath = localProperties.getProperty(			"zado.proj.ORSheet.path").trim();
				browser = localProperties.getProperty(			"zado.browser.name").trim().toLowerCase();
				WaitFor = localProperties.getProperty(			"zado.proj.waitSec").trim().toLowerCase();
				uploadFilePath=localProperties.getProperty(			"zado.proj.upload").trim();

				//Mobile Configuration
				MOBILE_APPPATH=localProperties.getProperty(			"zado.mobileapp.apk.path").trim();
				MOBILEAPP_APK_NAME=localProperties.getProperty(			"zado.mobile.apk.name").trim();
				MOBILE_DEVICE_NAME=localProperties.getProperty(			"zado.mobile.devicename").trim();
				MOBILE_DEVICE_VERSION=localProperties.getProperty(			"zado.mobile.version").trim();
				MOBILE_APK_APPPACKAGE=localProperties.getProperty(			"zado.mobile.apppackage.name").trim();
				MOBILE_APP_TYPE = localProperties.getProperty(			"zado.mobile.app.type").trim();
				MOBILE_WEB_BROWSER_NAME = localProperties.getProperty(			"zado.mobile.web.browser").trim();
				MOBILE_WEB_URL = localProperties.getProperty(			"zado.mobile.web.Url").trim();
				MOBILE_DEVICE_TYPE = localProperties.getProperty(			"zado.mobile.device.type").trim();
				MOBILE_IOSDEVICE_UDID = localProperties.getProperty(			"zado.ios.mobile.udid").trim();
				//Resolution
				MOBILE_SCREEN_RESOLUTION_SIZE = localProperties.getProperty(			"zado.mobile.screen.resolution").trim();
				WEB_SCREEN_RESOLUTION_SIZE=localProperties.getProperty(			"zado.web.screen.resolution.size").trim();

				Properties urlProperties = new Properties();
				InputStream input = new FileInputStream(testCasePath+"/URL.properties");
				urlProperties.load(input);		

				/////////////// POC Closer Connect	/////////////////
				CLOSER_CONNECT_URL = urlProperties.getProperty(			"closer.connect.url").trim();
				CLOSER_CONNECT_USERNAME = urlProperties.getProperty(			"closer.connect.username").trim();
				CLOSER_CONNECT_PASSWORD = urlProperties.getProperty(			"closer.connect.password").trim();
				CLOSER_CONNECT_PUBLISH_MAIL = urlProperties.getProperty(			"closer.connect.publish.mail.id").trim();
				ZOHO_URL = urlProperties.getProperty(			"zoho.url").trim();				
				ZOHO_USERNAME = urlProperties.getProperty(			"zoho.username").trim();
				ZOHO_PASSWORD = urlProperties.getProperty(			"zoho.password").trim();
				///GEM25
				GEM25_URL = urlProperties.getProperty(			"gem.url").trim();			
				GEM25_HOST = urlProperties.getProperty(			"gem.host").trim();				
				GEM25_USERNAME = urlProperties.getProperty(			"gem25.username").trim();
				GEM25_PASSWORD = urlProperties.getProperty(			"gem25.password").trim();
				GEM25_SSH_USERNAME = urlProperties.getProperty(			"gem.username").trim();				
				GEM25_SSH_PASSWORD = urlProperties.getProperty(			"gem.password").trim();
				GEM25_SSH_CLEARSESSION = urlProperties.getProperty(			"gem.command").trim();
				GEM25_SIMULATOR_HOST = urlProperties.getProperty(			"gem.simulator.host").trim();		
				GEM25_SIMULATOR_USERNAME = urlProperties.getProperty(			"gem.simulator.username").trim();
				GEM25_SIMULATOR_PASSWORD = urlProperties.getProperty(			"gem.simulator.password").trim();
				
				
				
				/*
				 *  #FlyOnit
					#Fair Value Solar
					zado.fair.url = http://www.fairvaluesolar.com.au/
					#ANL lighting
					zado.anl.url = http://www.anllighting.com.au/
					FAIR_URL=urlProperties.getProperty(			"zado.fair.url").trim();
					ANL_URL=urlProperties.getProperty(			"zado.anl.url").trim();
				 */

				/*				
				//WIDGET_HTML_FILE = urlProperties.getProperty(			"zado.proj.widget").trim();
				CMS_NOC_URL=urlProperties.getProperty(			"ct.cms.noc.url").trim();
				CMS_Write_URL=urlProperties.getProperty(			"ct.cms.write.url").trim();
				CMS_Read_URL=urlProperties.getProperty(			"ct.cms.read.url").trim();
				CMS_NA_URL=urlProperties.getProperty(			"ct.cms.na.url").trim();
				/////////////// POC /////////////////
				STERIS_URL = urlProperties.getProperty(			"steris.url").trim();
				STERIS_USER_NAME = urlProperties.getProperty(			"steris.username").trim();
				STERIS_PASS_WORD = urlProperties.getProperty(			"steris.password").trim();
				RESOLVE_URL = urlProperties.getProperty(			"resolve.url").trim();
				RESOLVE_USER = urlProperties.getProperty(			"resolve.user").trim();				
				RESOLVE_RCU_ADMIN_USERNAME = urlProperties.getProperty(			"reslove.rcu.admin.user").trim();
				RESOLVE_RCU_ADMIN_PASSWORD = urlProperties.getProperty(			"reslove.rcu.admin.password").trim();
				RESOLVE_RCU_MANAGER_USERNAME = urlProperties.getProperty(			"reslove.rcu.manager.user").trim();
				RESOLVE_RCU_MANAGER_PASSWORD = urlProperties.getProperty(			"reslove.rcu.manager.password").trim();
				RESOLVE_RCU_OFFICER_USERNAME = urlProperties.getProperty(			"reslove.rcu.officer.user").trim();
				RESOLVE_RCU_OFFICER_PASSWORD = urlProperties.getProperty(			"reslove.rcu.officer.password").trim();
				RESOLVE_PCEP_ADMIN_USERNAME = urlProperties.getProperty(			"reslove.pcep.admin.user").trim();
				RESOLVE_PCEP_ADMIN_PASSWORD = urlProperties.getProperty(			"reslove.pcep.admin.password").trim();
				RESOLVE_PCEP_MANAGER_USERNAME = urlProperties.getProperty(			"reslove.pcep.manager.user").trim();
				RESOLVE_PCEP_MANAGER_PASSWORD = urlProperties.getProperty(			"reslove.pcep.manager.password").trim();
				RESOLVE_PCEP_OFFICER_USERNAME = urlProperties.getProperty(			"reslove.pcep.officer.user").trim();
				RESOLVE_PCEP_OFFICER_PASSWORD = urlProperties.getProperty(			"reslove.pcep.officer.password").trim();
				NEON_USER_NAME = urlProperties.getProperty(			"neon.username").trim();
				NEON_PASS_WORD = urlProperties.getProperty(			"neon.password").trim();
				 */

				try {
					if ((headerText != null) && (headerText.length() > 0)) {
						ReportLabels.HEADER_TEXT.setLabel(headerText);
					}
					if ((takeScreenShot != null) && (takeScreenShot.length() > 0)) {
						try {
							takeScreenshot = Boolean.parseBoolean(takeScreenShot);
						} catch (Exception localException1) {
						}
					}
					if ((reExecution != null) && (reExecution.length() > 0)) {
						try {
							reexecution = Boolean.parseBoolean(reExecution);
						} catch (Exception localException1) {
						}
					}
					if ((configReports != null) && (configReports.length() > 0)) {
						try {
							generateConfigReports = Boolean.parseBoolean(configReports);
						} catch (Exception localException2) {
						}
					}
					if ((excelReport != null) && (excelReport.length() > 0)) {
						try {
							generateExcelReports = Boolean.parseBoolean(excelReport);
						} catch (Exception localException3) {
						}
					}
					if ((contExectution != null) && (contExectution.length() > 0)) {
						try {
							continueExecutionAfterStepFailed = Boolean
									.parseBoolean(contExectution);
						} catch (Exception localException4) {
						}
					}
					/*if ((recordExecution != null) && (recordExecution.length() > 0)) {
			try {
			    RecordingFor localRecordingFor = RecordingFor
				    .valueOf(recordExecution.toUpperCase());
			    if (localRecordingFor == RecordingFor.SUITE) {
				recordSuiteExecution = true;
			    } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
				recordTestMethodExecution = true;
			    }
			} catch (Throwable localThrowable) {
			}
		    }*/
					File outputFile = new File(Directory.Zipfolder_Path);
					if(!outputFile.exists()){
						outputFile.mkdir();
					}
					File file = new File(reportsDir);
					if(file.exists())
					{
						/*FileUtils.cleanDirectory(file); //clean out directory (this is optional -- but good know)
			            FileUtils.forceDelete(file);
						System.out.println("Report directory deleted");*/
					}
					if ((projectDescription != null) && (projectDescription.length() > 0)) {
						ZadoReports.indexPageDescription = projectDescription;
					}
					if ((reportsDir != null) && (reportsDir.length() > 0)) {
						REPORTSDir = reportsDir;
						REPORTSDIRName = new File(REPORTSDir).getName();
						RESULTSDir = REPORTSDir + SEP + "Results";
						HTMLDESIGNDIRName = "HTML_Design_Files";
						HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
						CSSDIRName = "CSS";
						CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
						IMGDIRName = "IMG";
						IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
						JSDIRName = "JS";
						JSDir = HTMLDESIGNDir + SEP + JSDIRName;
						RUNName = "Run_";
						RUNDir = RESULTSDir + SEP + RUNName;
						SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
					}
				} catch (Exception localException5) {
					throw new ZadoReporterException(localException5.toString());
				}
			} catch (FileNotFoundException localFileNotFoundException) {
				throw new ZadoReporterException(
						"The Path for the Custom Properties file could not be found");
			} catch (IOException localIOException) {
				throw new ZadoReporterException(
						"Problem Occured while reading the Zado Reporter Config File");
			}
		}
	}

	public static void mkDirs(String paramString) {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
	}

	public static boolean exists(String paramString) {
		File localFile = new File(paramString);
		return localFile.exists();
	}
	/**
	 * Verifying required files for report generation
	 * @throws Exception 
	 */
	public static void verifyRequiredFiles() throws Exception {
		init();
		log.info("Setting Reports Dir---"+REPORTSDir);
		log.info("Setting Results Dir---"+RESULTSDir);
		mkDirs(REPORTSDir);
		if (!exists(RESULTSDir)) {
			mkDirs(RESULTSDir);
			SettingsFile.initSettingsFile();
		}
		if (!exists(HTMLDESIGNDir)) {
			mkDirs(HTMLDESIGNDir);
			mkDirs(CSSDir);
			mkDirs(JSDir);
			mkDirs(IMGDir);
			HTMLDesignFilesWriter.writeCSS();
			HTMLDesignFilesWriter.writeIMG();
			HTMLDesignFilesWriter.writeJS();
		}
		if ((logo != null) && (logo.length() > 0)) {
			String str = new File(logo).getName();
			if (!new File(IMGDir + SEP + str).exists()) {
				copyImage(logo);
			}
			ReportLabels.PROJ_LOGO.setLabel(str);
		}
	}

	private static void copyImage(String paramString) throws ZadoReporterException {
		File localFile = new File(paramString);
		if (!localFile.exists()) {
			return;
		}
		FileImageInputStream localFileImageInputStream = null;
		FileImageOutputStream localFileImageOutputStream = null;
		try {
			localFileImageInputStream = new FileImageInputStream(new File(
					paramString));
			localFileImageOutputStream = new FileImageOutputStream(new File(
					IMGDir + SEP + localFile.getName()));
			int i = 0;
			while ((i = localFileImageInputStream.read()) >= 0) {
				localFileImageOutputStream.write(i);
			}
			localFileImageOutputStream.close();
			return;
		} catch (Exception localException2) {
		} finally {
			try {
				localFileImageInputStream.close();
				localFileImageOutputStream.close();
				localFile = null;
			} catch (Exception localException4) {
				localFileImageInputStream = null;
				localFileImageOutputStream = null;
				localFile = null;
			}
		}
	}

	public static String getCustomProperties() {
		return System.getProperty("zado.reporter.config");
	}

	public static String createTestRunDateTime() {
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "dd-MM-yy, hh.mm aa");
	}

	public static String getTestRunDateTime(int run) {
		try {
			String testRun = SettingsFile.get("testRunDT");
			String timeArray [] = testRun.split(";");
			return timeArray[run-1];
		} catch (ZadoReporterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return " ";
	}
}
