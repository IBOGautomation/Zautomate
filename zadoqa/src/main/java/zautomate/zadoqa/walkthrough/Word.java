package zautomate.zadoqa.walkthrough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class Word {

	public static void main(String[] args)   {
		try {
			String name = "ABC test";
			FileInputStream fis=new FileInputStream("D://GOBI//WORD.docx");
			XWPFDocument docx=new XWPFDocument(fis);
			List<XWPFParagraph> paragraphlist=docx.getParagraphs();

			
			//File file1 = new File("D://Validate//RPT02.PDF");
			//File file2 = new File("D://Validate//One//RPT02.PDF");
			//FileUtils.getFile("FileA", "FileB");
			//FileUtils.moveFile(file1, file2);
			//FileUtils.
			
			int i = 0;
			for(XWPFParagraph paragraph:paragraphlist)
			{
				
				//System.out.println(paragraph.getText());
				System.out.println();
				if (paragraph.getText().contains(name))
				{
					System.out.println((name+ " is present in word file "));
				} 	
				else
				{
					System.out.println((name+ " is not present in word file "));
				}
				i++;
				
			}
			System.out.println((i+ " count "));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	
}