package TestCases;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;

import Actions.CreateJsonString;
import Actions.HttpPost;
import JsonObjects.CalculatorObject;
import JsonObjects.CalculatorResponseObject;
import Tools.Settings;

public class CalculatorTestCases {

	
	@DataProvider(name="NullValues")
	public Object[][] createData1() {
		 return new Object[][] {
			 //Left num, 	Right num,	Operator
			 { 2, 			4, 			null },
			 { null, 			4, 			"-" },
			 { 6, 			null, 			"+" },
			
		 };
	
}
	
	@Test (dataProvider="NullValues")
	public void CalculatorNullValues(Integer leftNumber, Integer rightNumber, String operator) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = operator;
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Get Response code
		Integer intResCode = HttpPost.GetResponseCode(con);
		//Verify Response code not 200
		Assert.assertFalse(intResCode.equals(200), "Response code was 200, should have been a failure code");
		
		
	}
	
	@Test
	public void CalculatorInvalidAuth() throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = 20;
		calc.RightNumber = 40;
		calc.Operator = "+";
		
		//Make Auth Key inavlid
		String inavlidAuthKey  = Settings.authKey;
		inavlidAuthKey = inavlidAuthKey.substring(0, 20);
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Get Response code
		Integer intResCode = HttpPost.GetResponseCode(con);
		//Verify Response code not 200
		Assert.assertFalse(intResCode.equals(200), "Response code was 200, should have been a failure code");
		
		
	}
	
		
	@DataProvider(name="AddtionDataSet")
	public Object[][] createData2() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 0, 			4 }, 		//4
			 { 12, 			0 }, 		//12
			 { 999, 		999 }, 		//1998
			 { 1000, 		1001 }, 	//2001
			 { -10, 		-5 }, 		//-15
			 { -8, 			5 }, 		//-3
			 { 8, 			-12 }, 		//-4
			 { 2147483647, 	0 }, 		//2147483647
			 { 2147483647, 	1 }, 		//-2147483647
			 
	
		 };
	
}
	
	@Test(dataProvider="AddtionDataSet")
	public void AddtionTestCases(Integer leftNumber, Integer rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "+";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code = 200
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
		
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Integer result = leftNumber + rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
	}
	
	
	@DataProvider(name="SubtractionDataSet")
	public Object[][] createData3() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 2, 			0 },		//2
			 { 0, 			6 },		//-6
			 { 999, 		999 },		//0
			 { 1000, 		999 },		//1
			 { -7, 			-23 },		//16
			 { -8, 			5 },		//-13
			 { 8, 			-12 },		//4
		 };
	
	}
	
	@Test(dataProvider="SubtractionDataSet")
	public void SubtractionTestCases(Integer leftNumber, Integer rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "-";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code = 200
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Integer result = leftNumber - rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
	
	@DataProvider(name="MultiplicationDataSet")
	public Object[][] createData4() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 2, 			0 },		//0
			 { 1, 			6 },		//6
			 { 0, 			8 },		//0
			 { 999, 		999 },		//998001
			 { 1000, 		9 },		//9000
			 { -7, 			-23 },		//161
			 { -8, 			5 },		//-40
			 { 8, 			-12 },		//-96
		 };
	
	}
	
	@Test(dataProvider="MultiplicationDataSet")
	public void MultiplecationTestCases(Integer leftNumber, Integer rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "*";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code = 200
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Integer result = leftNumber * rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
	
	@DataProvider(name="DivisionDataSet")
	public Object[][] createData5() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { 1, 			1 },		//1
			 { 10, 			2 },		//5
			 { 8, 			0 },		//infinity
			 { 999, 		999 },		//1
			 { 1000, 		9 },		//111.1	//Integers drop decimal places
			 { -700, 		-23 }, 		//30.4
			 { -8, 			5 }, 		//-1.6
			 { 80, 			-12 }, 		//-6.66
		 };
	
	}
	
	@Test(dataProvider="DivisionDataSet")
	public void DivisionTestCases(Integer leftNumber, Integer rightNumber) throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = leftNumber;
		calc.RightNumber = rightNumber;
		calc.Operator = "/";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Verify response code <400
		HttpPost.VerifyResponseCodeLessThan400(con);
		//Get response
		String jsonResponse = HttpPost.GetResponse(con);
				
		//Extract response from Json
		Gson gson = new Gson();
		CalculatorResponseObject response = gson.fromJson(jsonResponse, CalculatorResponseObject.class);
		
		//Calculate the correct response
		Integer result = leftNumber / rightNumber;
		//Evaluate the response
		Assert.assertEquals(result, response.value, "Value from calculator is incorrect");
		
		
		
	}
	
}
