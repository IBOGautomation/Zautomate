package zautomate.zadoqa.walkthrough;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class FileProp
{
	
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.setProperty("favoriteAnimal", "marmot");
			properties.setProperty("favoriteContinent", "Antarctica");
			properties.setProperty("favoritePerson", "Nicole");

			File file = new File("D:\\test2.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Favorite Things");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String currenttime = new SimpleDateFormat("ddHHmmss").format(Calendar.getInstance().getTime());
		 
		String combinedValues = "First"+currenttime;
		
		System.out.println("ok : "+combinedValues);
		

	}

}
