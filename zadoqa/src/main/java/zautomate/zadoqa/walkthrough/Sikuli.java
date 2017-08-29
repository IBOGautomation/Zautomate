package zautomate.zadoqa.walkthrough;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Sikuli
{
	
	public static void main(String []args) throws Exception
	{
		
		 
		String Path = "";
		Screen screen = new Screen();		
		try
		{
			Pattern Save = new Pattern("C:\\Validate\\One\\Save.png");
			screen.wait(Save, 2000);
			screen.click(Save);
			
		}
		catch(Exception e)
		{
			Pattern SaveAs = new Pattern("C:\\Validate\\One\\SaveAs.png");
			screen.wait(SaveAs, 2000);
			screen.click(SaveAs);
		}
		Pattern Ok = new Pattern("C:\\Validate\\One\\OK.png");
		screen.wait(Ok, 2000);
		screen.click(Ok);
		screen.wait(3000);
	}
	

}
