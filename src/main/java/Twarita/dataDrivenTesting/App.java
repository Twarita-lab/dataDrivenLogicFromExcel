package Twarita.dataDrivenTesting;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {
    public static void main(String[] args) throws Exception {
      
    	//Identify Testcases coloum by scanning the entire 1st row 
    	//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row 
    	//after you grab purchase testcase row = pull all the data of that row and feed into test  
    	//Identify Testcases coloum by scanning the entire 1st row  
    	//row is collection of cells 
    	//once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
    	//after you grab purchase testcase row = pull all the data of that row and feed into test  

        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\DataForDataDrivenTesting.xlsx");
        
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
        		 
    			 if(rowIterator.next().getStringCellValue().equalsIgnoreCase("Purchase")){
    				 for(int i=1; i<coolumnNo; i++) {
    					 System.out.println(rowIterator.next().getStringCellValue()); 
    				 }
    			 }
    		 }
    	 }
     }
        workbook.close();
        
        
        
    }
}
