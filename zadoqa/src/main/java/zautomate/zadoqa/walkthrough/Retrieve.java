package zautomate.zadoqa.walkthrough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Retrieve
{
		public static void data(String Values) throws BiffException, IOException
		{
			String one="";
			 
			Sheet s;
			//new Excell().name();
			FileInputStream fi = new FileInputStream("D:/Validate/TestData.xls");
			Workbook W = Workbook.getWorkbook(fi);
	 
			String input=Values;
			String testcases="Credentials";

			Sheet sht = W.getSheet("Sheet1");
			//Cell cell1=sht.getCell(0, 0);
			int currRow = sht.findCell(input).getRow();
			int currColumn = sht.findCell(input).getColumn();
			String string=sht.findLabelCell(input).getString();
			int particular=sht.findCell(testcases).getRow();
			System.out.println("The input value is  found : "+string);
			
			if(particular==currRow)
			{		    	

				if(string.equalsIgnoreCase(input))
				{
					int row=currRow+1;
					int col=currColumn;
				    one=sht.getCell(col,row).getContents();		    	
					System.out.println(input+" value is :" +one);
				}
			}
			 
			
		}
		
		public static void data1(String Values) throws BiffException, IOException
		{
			String one="";
			 
			Sheet s;
			//new Excell().name();
			FileInputStream fi = new FileInputStream("D:/Validate/TestData.xls");
			Workbook W = Workbook.getWorkbook(fi);
	 
			String input=Values;
			

			Sheet sht = W.getSheet("Sheet1");
			//Cell cell1=sht.getCell(0, 0);
			int currRow = sht.findCell(input).getRow();
		
			int currColumn = sht.findCell(input).getColumn();
		
			
			String string=sht.findLabelCell(input).getString();
			 
			
			 		    	

				if(string.equalsIgnoreCase(input))
				{
					int row=currRow;
					int col=currColumn+1;
				    one=sht.getCell(col,row).getContents();		    	
					System.out.println(input+" value is :" +one);
				}
			 
			 
			
		}
		
		
		
		 
	public static void main(String[] args) throws BiffException, IOException 
	{
		 		
		data("UserName");
		data1("User1");
		data1("Pass1");
	}
		 
		 
		
 

}
