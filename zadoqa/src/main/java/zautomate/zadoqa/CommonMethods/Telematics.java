package zautomate.zadoqa.CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import zautomate.zadoqa.commands.Manipulation;
import zautomate.zadoqa.commands.OR;

public class Telematics extends Manipulation implements OR
{	
	public static File Path = null;
	public static File download() throws Exception
	{
		String down = "C:/Users/Ibog Gobi/Downloads/";
		File dir = new File(down);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {      
			System.out.println("File : "+files);      
		}
		File lastModifiedFile = files[0];
		File First = null ;
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];            
				First = lastModifiedFile;			
			}
		} 		     
		File downloaded = First;     
		String filename = downloaded.getName();		    
		String extension = FilenameUtils.getExtension(filename);		  
		File NewFolder = new File("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/"+filename+""); 
		FileUtils.copyFile(First, NewFolder);		
		Path = NewFolder;		
		return NewFolder;

	}
	
	public static String verifydownloadedPdfFile(WebDriver driver) throws IOException 
	{
		String Results="";
		File file = Path;
		FileInputStream inputPdfFile = new FileInputStream(file); 
		PDFParser pdfParser = new PDFParser(inputPdfFile);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser.getPDDocument());
		pdfParser.getPDDocument().close();		
		for (int i = 0; i < widgetUrls; i++) {		
			if (pdfContent.contains(ApplicationValue[i])) {					
				Results = ApplicationValue[i] + "  is present in the PDF file";
			} else {			
				Results = ApplicationValue[i] + "  is not present in the PDF file";
			}
		}
		return Results;
	}

	public static String verifydownloadedWordFile(WebDriver driver,String inputData) throws Exception 
	{
		String Results=""; 
		File NewFolder = Path;	
		FileInputStream fis=new FileInputStream(NewFolder);
		XWPFDocument docx=new XWPFDocument(fis);
		List<XWPFParagraph> paragraphlist=docx.getParagraphs();  
		for(XWPFParagraph paragraph:paragraphlist) { 
			if (paragraph.getText().contains(inputData)) {				
				Results = inputData + "  is present in the word file";
			}  
			else {
				Results = inputData + "  is present in the word file";
			}
		}		
		return Results;

	}

	public static void DownloadFile() throws Exception
	{
		try	{
			Screen screen = new Screen();		
			try	{			 
				Pattern Save = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/Save.png");
				screen.wait(Save, 1);
				screen.click(Save);
			} catch(Exception e)	{
				Pattern SaveAs = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/SaveAs.png");
				screen.wait(SaveAs, 1);
				screen.click(SaveAs);
			}
			Pattern Ok = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/OK.png");
			screen.wait(Ok, 1);
			screen.click(Ok);
		}
		catch(Exception e) {			
		}

	}
	
	public static void Cancel() throws Exception
	{
		try	{
			Screen screen = new Screen();		
			try	{			 
				Pattern Save = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/Cancel.png");
				screen.wait(Save, 1);
				screen.click(Save);
			} catch(Exception e)	{
				Pattern SaveAs = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/Cancel.png");
				screen.wait(SaveAs, 1);
				screen.click(SaveAs);
			}
			/*Pattern Ok = new Pattern("C:/workspace/Zautomate/ZA/testcases/UploadFiles/Download/OK.png");
			screen.wait(Ok, 1);
			screen.click(Ok);*/
		}
		catch(Exception e) {			
		}

	}

}
