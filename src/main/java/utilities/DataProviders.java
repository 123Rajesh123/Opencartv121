package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	  //DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path=".\\testData\\OpencartLoginData.xlsx";//Taking xl file from testData
		
		ExcelUtility EU=new ExcelUtility(path);//creating an object for xLutility
		
		int totalrows=EU.getRowCount("Sheet1");
		int totalcols=EU.getcellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];//create for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++) { //1  //read the data from xl storing in two dimensional array
			for(int j=0;j<totalcols;j++) { //0 i is rows j is column
				logindata[i-1][j]=EU.getcellData("Sheet1", i, j);//1,0 --[i-1]-->Array index start from zero
			}
		}
		return logindata;//returning two dimensional array
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
