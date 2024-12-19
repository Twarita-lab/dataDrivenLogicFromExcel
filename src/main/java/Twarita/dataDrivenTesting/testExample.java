package Twarita.dataDrivenTesting;

import java.util.ArrayList;

public class testExample {

	public static void main(String[] args) throws Exception {
		String testCaseName = "Login";
		String fileName = "DataForDataDrivenTesting.xlsx";
		dataUtility app = new dataUtility();
		ArrayList<String> data = app.getData(fileName, testCaseName);
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}

	}

}
