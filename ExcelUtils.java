package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils 
{
	private static XSSFWorkbook workBook;
	private static XSSFSheet workSheet;
	private static XSSFCell cellValue;
	private static XSSFRow rowValue;
	
	//Method to set the excel file
	//parameters ate excel path and sheet number
	
	public static void setExcelFile(String Path, int SheetNo) throws Exception
	{
		try
		{
			//Opening an File			
			FileInputStream fis = new FileInputStream(Path);
			
			//Accessing the File			
			workBook = new XSSFWorkbook(fis);
			workSheet = workBook.getSheetAt(SheetNo);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		    
		}
	}
	
	//Method to get the entire excel cell data
	
	public static String[][] getCellData() throws Exception
	{
		try
		{
		//getting total excel row value
		int rowCount = workSheet.getLastRowNum();
		int columnCount = workSheet.getRow(0).getLastCellNum();
		
		//Initializing two dimensional array to store the excel results
		String excelData[][] = new String[rowCount][2];
				
		for (int i = 1 ; i<=workSheet.getLastRowNum();i++)
		{
			for (int j = 0 ; j<columnCount ; j++)
			{
				rowValue = workSheet.getRow(i);
				cellValue = rowValue.getCell(j);
								
				excelData[i-1][j] = cellValue.getStringCellValue();
		
			}
		}
		//returning the excel data
		return excelData;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	//Method to print value in excel
	public static void setCellData(int RowNum, int ColumnNum, String Result) throws Exception
	{
		try
		{
			rowValue  = workSheet.getRow(RowNum);
			cellValue = rowValue.getCell(ColumnNum);
			if(cellValue == null)
			{
				cellValue = rowValue.createCell(ColumnNum);
				cellValue.setCellValue(Result);
				System.out.println("Excel Printed");				
				fileOutMethod("D:\\Java_Programs\\Eclipse programs\\Wealth_Manager\\src\\testData\\TestData_Login.xlsx");
			}
		}
		catch(Exception e)
		{
		System.out.println("No Null cell is identified");	

		}
				
		}
	
	//Non - return method to write and close the workbook
	
	public static void fileOutMethod(String path) throws Exception
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(path);
			workBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		catch(Exception e)
		{
			System.out.println("e");
		}
	}
		
}
	