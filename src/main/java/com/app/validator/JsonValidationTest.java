package com.app.validator;

import com.app.response.Response;

public class JsonValidationTest {
	
	public static void main(String[] args) {
		
		JSONValdiator valdiator = new JSONValdiator();
		Response response = valdiator.jsonValdiator("employee.json", "{\"id\" : 1,"+
				"    \"gender\" : \"Male\","+
				//"	\"firstName\" : \"mahi\"," + 
				"    \"lastName\" : \"Mahendra\"," + 
				"    \"age\" :100," + 
				"\"address\": [\"muthanapalli\"]"+
				"}");
		
		System.out.println(response);
	}
	
	

}
