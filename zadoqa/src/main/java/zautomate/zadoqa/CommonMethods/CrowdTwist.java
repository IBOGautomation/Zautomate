package zautomate.zadoqa.CommonMethods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONObject;

public class CrowdTwist 
{


	public static void GetMethod()throws IOException
	{
		String response1="";
		String apiKey = "";
		URL url = new URL("https://api.crowdtwist.com/v2/activities?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");	
		int code = conn.getResponseCode();
		System.out.println("Response : "+conn.getResponseCode());
		System.out.println("Response Info : "+conn.getResponseMessage());
		//String response1="";   
		if(code == HttpURLConnection.HTTP_OK)
		{
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
				response1+=line;
				response.append(line);
				response.append('\r');
			}
			rd.close();
			System.out.println("Post Response : "+response.toString());
		}
		else
		{
			InputStream errorstream = conn.getErrorStream();	        
			String line;
			BufferedReader br=new BufferedReader(new InputStreamReader(errorstream));
			while ((line=br.readLine()) != null) {
				response1+=line;
			}
			System.out.println("Response Info : "+response1);		       
		}
		/////////////////
		String replacedStr = response1.replaceAll("\\[", "").replaceAll("\\]", "");		
		JSONObject obj = new JSONObject(replacedStr);
		String responseCode = obj.getString("title");	
		String responseCodeErr = obj.getString("id");
		System.out.println("status : " + responseCode);
		System.out.println("status err : " + responseCodeErr);
		System.out.println("status Code : " + conn.getResponseCode());
		int code1 = conn.getResponseCode();
		if(conn.getResponseMessage().equalsIgnoreCase("OK"))
		{
			if(code1 == HttpURLConnection.HTTP_OK)
			{
				System.out.println("status : " + conn.getRequestMethod());
				System.out.println("status : " + obj.getString("id"));
			}
		}
	}

	public static void postMethod()throws IOException
	{
		String data = URLEncoder.encode("email_address", "UTF-8") + "=" + URLEncoder.encode("alice1Twsist@crowdtwist.com", "UTF-8");
		data += "&" + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode("Twist", "UTF-8");
		data += "&" + URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode("Alice", "UTF-8");
		data += "&" + URLEncoder.encode("postal_code", "UTF-8") + "=" + URLEncoder.encode("10010", "UTF-8");
		data += "&" + URLEncoder.encode("date_of_birth", "UTF-8") + "=" + URLEncoder.encode("344754000", "UTF-8");
		data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("x8d9sD1N338aA323", "UTF-8");
		data += "&" + URLEncoder.encode("store_id", "UTF-8") + "=" + URLEncoder.encode("1024", "UTF-8");
		data += "&" + URLEncoder.encode("sign_up_campaign", "UTF-8") + "=" + URLEncoder.encode("SpringMediaBuy", "UTF-8");
		data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("AliceWel1come", "UTF-8");
		data += "&" + URLEncoder.encode("third_party_id", "UTF-8") + "=" + URLEncoder.encode("Twist1234", "UTF-8");
		//Create connection
		URL url = new URL("https://api.crowdtwist.com/v2/users?api_key=CAPl3y7r0s5ukCXz5lCJOCrTZ427pjp5");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");	
		conn.setDoInput(true);
		conn.setDoOutput(true);	
		//Send request
		OutputStream os = conn.getOutputStream();	
		DataOutputStream wr = new DataOutputStream (conn.getOutputStream ());
		wr.writeBytes (data);
		wr.flush ();
		wr.close ();
		os.close();
		conn.connect();
		int code = conn.getResponseCode();
		System.out.println("Response : "+conn.getResponseCode());
		System.out.println("Response Info : "+conn.getResponseMessage());
		//Get Response	
		String response1="";   
		if(code == HttpURLConnection.HTTP_OK)
		{
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			System.out.println("Post Response : "+response.toString());
		}
		else
		{
			InputStream errorstream = conn.getErrorStream();	        
			String line;
			BufferedReader br=new BufferedReader(new InputStreamReader(errorstream));
			while ((line=br.readLine()) != null) {
				response1+=line;
			}
			System.out.println("Response Info : "+response1);		       
		}
		/////////////////
		String replacedStr = response1.replaceAll("\\[", "").replaceAll("\\]", "");		
		JSONObject obj = new JSONObject(replacedStr);
		String responseCode = obj.getString("error");	
		String responseCodeErr = obj.getString("message");
		System.out.println("status : " + responseCode);
		System.out.println("status err : " + responseCodeErr);
		System.out.println("status Code : " + conn.getResponseCode());
		int code1 = conn.getResponseCode();
		if(conn.getResponseMessage().equalsIgnoreCase("OK"))
		{
			if(code1 == HttpURLConnection.HTTP_OK)
			{
				System.out.println("status : " + conn.getRequestMethod());
				System.out.println("status : " + obj.getString("id"));
			}
		}


	}



	public static void main(String []args) throws IOException
	{

		GetMethod();
		postMethod();

	}



}
