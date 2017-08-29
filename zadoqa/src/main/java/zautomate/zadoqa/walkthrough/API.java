package zautomate.zadoqa.walkthrough;


//import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.*;
//import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.*;


public class API 
{
	static String baseURL = "https://maps.googleapis.com";
	static String path = "/maps/api/geocode/json";	

	public static void main(String[] args) throws Exception
	{

		String url ="http://local-api.crowdtwist.com/v2/activities";//?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5";
		int statuscode = given()
				.param("Accept", "application/json")
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.get(url)
				.statusCode();
		System.out.println("Code : "+statuscode);

		String Message = given()
				.param("Accept", "application/json")
				.param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5")
				.get(url)
				.thenReturn()
				.getStatusLine();
		System.out.println("Line : "+Message);

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
 
		int status = given().
		param("Accept", "application/json").
		param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
		queryParam("email_address", "alice1Twsistas@crowdtwist.com"). 
		queryParam("last_name", "Twist"). 
		queryParam("first_name", "Alice"). 
		queryParam("postal_code", "10010"). 
		queryParam("date_of_birth", "344754000"). 
		queryParam("password", "x8d9sD1N338aA323"). 
		queryParam("store_id", "1024"). 
		queryParam("sign_up_campaign", "SpringMediaBuy"). 
		queryParam("username", "AliceWel1comeas"). 
		queryParam("third_party_id", "Twist1234"). 	
		//body(containsString("OK")).
		when().
		request().
		post("http://local-api.crowdtwist.com/v2/users").
		statusCode();
		System.out.println("code : "+status);
		
		InputStream response3 = given().
				param("Accept", "application/json").
				//param("api_key", "CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
				queryParam("email_address", "alice1Twsist1as@crowdtwist.com"). 
				queryParam("last_name", "Twist"). 
				queryParam("first_name", "Alice"). 
				queryParam("postal_code", "10010"). 
				queryParam("date_of_birth", "344754000"). 
				queryParam("password", "x8d9sD1N338aA323"). 
				queryParam("store_id", "1024"). 
				queryParam("sign_up_campaign", "SpringMediaBuy"). 
				queryParam("username", "AliceWel1come1as"). 
				queryParam("third_party_id", "Twist1234"). 	
				//body(containsString("OK")).
				when().
				request().
				post("https://api.crowdtwist.com/v2/users?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5").
				asInputStream();
		System.out.println("Line Status : "+response3);
		BufferedReader rd3 = new BufferedReader(new InputStreamReader(response3));
		String line3;
		String res3 = "";
		StringBuffer response13 = new StringBuffer(); 
		while((line3 = rd3.readLine()) != null) {
			res3+=line3;
			response13.append(line3);
			response13.append('\r');
		}
		System.out.println("val 3 : "+res3);
			 
	}

}
