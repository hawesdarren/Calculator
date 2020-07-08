package TestCases;

import javax.net.ssl.HttpsURLConnection;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Actions.CreateJsonString;
import Actions.HttpPost;
import JsonObjects.CalculatorObject;
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
	public void CalculatorMalformedJson() throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = 10;
		calc.RightNumber = 30;
		calc.Operator = "-";
				
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		//Remove 1st comma
		calcJson = calcJson.replaceFirst(",", "");
		
		//Make Post call with Json string
		HttpsURLConnection con = HttpPost.sendPost(calcJson);
		//Get Response code
		Integer intResCode = HttpPost.GetResponseCode(con);
		//Verify Response code not 200
		Assert.assertFalse(intResCode.equals(200), "Response code was 200, should have been a failure code");
		//Verify Response code not 500
		Assert.assertTrue(intResCode.equals(500), "Response code was not 500 - Response code was: " + intResCode );
		
	}
	
	@Test (priority=1000)
	public void CalculatorInvalidAuth() throws Exception {
		
		//Create Calculator object
		CalculatorObject calc = new CalculatorObject();
		calc.LeftNumber = 20;
		calc.RightNumber = 40;
		calc.Operator = "+";
		
		//Create Json string
		String calcJson = CreateJsonString.CreateCalculatorJson(calc);
		
		//Store valid auth key
		String validAuthKey = Settings.authKey;
		
		try {
			
			//Make Auth Key invalid
			String inavlidAuthKey  = Settings.authKey;
			inavlidAuthKey = inavlidAuthKey.substring(0, 20);
			Settings.authKey = inavlidAuthKey;
			
			//Make Post call with Json string
			HttpsURLConnection con = HttpPost.sendPost(calcJson);
			//Get Response code
			Integer intResCode = HttpPost.GetResponseCode(con);
			//Verify Response code not 200
			Assert.assertFalse(intResCode.equals(200), "Response code was 200, should have been 401");
			//Verify Response code = 401
			Assert.assertTrue(intResCode.equals(401), "Response code was not 401 - Reponse code was actually: " + intResCode);
		}
		
		finally{
			//Restore valid auth key
			Settings.authKey = validAuthKey;
		}
		
		
		
		
	}
}
