package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import Actions.CalculatorActions;
import Tools.Browser;
import Tools.Settings;

public class Calculator_UI {

	private WebDriver driver;
	
	
	@BeforeClass
	public void BeforeClass() {
		
		//Start Browser
		driver = Browser.StartBrowser(driver);
		//Nav to site
		driver.navigate().to(Settings.calculatorUL_URL);
		//driver.get(Settings.calculatorUL_URL);
		
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	@DataProvider(name="AddtionDataSet")
		public Object[][] createData1() {
			 return new Object[][] {
				 //Left num, 	Right num, 	Result
				 { "2", 		"4", 		"6" },
				 { "999", 		"999", 		"1998" },
				 { "1000", 		"1001", 	"2001" },
				 { "1e2", 		"3", 		"103" },
				 { "2", 		"3e2", 		"302" },
				 { "1.3", 		"3", 		"4.3" },
				 { "8", 		"6.5", 		"14.5"},
				 { "0", 		"5", 		"5" },
				 { "-8", 		"5", 		"-3" },
				 { "8", 		"-12", 		"-4" },
				 { "2147483647","1", 		"2147483648" },
		
			 };
		
	}
	
	@Test(dataProvider = "AddtionDataSet")
	public void Calculator_Addition(String strLeftNumber, String strRightNumber, String Result) {
		
		CalculatorActions.EnterData(driver, strLeftNumber, strRightNumber, "+");
		CalculatorActions.SelectCalculate(driver);
		CalculatorActions.VerifyResults(driver, strLeftNumber, strRightNumber, Result);
	}
	
	@DataProvider(name="SubtractionDataSet")
	public Object[][] createData2() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { "2", 		"4", 		"-2" },
			 { "999", 		"999", 		"0" },
			 { "1000", 		"999", 		"1" },
			 { "1e2", 		"3", 		"97" },
			 { "2", 		"3e2", 		"-298" },
			 { "1.3", 		"3", 		"-1.7" },
			 { "8", 		"6.5", 		"1.5"},
			 { "0", 		"5", 		"-5" },
			 { "-8", 		"5", 		"-13" },
			 { "8", 		"-12", 		"20" },
		 };
	
}
	
	@Test(dataProvider = "SubtractionDataSet")
	public void Calculator_Subraction(String strLeftNumber, String strRightNumber, String Result) {
		
		CalculatorActions.EnterData(driver, strLeftNumber, strRightNumber, "-");
		CalculatorActions.SelectCalculate(driver);
		CalculatorActions.VerifyResults(driver, strLeftNumber, strRightNumber, Result);
	}
	
	
	@DataProvider(name="MultiplicationDataSet")
	public Object[][] createData3() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { "2", 		"4", 		"8" },
			 { "999", 		"999", 		"998001" },
			 { "100", 		"0", 		"0" },
			 { "1e2", 		"3", 		"300" },
			 { "2", 		"3e2", 		"600" },
			 { "1.3", 		"3", 		"3.9" },
			 { "8", 		"6.5", 		"52"},
			 { "3", 		"-5", 		"-15" },
			 { "-8", 		"5", 		"-40" },
			 { "ten", 		"2", 		"error" },
		 };
	
}
	
	@Test(dataProvider = "MultiplicationDataSet")
	public void Calculator_Multiplication(String strLeftNumber, String strRightNumber, String Result) {
		
		CalculatorActions.EnterData(driver, strLeftNumber, strRightNumber, "*");
		CalculatorActions.SelectCalculate(driver);
		CalculatorActions.VerifyResults(driver, strLeftNumber, strRightNumber, Result);
	}
	
	@DataProvider(name="DivisonDataSet")
	public Object[][] createData4() {
		 return new Object[][] {
			 //Left num, 	Right num, 	Result
			 { "2", 		"4", 		"0.5" },
			 { "999", 		"999",	 	"1" },
			 { "100", 		"0", 		"0" },
			 { "1e2", 		"3", 		"33.33" },
			 { "2", 		"3e2", 		"600" },
			 { "20.5", 		"2", 		"10.25" },
			 { "325", 		"6.5", 		"50"},
			 { "30", 		"-5", 		"-6" },
			 { "-40", 		"5", 		"-8" },
			 { "ten", 		"2", 		"error" },
		 };
	
}
	
	@Test(dataProvider = "DivisonDataSet")
	public void Calculator_Division(String strLeftNumber, String strRightNumber, String Result) {
		
		CalculatorActions.EnterData(driver, strLeftNumber, strRightNumber, "*");
		CalculatorActions.SelectCalculate(driver);
		CalculatorActions.VerifyResults(driver, strLeftNumber, strRightNumber, Result);
	}
	
	@Test
	public void Calculator_VerifyUI()
	{
		CalculatorActions.VerifyPageUI(driver);
	}
	
}
