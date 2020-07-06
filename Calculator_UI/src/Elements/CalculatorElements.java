package Elements;

import org.openqa.selenium.By;

public class CalculatorElements {

	public static By header = By.xpath("//h1");
	
	public static By leftNumber = By.id("leftNumber");
	public static By rightNumber = By.id("rightNumber");
	public static By operator = By.id("operator");
	public static By result = By.xpath("//input[@class='result']");
	
	//Button
	public static By caluculateButton = By.id("calculate");
	public static By caluculateButton_iFrame = By.xpath("//iframe[@frameborder='0']");
	
	
}
