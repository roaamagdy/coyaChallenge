package com.coya.challenge.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataReader {

	JSONObject jsonObj;
	JSONParser parser;
	HashMap<String,String> objectData;

	public DataReader()
	{
		try {
			parser = new JSONParser();
			jsonObj =(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/resources/data.json"));
		} catch (IOException | ParseException e) {           
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String,String> getData(String object){
		objectData = new HashMap<String,String>();
		objectData = ((HashMap<String,String>)jsonObj.get(object));
		return objectData;
	}
	
	
}
