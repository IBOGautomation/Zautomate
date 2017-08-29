package zautomate.zadoqa.walkthrough;

//Use Google's GeoCode Api to Autocomplete a partital address provided 
//Google's Geocode library
//https://developers.google.com/maps/documentation/geocoding/    
//use the JSON output returned by the library to retrieve the information 
//Example 
//http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway
//Input Partial Address
//OutPut List of possible Address Match

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class HelloWorld 
{



	public static void main(String[] args) throws IOException 
	{ 
		 
/*
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		.url("https://api.mobbr.com/api_v1/api/translations")
		.get()
		.addHeader("cache-control", "no-cache")
		.addHeader("postman-token", "fff06d95-d460-b280-063a-2f3f7df29660")
		.build();

		Response response = client.newCall(request).execute();

		System.out.println("HTTP code : "+ response.request());*/
		
		try {

			URL url = new URL("https://api.mobbr.com/api_v1/api/event_types");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		 
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
			}

			Scanner scan = new Scanner(url.openStream());
			String str = new String();
			while (scan.hasNext())
			str += scan.nextLine();
			scan.close();

			System.out.println("str : " + str);

			JSONObject obj = new JSONObject(str);
			String coord_lon = obj.getJSONObject("coord").getString("lon");
			String coord_lat = obj.getJSONObject("coord").getString("lat");
			System.out.println("coord.lon" + coord_lon);
			System.out.println("coord.lat" + coord_lat);

			JSONArray arr = obj.getJSONArray("weather");
			for (int i = 0; i < arr.length(); i++) {
			//System.out.println("coord.lat" + coord_lat);
			String post_id = arr.getJSONObject(i).getString("description");

			}

			conn.disconnect();

			} catch (MalformedURLException e) {

			e.printStackTrace();

			} catch (IOException e) {

			e.printStackTrace();

			}





	}
	
}
