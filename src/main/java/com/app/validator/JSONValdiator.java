package com.app.validator;
import java.io.File;
import java.util.List;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.app.constants.AppConstants;
import com.app.response.Error;
import com.app.response.Response;
import com.google.common.collect.Lists;

public class JSONValdiator {
	
	public  Response jsonValdiator(final String jsonPath, final String jsonData) {
		Response response = new Response();
		jsonValdiator(jsonPath, jsonData, response);
		return response;
	}
	
	public  void jsonValdiator(final String jsonPath, final String jsonData, Response response) {
		response.setStatuscd(AppConstants.ONE);
		try {
			JSONObject jsonSchema = new JSONObject(new JSONTokener(
					this.getClass().getClassLoader().getResourceAsStream(File.separatorChar + "schema"+ File.separatorChar+ jsonPath)));
			JSONObject jsonSubject = new JSONObject(jsonData);
			Schema schema = SchemaLoader.load(jsonSchema);
		    schema.validate(jsonSubject);
		} catch (ValidationException e) {
			String message = e.getMessage();
			if(e.getCausingExceptions() != null && !e.getCausingExceptions().isEmpty()) {
				List<String> expList = Lists.newArrayList();
				for(ValidationException exp : e.getCausingExceptions()) {
					expList.add(exp.getMessage());
				}
				message = expList.toString();
			}
			response.setStatuscd(AppConstants.ZERO);
			response.setError(new Error("JSON validation failed due to - "+message, "ZERO"));
		}
	}
}
