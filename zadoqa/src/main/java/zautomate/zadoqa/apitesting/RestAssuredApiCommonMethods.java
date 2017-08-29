package zautomate.zadoqa.apitesting;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.remote.JsonException;
import org.testng.Assert;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.enums.LogAs;
import zautomate.zadoqa.reports.CaptureScreen;
import zautomate.zadoqa.reports.CaptureScreen.ScreenshotOf;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestAssuredApiCommonMethods
{
	public static String URL = "";
	public static Response Get(String url)
	{
		Response response = given()
				.then()			
				.get(url)				
				.andReturn();
		return response;
	}

	public static ArrayList<String> AddValues = new ArrayList<>();
	public static void Parameter(String KeyValues)
	{		
		AddValues.add(KeyValues);		
	}

	public static String Prameters()
	{
		int size = AddValues.size();
		String Prams = "";
		String currenttime = new SimpleDateFormat("yyddHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = "Alice@crowdtwist.com";		
		String Email_Address = currenttime+originalValue;	
		String User_Name = "AliceTwist"+currenttime;
		for(int i=0;i<size;i++)	
		{
			String one = AddValues.get(i);			
			String[] splitData = one.split(",");
			String key = splitData[0];			
			String values = splitData[1];
			if(key.equalsIgnoreCase("email_address"))
			{
				values = Email_Address;
			}
			if(key.equalsIgnoreCase("username"))
			{
				values = User_Name;
			}
			if(i==0)
			{			
				Prams =  ""+key+","+values+"";			
			}
			else
			{
				Prams =  ","+key+","+values+"";			
			}	
		}
		return Prams;
	}

	public static Response post(String url)
	{
		String Params = Prameters();
		Response response = given().
				param("Accept", "application/json").			
				parameter(Params).
				when().
				request().				
				post(url).
				andReturn();
		return response;
	}

	public static void GET(String Url) throws JSONException 
	{     
	 	Response response = Get(Url);

		int ResponseCode = response.getStatusCode();
		String Code = String.valueOf(ResponseCode);
		String Status = response.getStatusLine();
		Report("GetResponseCode","To verify response code","","Response code should be verified",Code);
		Report("GetResponseStatus","To verify response status","","Response status should be verified",Status);
		String getResponse = response.asString();
		JSONArray jsonResponse = new JSONArray(getResponse);//(response.asString());
		int size = jsonResponse.length();		
		int val = 0;

		String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
		JSONObject jsonResponse1 = new JSONObject(getResponseReplace);
		System.out.println("Size : "+jsonResponse1.length());
		int object = jsonResponse1.length();

		ArrayList<String> FieldName = new ArrayList<>();

		for(int i=0;i<object;i++)
		{
			FieldName.add(jsonResponse1.names().getString(i));
			System.out.println("Content : "+jsonResponse1.names().getString(i));		

		}	
		int ArraySize = FieldName.size();
		System.out.println("Array Size : "+ArraySize);		
		for(int i=0;i<size;i++)
		{
			String GetResponseValue = jsonResponse.getJSONObject(i).toString();
			System.out.println("Value : "+GetResponseValue);
			Report(""+i+" RowResponseData","To verify "+i+" row response data","",""+i+" Row Response data should be verified",GetResponseValue);
			for(int j=0;j<ArraySize;j++)
			{
				try
				{
					String FieldData = FieldName.get(j);
					String FieldValue = jsonResponse.getJSONObject(i).getString(FieldData);
					System.out.println(FieldData+" value is : "+FieldValue);
					Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",FieldValue);
				}
				catch (JsonException e) 
				{
					String FieldData = FieldName.get(j);
					int FieldValue = jsonResponse.getJSONObject(i).getInt(FieldData);
					String FieldValues = String.valueOf(FieldValue);
					System.out.println(FieldData+" value is : "+FieldValue);
					Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",FieldValues);
				} 
				catch (Exception e) 
				{
					String FieldData = FieldName.get(j);
					Object FieldValue = jsonResponse.getJSONObject(i).get(FieldData);
					String Value = FieldValue.toString();
					System.out.println(FieldData+" value is : "+Value);
					Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",Value);
				} 
			}

		}
		String one = jsonResponse.getJSONObject(0).toString();		
		System.out.println("Value 1: "+one);		
		String capital = jsonResponse.getJSONObject(1).getString("title"); 

		//Assert.assertEquals(capital, "Oslo"); 

	}

	public static String EmailAddress = "";
	String parameter = "";
	public static void POST(String Url) throws JSONException, Exception 
	{   
		String currenttime = new SimpleDateFormat("yyddHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = "Alice@crowdtwist.com";		
		String Email_Address = currenttime+originalValue;	
		String User_Name = "AliceTwist"+currenttime;
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
				parameter("third_party_id", "Twist1234"). */	
			//	parameters("email_address",Email_Address,"last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","username", User_Name,"third_party_id", "Twist1234" 	).
				parameters("last_name", "Twist","first_name", "Alice", "postal_code", "10010", "date_of_birth", "344754000", "password", "x8d9sD1N338aA323","store_id", "1024", "sign_up_campaign", "SpringMediaBuy","third_party_id", "Twist1234" 	).

				when().
				request().				
				post(Url).
				andReturn();
		int ResponseCode = response.getStatusCode();	
		String Code = String.valueOf(ResponseCode);
		String Status = response.getStatusLine();
		//System.out.println("code : "+Code);
		//System.out.println("status : "+Status);
		Report("PostResponseCode","To verify Post response code","","Post Response code should be verified",Code);
		Report("PostResponseStatus","To verify Post response status","","Post Response status should be verified",Status);
		String getResponse = response.asString();
		System.out.println("Value : "+getResponse);
		String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");		
		//String getResponseReplace = getResponse.replaceAll("\\[", "").replaceAll("\\]", "");	
		JSONObject jsonResponse1 = new JSONObject(getResponseReplace);
		System.out.println("Size : "+jsonResponse1.length());
		int object = jsonResponse1.length();

		ArrayList<String> FieldName = new ArrayList<>();

		for(int i=0;i<object;i++)
		{
			FieldName.add(jsonResponse1.names().getString(i));
			System.out.println("Content : "+jsonResponse1.names().getString(i));		

		}

		int ArraySize = FieldName.size();
		System.out.println("Array Size : "+ArraySize);

		JSONArray jsonResponse = new JSONArray("["+getResponse+"]");//(response.asString());
		String Email = jsonResponse.getJSONObject(0).getString("email_address");
		
		int size = jsonResponse.length();		
		int val = 0;		
		if(ResponseCode == 200)
		{	

			for(int i=0;i<size;i++)
			{
				String GetResponseValue = jsonResponse.getJSONObject(i).toString();
				System.out.println("Size value : "+GetResponseValue);

				for(int j=0;j<ArraySize;j++)
				{
					try
					{
						String FieldData = FieldName.get(j);
						String FieldValue = jsonResponse.getJSONObject(i).getString(FieldData);
						System.out.println(FieldData+" value is : "+FieldValue);
						if(FieldData.equalsIgnoreCase("email_address"))
						{							
							Assert.assertEquals(FieldValue, Email);							
						}
						Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",FieldValue);
					}
					catch (JsonException e) 
					{
						String FieldData = FieldName.get(j);
						int FieldValue = jsonResponse.getJSONObject(i).getInt(FieldData);
						String FieldValues = String.valueOf(FieldValue);
						System.out.println(FieldData+" value is : "+FieldValue);
						Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",FieldValues);
					} 
					catch (Exception e) 
					{
						String FieldData = FieldName.get(j);
						Object FieldValue = jsonResponse.getJSONObject(i).get(FieldData);
						String Value = FieldValue.toString();
						System.out.println(FieldData+" value is : "+Value);
						Report(FieldData,"To verify "+FieldData+"","",""+FieldData+" should be verified",Value);
					} 					
				}

				//Report("PostResponseData","To verify Post response data","","Post Response data should be verified",GetResponseValue);
			}		
		}
		else
		{
			String error = jsonResponse1.getString("message"); 				 
			Assert.assertEquals(error, "username already taken.");
		}		
	}

	public static void Report(String Action,String Actual,String Input,String Expected,String ReturnValue)
	{
		ZadoReports.add(Action,Actual,Input, Expected,Objects.toString(ReturnValue, ""),LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}

	public static void main(String []args) throws JSONException, Exception
	{
		POST("http://local-api.crowdtwist.com/v2/users?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5");
	}

}
