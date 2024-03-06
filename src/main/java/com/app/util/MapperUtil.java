package com.app.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class MapperUtil {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public String convertObjectToString(Object clazz) {
		
		try {
			
			
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writer().withDefaultPrettyPrinter();
			
			String json = mapper.writeValueAsString(clazz);
			System.out.println(json);
			
			return mapper.writeValueAsString(clazz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
