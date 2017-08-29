package zautomate.zadoqa.walkthrough;


import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

import junit.framework.Assert;

//import org.openqa.selenium.lift.Matchers;



import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.matchesXsd; 
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import zautomate.zadoqa.ZadoReports;
import zautomate.zadoqa.enums.LogAs;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.path.json.JsonPath;

public class RestAssured 
{
	static String baseURL = "https://maps.googleapis.com";
	static String path = "/maps/api/geocode/json";


	public static void GetRestApi() throws Exception
	{
		String url ="http://local-api.crowdtwist.com/v2/activities";		
		InputStream response = given()
				.param("Accept", "application/json")
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.then()
				.get(url)
				.asInputStream();
		System.out.println("Line : "+response);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response));
		String line;
		String res = "";
		StringBuffer response1 = new StringBuffer(); 
		while((line = rd.readLine()) != null) {
			res+=line;
			response1.append(line);
			response1.append('\r');
		}
		System.out.println("val : "+res);
	}


	public static void main(String[] args) throws Exception
	{
		//GetRestApi();
		Response response = given()
				.param("Accept", "application/json")
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.then()					
				.get("http://local-api.crowdtwist.com/v2/activities")	
				
				.andReturn();
		System.out.println("Code : "+response.getStatusCode());		
		System.out.println("Code : "+response.getStatusLine());	


		String response12 = response.asString();
		System.out.println("Full length : "+response12);
		String one = response12.replaceAll("\\[", "").replaceAll("\\]", "");		
		JSONObject jsonResponse = new JSONObject(one);	


		String title = jsonResponse.getString("title"); 		
		System.out.println("Style : "+title);
		
		JSONArray json = new JSONArray(response12); 
		
		 
		
		 //Assert.assertEquals(jsonResponse.getString("description"), "Daily Login");


		Response response3 = given().
				param("Accept", "application/json").
				//param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
				parameter("email_address", "alice1Twsist11qas@crowdtwist.com").
				parameter("email_address", "alice1Twsist1qqas@crowdtwist.com"). 
				parameter("last_name", "Twist"). 
				parameter("first_name", "Alice"). 
				parameter("postal_code", "10010"). 
				parameter("date_of_birth", "344754000"). 
				parameter("password", "x8d9sD1N338aA323"). 
				parameter("store_id", "1024"). 
				parameter("sign_up_campaign", "SpringMediaBuy"). 
				parameter("username", "AliceWel1qq1come1as"). 
				parameter("third_party_id", "Twist1234"). 	
				//body(containsString("OK")).
				//contentType("application/json").
				//body(jsonObj.toString()).
				when().
				request().
				post("http://local-api.crowdtwist.com/v2/users?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
				andReturn();
		System.out.println("Post Code : "+response3.getStatusCode());		
		System.out.println("Post Code : "+response3.getStatusLine());	
		
		String response121 = response3.asString();
	 
		System.out.println("Full length : "+response121);
		String one1 = response121.replaceAll("\\[", "").replaceAll("\\]", "");		
		JSONObject jsonResponse1 = new JSONObject(one1);	
		String title1 = jsonResponse1.getString("message"); 		
		System.out.println("Style : "+title1);

	}
}


