package zautomate.zadoqa.apitesting;

import static com.jayway.restassured.RestAssured.given;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;

//import Decoder.BASE64Encoder;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class RestAPI {

	public static void main(String[] args) throws Exception {

		//GetRestApi();
		PostRestApi1();
		//verifyCreatedUser();
		//verifyInvalidUser();
		
		/*HashMap<String, String> hash = new HashMap<>();
		hash.put("email_address", "1628130112220Alice@crowdtwist.com");
		hash.put("last_name", "Twist");
		hash.put("first_name", "Alice");
		hash.put("postal_code", "10010");
		hash.put("date_of_birth", "344754000");
		hash.put("password", "x8d9sD1N338aA323");
		hash.put("store_id", "1024");
		hash.put("sign_up_campaign", "SpringMediaBuy");
		hash.put("username", "AliceTwistUser");
		hash.put("third_party_id", "Twist1234");
		
		System.out.println("Hash : "+hash);*/
 
	}




	public static void GetRestApi() throws Exception {
		Response response = given()
				.param("Accept", "application/json")
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.then()					
				.get("http://local-api.crowdtwist.com/v2/activities")				
				.andReturn();
		int ResponseCode = response.getStatusCode();
		ReportBlue("Get Response Code : "+ResponseCode);		
		String ResponseStatus = response.getStatusLine();
		ReportBlue("Get Response Status : "+ResponseStatus);		

		if(ResponseCode == 200)	{
			String getResponse = response.asString();
			ReportBlue("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String title = jsonResponse.getString("title"); 		
			ReportBlue("Get Response Title : "+title);
			Assert.assertEquals(title, "Ad Hoc Points");
		}
		else{
			String getResponse = response.asString();
			ReportBlue("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String error = jsonResponse.getString("error"); 		
			ReportBlue("Get Response Error : "+error);			 
		}		
	}

	public static ArrayList<String> AddValues = new ArrayList<>();
	public static void Parameter(String KeyValues)
	{		
		AddValues.add(KeyValues);		
	}
	
	public static HashMap<String, String> Parameters() throws UnsupportedEncodingException
	{		
		HashMap<String, String> hash = new HashMap<>();
		hash.put("email_address", "1628130112220Alice@crowdtwist.com");
		hash.put("last_name", "Twist");
		hash.put("first_name", "Alice");
		hash.put("postal_code", "10010");
		hash.put("date_of_birth", "344754000");
		hash.put("password", "x8d9sD1N338aA323");
		hash.put("store_id", "1024");
		hash.put("sign_up_campaign", "SpringMediaBuy");
		hash.put("username", "AliceTwistUser");
		hash.put("third_party_id", "Twist1234");
		
		
		return hash;		
	}


	public static String EmailAddress = "";
	public static void PostRestApi() throws Exception {	 

		String currenttime = new SimpleDateFormat("yyddHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = "Alice@crowdtwist.com";		
		String Email_Address = currenttime+originalValue;	
		String User_Name = "AliceTwist"+currenttime;
		String par = "email_address,1628130ss112ss20Alice@crowdtwist.com,last_name,Twist,first_name,Alice,postal_code,10010,date_of_birth,344754000,password,x8d9sD1N338aA323,store_id,1024,sign_up_campaign,SpringMediaBuy,username,AliceTwisssssst22221628130110,third_party_id,Twist1234";
		Map<String, ?> para = Parameters();
		Response response = given().
				param("Accept", "application/json").				
				/*parameter("email_address", Email_Address). 
				parameter("last_name", "Twist"). 
				parameter("first_name", "Alice"). 
				parameter("postal_code", "10010"). 
				parameter("date_of_birth", "344754000"). 
				parameter("password", "x8d9sD1N338aA323"). 
				parameter("store_id", "1024"). 
				parameter("sign_up_campaign", "SpringMediaBuy"). 
				parameter("username", User_Name). 
				parameter("third_party_id", "Twist1234"). 	*/
				//parameters("email_address",Email_Address,"last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","username", User_Name,"third_party_id", "Twist1234" 	).
				parameters("last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","username", "sam","third_party_id", "Twist1234" 	).
				//parameters(para).				 
				when().
				request().				
				post("http://local-api.crowdtwist.com/v2/users?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
				andReturn();
		int ResponseCode = response.getStatusCode();	
		ReportGreen("Post Response Code : "+ResponseCode);
		String ResponseStatus = response.getStatusLine();
		ReportGreen("Post Response Status : "+ResponseStatus);

		if(ResponseCode == 200)	{
			String getResponse = response.asString();
			ReportGreen("Post Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String email = jsonResponse.getString("email_address");
			EmailAddress = email;
			ReportGreen("Post Response Email : "+email);
			String User = jsonResponse.getString("username"); 		
			ReportGreen("Post Response User : "+User);

			if(email.equalsIgnoreCase(Email_Address)) {
				if(User.equalsIgnoreCase(User_Name)) {
					ReportGreen("Post Response Successfully validated");					
				}
			}			
		}
		else{
			String getResponse = response.asString();
			ReportGreen("Post Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String error = jsonResponse.getString("message"); 	
			ReportGreen("Post Response Error : "+error);	
			//Assert.assertEquals(error, "username already taken.");
		}		
	}
	
	
	public static void PostRestApi1() throws Exception {	 

		String currenttime = new SimpleDateFormat("yyddHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = "Alice@crowdtwist.com";		
		String Email_Address = currenttime+originalValue;	
		String User_Name = "AliceTwist"+currenttime;
		
		  String username = "ZSA";
		     String password = "Pass@123";
		     String enc=username + ":" + password;
		
		String par = "email_address,1628130ss112ss20Alice@crowdtwist.com,last_name,Twist,first_name,Alice,postal_code,10010,date_of_birth,344754000,password,x8d9sD1N338aA323,store_id,1024,sign_up_campaign,SpringMediaBuy,username,AliceTwisssssst22221628130110,third_party_id,Twist1234";
	    String payload="{\"Single_Payment_Corp_Req\":{\"Header\":{\"TranID\":\"12345\",\"Corp_ID\":\"ZSA01\",\"Maker_ID\":\"\",\"Checker_ID\":\"\",\"Approver_ID\":\"\"},\"Body\":{\"Amount\":\"3.00\",\"Debit_Acct_No\":\"1000112010000333\",\"Debit_Acct_Name\":\"ARPIT\",\"Ben_IFSC\":\"UTIB0000404\",\"Ben_Acct_No\":\"913010028000501\",\"Ben_Name\":\"ARPIT\",\"Ben_Address\":\"MUMBAI\",\"Ben_Email\":\"ADAS@YAHOO.COM\",\"Ben_Mobile\":\"9111111111\",\"Mode_of_Pay\":\"NEFT\",\"Remarks\":\"Test1\"},\"Signature\":{\"Signature\":\"Signature\"}}}";
		Map<String, ?> para = Parameters();
		Response response = given().
				contentType(ContentType.JSON).
				//param("Authorization", "Basic " + new BASE64Encoder().encode(enc.getBytes())).	
				//param("Username", "ZSA "). 
			//	param("Password", "Pass@123 ").
				auth().basic("username", "ZSA").
				auth().basic("username", "Pass@123").
				header("Accept", "application/json").	
				header("x-ibm-client-id", "081abbaf-e89f-4986-a7ae-9d998e76e3de").	
				header("x-ibm-client-secret", "sU2kC8aY7dL4aH0yA2wM4tT4vT0tN8xJ3rE0wH2gY5qH6uM8qD").	
				
				//param("Accept", "application/json").	
				/*parameter("email_address", Email_Address). 
				parameter("last_name", "Twist"). 
				parameter("first_name", "Alice"). 
				parameter("postal_code", "10010"). 
				parameter("date_of_birth", "344754000"). 
				parameter("password", "x8d9sD1N338aA323"). 
				parameter("store_id", "1024"). 
				parameter("sign_up_campaign", "SpringMediaBuy"). 
				parameter("username", User_Name). 
				parameter("third_party_id", "Twist1234"). 	*/
				//parameters("email_address",Email_Address,"last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","username", User_Name,"third_party_id", "Twist1234" 	).
				queryParam(payload).
				//parameters("last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","username", "sam","third_party_id", "Twist1234" 	).
				//parameters(para).				 
				when().
				request().				
				post("https://apideveloper.rblbank.com/test/sb/rbl/v1/payments/corp/payment").
				andReturn();
		int ResponseCode = response.getStatusCode();	
		ReportGreen("Post Response Code : "+ResponseCode);
		String ResponseStatus = response.getStatusLine();
		ReportGreen("Post Response Status : "+ResponseStatus);
		String ResponseStatus1 = response.toString();
		ReportGreen("Post Response Status : "+ResponseStatus1);

		if(ResponseCode == 200)	{
			String getResponse = response.asString();
			ReportGreen("Post Response Entire Info : "+getResponse);
			//String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponse);
			String email = jsonResponse.getString("email_address");
			EmailAddress = email;
			ReportGreen("Post Response Email : "+email);
			String User = jsonResponse.getString("username"); 		
			ReportGreen("Post Response User : "+User);

			if(email.equalsIgnoreCase(Email_Address)) {
				if(User.equalsIgnoreCase(User_Name)) {
					ReportGreen("Post Response Successfully validated");					
				}
			}			
		}
		else{
			
			 
		     
			String getResponse = response.asString();
			ReportGreen("Post Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String error = jsonResponse.getString("message"); 	
			ReportGreen("Post Response Error : "+error);	
			//Assert.assertEquals(error, "username already taken.");
		}		
	}
	public static void verifyCreatedUser() {

		ReportYellow("Created Email Address : "+EmailAddress);
		Response response = given()
				.param("Accept", "application/json")				
				//.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.param("id_type", "email")
				.then()					
				.get("http://local-api.crowdtwist.com/v2/users/"+EmailAddress+"?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")				
				.andReturn();
		int ResponseCode = response.getStatusCode();
		ReportPurple("Get Response Code : "+ResponseCode);
		String ResponseStatus = response.getStatusLine();
		ReportPurple("Get Response Status : "+ResponseStatus);	

		if(ResponseCode == 200)	{
			String getResponse = response.asString();
			ReportPurple("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String Email = jsonResponse.getString("email_address"); 		
			ReportPurple("Get Response Title : "+Email);
			Assert.assertEquals(Email, EmailAddress);
		}
		else{
			String getResponse = response.asString();
			ReportPurple("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String error = jsonResponse.getString("message"); 		
			ReportPurple("Get Response Error : "+error);			
		}		
	}

	public static void verifyInvalidUser() {

		Response response = given()
				.param("Accept", "application/json")				
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.param("id_type", "email")
				.then()					
				.get("http://local-api.crowdtwist.com/v2/users/"+"Sample@Twist.com"+"")				
				.andReturn();
		int ResponseCode = response.getStatusCode();
		ReportRed("Get Response Code : "+ResponseCode);
		String ResponseStatus = response.getStatusLine();
		ReportRed("Get Response Status : "+ResponseStatus);	

		if(ResponseCode == 200)	{
			String getResponse = response.asString();
			ReportRed("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String Email = jsonResponse.getString("email_address"); 		
			ReportRed("Get Response Title : "+Email);
			Assert.assertEquals(Email, "Sample@Twist.com");
		}
		else{
			String getResponse = response.asString();
		 	
			ReportRed("Get Response Entire Info : "+getResponse);
			String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
			JSONObject jsonResponse = new JSONObject(getResponseReplace);
			String error = jsonResponse.getString("message"); 		
			ReportRed("Get Response Error : "+error);		
			Assert.assertEquals(error, "User does not exist.");
		}		
	}




	public static void ReportBlue(String Info)
	{
		System.out.println(ANSI_BLUE +Info+ANSI_RESET);
	}
	public static void ReportGreen(String Info)
	{
		System.out.println(ANSI_GREEN +Info+ANSI_RESET);
	}
	public static void ReportPurple(String Info)
	{
		System.out.println(ANSI_PURPLE +Info+ANSI_RESET);
	}
	public static void ReportRed(String Info)
	{
		System.out.println(ANSI_RED +Info+ANSI_RESET);
	}
	public static void ReportYellow(String Info)
	{
		System.out.println(ANSI_CYAN +Info+ANSI_RESET);
	}

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";



}
