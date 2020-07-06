package Actions;

import com.google.gson.Gson;

import JsonObjects.CalculatorObject;

public class CreateJsonString {

	public static String CreateCalculatorJson(CalculatorObject calc) {
		
		//Create Json String
		Gson gson = new Gson();
		String json = gson.toJson(calc);

		return json;
	}
	
}
