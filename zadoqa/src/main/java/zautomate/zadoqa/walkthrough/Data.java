package zautomate.zadoqa.walkthrough;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Data
{

	public static void main(String []args) throws IOException
	{
		
	/*	File one = new File("D:/123.txt");
		File two = new File("D:/GOBI/Sample.txt");	*/	
		String down = "C:/Users/Praba/Downloads/";
		String same = down+"?attendnow";		
		//FileUtils.moveFile(one, two);
		
		System.out.println(same);	
		
		File dir = new File(down);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	    	
	    	//System.out.println("File : "+files);
	    	
	    }
	    File lastModifiedFile = files[0];
	    File First = null ;
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	           
	           First = lastModifiedFile;
	           
	           
	           
	           System.out.println("File : "+lastModifiedFile);
	       }
	    }	    
	    System.out.println("Total File : "+files.length);
	    System.out.println("Last Modified File : "+First);
	    
	    File downloaded = First;
	    
	    String ext = downloaded.getName();
	    System.out.println("Name : "+ext);
	    
	    String extension = FilenameUtils.getExtension(ext);
	  
	    System.out.println("EXt : "+extension);
	    
	    
	    
	    File NewFolder = new File("D://GOBI//"+ext+"");	
	    FileUtils.copyFile(First, NewFolder);
	    System.out.println("File Copied");
	    
	}
	
	
}
