package Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import JsonObjects.CalculatorObject;

public class CreateJsonString {

	public static String CreateCalculatorJson(CalculatorObject calc) {
		
		//Create Json String
		Gson gson = new GsonBuilder().serializeNulls().create();
		String json = gson.toJson(calc);

		return json;
	}
	
}
