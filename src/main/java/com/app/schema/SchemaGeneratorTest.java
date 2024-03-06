package com.app.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;
 
public class SchemaGeneratorTest {
    private static ObjectMapper mapper = new ObjectMapper();
    public static final String JSON_$SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_$SCHEMA_ELEMENT = "$schema";
    
    static {
        // required for pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    public static void main(String[] args) throws JsonProcessingException {
        
        // get the draft 4 schema builder
        JsonSchemaGenerator v4generator = SchemaGeneratorBuilder.draftV4Schema().build();
        
        // use the schema builder to generate JSON schema from Java class
        JsonNode schemaNode = v4generator.generateSchema(Employee.class);
        //JsonNode schemaNode = v4generator.generateSchema(GenerateToken.class);
        
        // add the $schema node to the schema. By default, JJSchema v0.6 does not add it 
        ((ObjectNode) schemaNode).put(JSON_$SCHEMA_ELEMENT, JSON_$SCHEMA_DRAFT4_VALUE);
        
        // print the generated schema 
        prettyPrintSchema(schemaNode);
    }
    
    private static void prettyPrintSchema(JsonNode schema) throws JsonProcessingException{
        System.out.println(mapper.writeValueAsString(schema));
    }
}