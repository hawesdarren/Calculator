package Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Elements.CalculatorElements;

public class CalculatorActions extends CalculatorElements{
	
	public static void EnterData(WebDriver driver, String strLeftNumber, String strRightNumber, String strOperator) {
		
		//Check page loaded
		Assert.assertEquals(driver.findElement(header).getText(), "Simple Calculator");
		//Clear left and right number from prev entries
		driver.findElement(leftNumber).clear();
		driver.findElement(rightNumber).clear();
		
		//Enter Values
		driver.findElement(leftNumber).sendKeys(strLeftNumber);
		driver.findElement(rightNumber).sendKeys(strRightNumber);
		
		//Set operator
		new Select (driver.findElement(operator)).selectByValue(strOperator);
		
		
		
	}
	
	public static void SelectCalculate(WebDriver driver) {
		
		
		//Switch to iFrame
		try {
			//driver.switchTo().frame(0);
			driver.switchTo().frame(driver.findElement(caluculateButton_iFrame));
			//Check Calculate button is enable 1st
			Assert.assertTrue(driver.findElement(caluculateButton).isEnabled(), "Calculate button is not enabled");
			driver.findElement(caluculateButton).click();
			//Wait for page to be ready
			Assert.assertTrue(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
		
		}
		catch(Exception e) {
			Assert.assertTrue(false, "Something went wrong trying th click the Calculate button");
		}
		finally {
			driver.switchTo().defaultContent();
			
		}
		
	}
	
	
	
	public static void VerifyResults(WebDriver driver, String strLeftNumber, String strRightNumber, String Result) {
				
		//Wait until vlaue is the correct answer - this is to allow for the AJAX call
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.attributeToBe(result, "value", Result));
		
		//Assert result is as Expected
		//Assert.assertEquals(strActualResult, Result, "Result failed to Match expected result");
	}
	
	public static void VerifyPageUI(WebDriver driver) {
		//Header
		Assert.assertTrue(driver.findElement(header).isDisplayed());
		Assert.assertEquals(driver.findElement(header).getText(), "Simple Calculator");
		//Left number
		Assert.assertTrue(driver.findElement(leftNumber).isDisplayed());
		//Right number
		Assert.assertTrue(driver.findElement(rightNumber).isDisplayed());
		//Result
		Assert.assertTrue(driver.findElement(result).isDisplayed());
		//Operator
		VerifyOperatorDropDown(driver);
		//Calculate button
		VerifyCalculatorButton(driver);
	}
	
	private static void VerifyCalculatorButton(WebDriver driver) {
		//Switch to iFrame
		try {
				//driver.switchTo().frame(0);
				driver.switchTo().frame(driver.findElement(caluculateButton_iFrame));
				//Check Calculate button 
				//Calculate button
				Assert.assertTrue(driver.findElement(caluculateButton).isDisplayed());
				Assert.assertEquals(driver.findElement(caluculateButton).getText(), "Calculate");
			}
			catch(Exception e) {
				Assert.assertTrue(false, "Calculate button is either not visable or has the wrong text");
			}
			finally {
				driver.switchTo().defaultContent();
					
			}
	}
	
	private static void VerifyOperatorDropDown(WebDriver driver) {
		Assert.assertTrue(driver.findElement(operator).isDisplayed());
		//Create List of operators
		ArrayList<String> expectedOperatorList = new <String>ArrayList();
		expectedOperatorList.add("+");
		expectedOperatorList.add("-");
		expectedOperatorList.add("/");
		expectedOperatorList.add("*");
		
		//Check elements
		//Loop through options
		for(String operatorValue : expectedOperatorList) {
			try{
				new Select(driver.findElement(operator)).selectByValue(operatorValue);
			}
			catch(Exception e){
				Assert.assertTrue(false, "Couldn't select operator " + operatorValue);
			}
		}
		
		//Perform count of operators
		Select select = new Select (driver.findElement(operator));
		//Get all options
		List<WebElement> allOptions = select.getOptions();
		//Count the options
		Assert.assertEquals(expectedOperatorList.size(), allOptions.size(), "More Element in the Operator dropdown list than expected");
		
	}

}
