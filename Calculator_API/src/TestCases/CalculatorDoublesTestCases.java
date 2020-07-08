package TestCases;

import javax.net.ssl.HttpsURLConnection;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import Actions.CreateJsonString;
import Actions.HttpPost;
import JsonObjects.CalculatorObject_UsingDoubles;
import JsonObjects.CalculatorResponseObject;

public class CalculatorDoublesTestCases {

	
	@DataProvider(name="AddtionDataSet_Doubles")
	public Object[][] createData_AddDoubles() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 10d, 			10.1 }, 	//20.1
			 { 5.55, 		1.11 }, 	//6.66
			 { 15d, 		30d }, 		//45
			 { -10.9, 		-5.4 }, 	//-16.3
			 { -8.7, 		5d }, 		//-3.7
			 { 8d, 			-12.3 }, 		//-4.3
			
		 };
	
}
	
	@Test(dataProvider="AddtionDataSet_Doubles")
	public void AddtionTestCases_UsingDoubles(Double leftNumber, Double rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject_UsingDoubles calc = new CalculatorObject_UsingDoubles();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "+";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code < 400
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
		
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Double result = leftNumber + rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
	}
	
	@DataProvider(name="SubtractionDataSet_UsingDoubles")
	public Object[][] createData_Sub_UsingDoubles() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 2d, 			0.1 },		//1.9
			 { 0d, 			6.3 },		//-6.3
			 { -7.1, 		-23.2 },		//16.3
			 { -8.2, 		5.3 },		//-13.5
			 { 8d, 			-12.8 },	//20.8
		 };
	
	}
	
	@Test(dataProvider="SubtractionDataSet_UsingDoubles")
	public void SubtractionTestCases_UsingDoubles(Double leftNumber, Double rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject_UsingDoubles calc = new CalculatorObject_UsingDoubles();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "-";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code < 400
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Double result = leftNumber - rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
	
	@DataProvider(name="MultiplicationDataSet_UsingDoubles")
	public Object[][] createData_MiltiUsingDoubles4() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 2d, 			0d },		//0
			 { 1.1, 		6d },		//6.1
			 { 10d, 		8.3 },		//83
			 { 1000.1, 		9.2 },		//9200.92
			 { -7.4, 		-23.4 },	//173.16
			 { 8.22, 		5.44 },		//44.7168
			 
		 };
	
	}
	
	@Test(dataProvider="MultiplicationDataSet")
	public void MultiplecationTestCases_UsingDoubles(Double leftNumber, Double rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject_UsingDoubles calc = new CalculatorObject_UsingDoubles();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "*";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code < 400
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Double result = leftNumber * rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
	
	@DataProvider(name="DivisionDataSet_UsingDoubles")
	public Object[][] createData_Div_UsingDoubes() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 1d, 			0.2 },		//5
			 { 100.5, 		2d },		//50.25
			 { 8.5, 		0d },		//infinity
			 { 999.9, 		999.9 },	//1
			 { 1000d, 		9d },		//111.1111111
			 { -700d, 		-23.2 }, 	//30.1724137931
			 { -8.1, 		5d }, 		//-1.62
			 { 80d, 		-12.5 }, 	//-6.4
		 };
	
	}
	
	@Test(dataProvider="DivisionDataSet_UsingDoubles")
	public void DivisionTestCases_UsingDoubles(Double leftNumber, Double rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject_UsingDoubles calc = new CalculatorObject_UsingDoubles();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "/";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code < 400
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Double result = leftNumber / rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
}
