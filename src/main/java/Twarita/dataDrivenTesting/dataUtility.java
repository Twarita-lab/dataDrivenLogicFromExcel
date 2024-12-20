package Twarita.dataDrivenTesting;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataUtility {
    
      
    	//Identify Testcases coloum by scanning the entire 1st row 
    	//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row 
    	//after you grab purchase testcase row = pull all the data of that row and feed into test  
    	//Identify Testcases coloum by scanning the entire 1st row  
    	//row is collection of cells 
    	//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
    	//after you grab purchase testcase row = pull all the data of that row and feed into test  

    	public ArrayList<String> getData(String filePath, String testCaseName) throws Exception{
    	ArrayList<String> a = new ArrayList<>();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\"+filePath);
        
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
     int numberOfSheets =    workbook.getNumberOfSheets();
     for(int sheet=0; sheet<numberOfSheets; sheet++) {
    	 if(workbook.getSheetName(sheet).equalsIgnoreCase("data")) {
    		 XSSFSheet sheetR = workbook.getSheet("data");
    		 Iterator<Row> itR = sheetR.iterator();
    		 Row firstRow = itR.next(); 
    		 Iterator<Cell> cITR = firstRow.cellIterator();
    		 
    		int coolumnNo=0;
    		int  K=0;
    		 while(cITR.hasNext()) {
    			 if(cITR.next().getStringCellValue().equalsIgnoreCase("end")) {
    				 coolumnNo=K;
    			 }
    			 K++;
    		 }
    		 System.out.println(coolumnNo);
    		 
    		
    		 while(itR.hasNext()) {
    			 Row row = itR.next();
    			 Iterator<Cell> rowIterator = row.cellIterator();
        		 
    			 if(rowIterator.next().getStringCellValue().equalsIgnoreCase(testCaseName)){
    				 for(int i=1; i<coolumnNo; i++) {
    					 Cell cell = rowIterator.next();
    					 if(cell.getCellType()==CellType.STRING)
    						 	a.add(cell.getStringCellValue()); 
    				 
    				 else 
    					 a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
    				 
    			 }
    		 }
    	 }
     }
        workbook.close();
        
        
        
        
    
    	
}
     return a;
    	}
}

