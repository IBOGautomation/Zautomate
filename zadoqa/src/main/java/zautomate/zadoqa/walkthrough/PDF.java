package zautomate.zadoqa.walkthrough;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class PDF 
{

	public static void main(String []args) throws IOException
	{
		String[] widgetUrlCount = {"RCU2016/05/0084","Sample1993","RCU Admin test"};
		URL url = new URL("D:/Validate/RPT02.pdf");
		
		File one = new File("D:/Validate/RPT02.pdf");
		//BufferedInputStream inputPdfFile = new BufferedInputStream(url.openStream());
		FileInputStream inputPdfFile=new FileInputStream(one);
		
		
		
		
		//BufferedInputStream inputPdfFile = new BufferedInputStream(url.openStream());
		PDFParser pdfParser = new PDFParser(inputPdfFile);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser.getPDDocument());
		// pdfParser.getPDDocument().close();
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
		
	/*	try{
		    PDDocument document = null; 
		    document = PDDocument.load(new File("D:/Validate/RPT02.pdf"));
		    document.getClass();
		    if( !document.isEncrypted() ){
		        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		        stripper.setSortByPosition( true );
		        PDFTextStripper Tstripper = new PDFTextStripper();
		        String st = Tstripper.getText(document);
		        System.out.println("Text:"+st);
		    }
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		
		*/
		
		
		
		/*String[] widgetUrlCount = {"RCU2016/05/0084","Sample1993","RCU Admin test"};
		URL url = new URL("http://partners.adobe.com/public/developer/en/acrobat/PDFOpenParameters.pdf");
		BufferedInputStream inputPdfFile = new BufferedInputStream(url.openStream());
		PDFParser pdfParser = new PDFParser(inputPdfFile);
		pdfParser.parse();
		String pdfContent = new PDFTextStripper().getText(pdfParser
				.getPDDocument());
		// pdfParser.getPDDocument().close();
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
		}*/
	}

}
