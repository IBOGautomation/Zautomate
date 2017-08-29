package zautomate.zadoqa.walkthrough;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFile
{

	public static void main(String[] s) throws IOException 
	{ 
		String[] widgetUrlCount = {"RCU2016/05/0084","Sample1993","RCU Admin test"};
		
		File f = new File("D://Validate//RPT02.PDF");
		FileInputStream fis = new FileInputStream(f); 
		PDFParser pdfParser = new PDFParser(fis);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser.getPDDocument());
		pdfParser.getPDDocument().close();
		System.out.println("printing PDF content fully :" + pdfContent);
		
		
		for (int i = 1; i < widgetUrlCount.length; i++) 
		{
			if (pdfContent.contains(widgetUrlCount[i]))
			{
				System.out.println((widgetUrlCount[i] + " is present in the PDF file"));

			} else 
			{
				System.out.println((widgetUrlCount[i] + " is not present in the PDF file"));

			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		/*// setType("PDF"); 
		Reader reader = null; 
		PDDocument pdfDocument = null; 
		FileInputStream fis = null; 
		String contents = null; 
		File f = null;
		try { 
			f = new File("D://Validate//RPT02.PDF"); // some pdf file example 
			//			String str = box.getContent(f); 
			//			System.out.println("PDF Contents: " + str); 
			//			
			System.out.println("Getting contents from PDF: " + f.getName()); 
			FileInputStream fis = new FileInputStream(f); 
			PDFParser parser = new PDFParser(fis); 
			parser.parse(); 
			PDDocument pdfDocument = parser.getPDDocument(); 
			PDFTextStripper stripper = new PDFTextStripper(); 
			contents = stripper.getText(pdfDocument); 
			Reader reader = new StringReader(contents); 

			String pdfContent = new PDFTextStripper().getText(parser.getPDDocument());
			parser.getPDDocument().close();
			System.out.println("printing PDF content fully :" + pdfContent);
		} 
		catch (IOException e) { 
			System.out.println("Error: Can't open file: " + f.getName()); 
		} 
		} 


	//PDFile box = new PDFile(); 
	//String str = box.getContent(f); 


*/
}
