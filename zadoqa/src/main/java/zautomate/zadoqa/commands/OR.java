package zautomate.zadoqa.commands;

public interface OR {

	
	/*
	 * Gem25
	 * 
	 */
	public static String GEM25_PASSWORD = "password"; //ID
	public static String GEM25_LOGIN_BUTTON = "Login"; //Name
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Closer Connect 
	 */
	
	public static final String CLOSER_GET_STARTED = "//button[@class='btn btn-primary' and contains(text(),'GET STARTED')]";
	public static final String CLOSER_WELCOME_OK = "//button[@ng-click='ok()']";
	public static final String CLOSER_PAGE_LOGO = "//div[@class='cc-logo']";
	public static final String CLOSER_LOGIN_LINK = "//span[text()='Login']";
	public static final String CLOSER_USER_NAME_TEXTBOX =	"email";//ByName
	public static final String CLOSER_PASS_WORD_TEXTBOX =	"password";//ByName
	public static final String CLOSER_SIGN_IN_BUTTON = "//button[@type='submit']";
	public static final String CLOSER_LOGGED_USER_NAME = "//li[@class='top-menu-item dropdown']/a[@class='dropdown-toggle']//span[@class='hidden-xs ng-binding']";
	public static final String CLOSER_LOGOUT_BUTTON = "//a[contains(text(),'Logout')]";
	
	public static final String ZOHO_LOGIN_FRAME = "//*[@id='zohoiam']";
	public static final String ZOHO_USERNAME_TEXTBOX = "lid";//ByName
	public static final String ZOHO_PASSWORD_TEXTBOX = "pwd";//ByName
	public static final String ZOHO_SIGN_IN_BUTTON = "//button[@id='submit_but']";
	public static final String ZOHO_INBOX_TAB = "//div[contains(@title,'Inbox')]";
	public static final String ZOHO_INBOX_UNREAD_MAIL = "//div[@id='zm_centerHolder']/div/div//div//div[contains(@class,'zm_urd')]";
	public static final String PUBLISH_URL_LINK = "//div[1]/table/tbody/tr/td/div/a[2]";
	
	public static final String MY_SALES_PROJECT_TAB = "//span[contains(text(),'My Sales Projects')]";
	public static final String MY_LEARNING_PROJECT_TAB = "//span[contains(text(),'My Learning Projects')]";
	public static final String MY_SALES_PROJECT_PAGE_TITLE = "//h1[text()='My Sales Projects']";
	public static final String MY_LEARNING_PROJECT_PAGE_TITLE = "//h1[text()='My Learning Projects']";
	public static final String PROJECT_ACTION_BUTTON = "//div[contains(text(),'";
	public static final String PROJECT_ACTION_BUTTON_1 = "')]//following-sibling::div/span[@class='dropdown']/a[contains(text(),'actions')]";
	public static final String PROJECT_DELETE_BUTTON = "//span[@class='dropdown open']//ul//li/a//span[text()='delete']";
	public static final String PROJECT_DELETE_YES_BUTTON = "//button[text()='Yes']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Neon Mobile 
	 */
	public static final String NEON_LOGIN_PAGE_TITLE = "//div[@class='form-title']";
	public static final String NEON_USERNAME = "username"; //ID
	public static final String NEON_PASSWORD = "password"; //ID
	public static final String NEON_LOGIN_BUTTON = "//button[@type='submit' and text()='LOGIN']";
	public static final String NEON_CROP_IMAGE_CORNER = "//div[@class='ord-se jcrop-handle']";
	public static final String NEON_MENU_DELETE = "//div[@class='portlet-body']//div[4]//div[@class='portlet-title']//div[@class='actions']//button[@has-permission='delete']";
	public static final String NEON_MENU_CONFIRM_DELETE = "//button[@type='button' and contains(text(),'Delete')]";
	public static final String NEON_MENU_ITEM_SELECT = "//div[@name='currentActiveMenuNameParentDiv' and text()='";
	public static final String NEON_MENU_ITEM_SELECT1= "']";
	public static final String NEON_SELECT_BUSINESS="//div[@class='form-group']//div[contains(@id,'select-business')]//a[@class='select2-choice']/span";
	public static final String SELECT_BUSINESS = "//span[@class='select2-match']";
	public static final String NEON_SELECT_BUSINESS_LOCATION="//div[@class='form-group']//div[contains(@id,'select-location')]//a[@class='select2-choice']/span";
	public static final String NEON_SELECT_LIST_BOX = "//span[@class='select2-match']/parent::div[contains(.,'";
	public static final String NEON_SELECT_LIST_BOX1 = "')]";	
	
	
	
	/**
	 * Steris OR
	 */
	public static String CLOSE_TAB = "//html/body/div[5]/div[2]/div[1]/div[1]/div[2]/div/a[";
	public static String CLOSE_TAB1 = "]/span[2]";
	public static String STERIS_LOGO = "//img[@src='resources/images/steris_logo.png']"; //Xpath
	public static String USER_NAME = "userName";	//Name
	public static String PASS_WORD = "password";	//Name
	public static String LOGIN_BUTTON = "//span[text()='Log In']";	//Xpath
	public static String PREFERENCE_BOX = "//span[text()='PREFERENCES']"; //Xpath
	//Imported user details
	public static String USER_USER_NAME = "//td[1]/div[text()='";
	public static String USER_USER_NAME1 = "']";
	public static String USER_FIRST_NAME = "//td[2]/div[text()='";
	public static String USER_FIRST_NAME1 = "']";
	public static String USER_LAST_NAME = "//td[3]/div[text()='";
	public static String USER_LAST_NAME1 = "']";
	public static String USER_PRIMARY_EMAIL = "//td[4]/div[text()='";
	public static String USER_PRIMARY_EMAIL1 = "']";
	public static String USER_ROLE = "//td[7]/div[text()='";
	public static String USER_ROLE1 = "']";
	public static String USER_NOTIFICATION = "//td[13]/div[text()='";
	public static String USER_NOTIFICATION1 = "']";	
	public static String CASE_DETAILS_OR = "//td[text()='OR#']/following-sibling::td[text()='";
	public static String CASE_DETAILS_OR1 = "']";
	public static String CASE_DETAILS_MRN = "//td[text()='Patient MRN']/following-sibling::td[text()='";
	public static String CASE_DETAILS_MRN1 = "']";
	public static String CASE_DETAILS_ASSETS = "//td[text()='Assets']/following-sibling::td[text()='";
	public static String CASE_DETAILS_ASSETS1 = "']";
	
	/**
	 * Resolve OR
	 * 
	 */
	public static String RESOLVE_LOGO = "//div[@class='inner']//a"; //Xpath
	public static String RESOLVE_USER_NAME = "username";	//ID
	public static String RESOLVE_PASS_WORD = "password";	//ID
	public static String RESOLVE_LOGIN_BUTTON = "//input[@value='Login']";	//Xpath
	public static String LOGOUT_BUTTON = "//button[@type='button' and text()='Logout']";
	public static String LOGOUT_INFORMATION = "//span[text()='Are you sure you want to log out?']";
	public static String LOGOUT_YES_BUTTON = "//button[@type='button' and text()='Yes']";
	public static String LOGGED_USER = "//button[contains(text(),'";
	public static String LOGGED_USER1 = "')]";
	
	
	
}
 	