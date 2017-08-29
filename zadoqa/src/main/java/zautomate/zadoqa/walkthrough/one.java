package zautomate.zadoqa.walkthrough;

import java.io.IOException;

 

public class one
{

	
	public static void main(String[] args)
	{
		String command = "adb shell monkey -p com.loylogic.pointspay -c android.intent.category.LAUNCHER 1";
		System.out.println("kdafdas");
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
