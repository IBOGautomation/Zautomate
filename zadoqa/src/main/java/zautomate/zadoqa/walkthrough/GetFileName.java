package zautomate.zadoqa.walkthrough;

import java.io.File;

import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.utils.Directory;

public class GetFileName 
{
	
	public static void main(String[] args)
	{
		
		
		String n = "3";
		int i=1;
		int j = Integer.parseInt(n);
		int k = 1+j;
		System.out.println("Vaslue : "+k);
		
		
		/*String filepath="C:/workspace/Zautomate/ZA/testcases/UploadFiles/CloserConnectPdfFile.pdf";
		File fileName = new File(filepath);
		String on = fileName.getName().toString();		
		int ins = on.lastIndexOf('.');
		on = on.substring(0, ins);
		
		
		System.out.println("Value : "+on);*/
		
		
		/* String path = "C:/workspace/Zautomate/ZA/testcases/UploadFiles";
	        File folder = new File(path);
	        File[] files = folder.listFiles();
	        String fileName1;
	        int lastPeriodPos;
	        for (int i = 0; i < files.length; i++) {
	            if (files[i].isFile()) {
	                fileName1 = files[i].getName();
	                lastPeriodPos = fileName1.lastIndexOf('.');
	                if (lastPeriodPos > 0)
	                fileName1 = fileName1.substring(0, lastPeriodPos);
	                System.out.println("File name is " + fileName1);
	            }
	        }
		*/
	}

}
