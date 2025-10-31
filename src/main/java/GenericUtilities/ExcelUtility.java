package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtility {
	public Workbook wb;
	
	public String FetchDataFromExcelFile(String sheetname,int rowno,int cellno) throws EncryptedDocumentException, IOException
	{
		FileInputStream efis=new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		 wb = WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetname).getRow(rowno).getCell(cellno).toString();
		return data;
	}
	
	public void WriteBackDataToNewRow(String sheetname,int rowindex,int cellindex,String data) throws Exception
	{
		FileInputStream efis=new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		 wb = WorkbookFactory.create(efis);
		Sheet sh = wb.getSheet(sheetname);
		sh.createRow(rowindex).createCell(cellindex).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		 wb.write(fos);
		
	}
	public void WriteBackDataToExistingRow(String sheetname,int rowindex,int cellindex,String data) throws Exception
	{
		FileInputStream efis=new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		 wb = WorkbookFactory.create(efis);
		Sheet sh = wb.getSheet(sheetname);
		sh.getRow(rowindex).createCell(cellindex).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		 wb.write(fos);
		
	}
	
	
	public void CloseTheExcel() throws Exception
	{
		
		wb.close();
		
		
	}

}
