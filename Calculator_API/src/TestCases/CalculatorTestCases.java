package TestCases;

import org.testng.annotations.*;

import Actions.CreateJsonString;
import Actions.HttpPost;
import JsonObjects.CalculatorObject;

public class CalculatorTestCases {

	
	@DataProvider(name="AddtionDataSet")
	public Object[][] createData1() {
		 return new Object[][] {
			 //Left num, Right num, Result
			 { 2, 4, 6 },
			 { 999, 999, 1998 },
			 { 1000, 1001, 2001 },
			 { 1e2, 3, 103 },
			 { 2, 3e2, 302 },
			 { 0, 5, 5 },
			 { -8, 5, -3 },
			 { 8, -12, -4 },
			 { 8.1, 5, 13.1 },
			 { 17, 6.8, 23.8 },
		 };
	
}
	
	@Test(dataProvider="AddtionDataSet")
	public void AddtionTestCases(Integer dblLeftNumber, Integer dblRightNumber, Integer dblResult) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = dblLeftNumber;
		calc.RightNumber = dblRightNumber;
		calc.Operator = "+";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		String response = HttpPost.sendPost(calcJson);
		
		//Evaluate the response
		
		
		
	}
}
