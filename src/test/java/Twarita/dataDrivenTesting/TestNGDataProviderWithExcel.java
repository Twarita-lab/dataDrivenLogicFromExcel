 package Twarita.dataDrivenTesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProviderWithExcel {

	DataFormatter dataFormatter = new DataFormatter();
	@Test(dataProvider = "dataProvider")
	public void usingData(String data1, String data2, String data3) {
			System.out.println(data1+data2+data3);
	}
	

	@DataProvider(name ="dataProvider")
	public Object[][] getData() throws Exception{
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/DataForDataDrivenTesting.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheetName = workbook.getSheet("data");
		
		int rowCount = sheetName.getPhysicalNumberOfRows();
		int columnCount = sheetName.getRow(0).getLastCellNum();
		System.out.println(rowCount +" "+columnCount);
		
		Object data[][] = new Object[rowCount-1][columnCount-2];
		for(int i=1; i<rowCount; i++) {
			for(int j=1; j<columnCount-1; j++) {
				
				Cell cell = sheetName.getRow(i).getCell(j);
				data[i-1][j-1]=dataFormatter.formatCellValue(cell);
			}
		}
		
		workbook.close();
		return data;
		
	}
}
