package com.tp.surveysystem.surveysystemdemo.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class SurveyDataStub {

	public String getQuestion1Json() {
		Gson gson = new Gson();
		
		HashMap<String, Object> expectedValues = new HashMap<String, Object>();
		expectedValues.put("id", "Question1");
		expectedValues.put("description", "Largest Country in the World");
		expectedValues.put("correctAnswer", "Russia");
		expectedValues.put("options", new String[] {"India", "Russia", "United States", "China"});
	    
		return gson.toJson(expectedValues);
	}
	
	public List<String> getAllData() {
				
		Gson gson = new Gson();
		List<String> jsonList = new ArrayList<String>();
		
		HashMap<String, Object> expectedValues = new HashMap<String, Object>();
		expectedValues.put("id", "Question1");
		expectedValues.put("description", "Largest Country in the World");
		expectedValues.put("correctAnswer", "Russia");
		expectedValues.put("options", new String[] {"India", "Russia", "United States", "China"});
		jsonList.add(gson.toJson(expectedValues));
		
		expectedValues = new HashMap<String, Object>();
		expectedValues.put("id", "Question2");
		expectedValues.put("description", "Most Populus Country in the World");
		expectedValues.put("correctAnswer", "China");
		expectedValues.put("options", new String[] {"India", "Russia", "United States", "China"});
		jsonList.add(gson.toJson(expectedValues));
		
		expectedValues = new HashMap<String, Object>();
		expectedValues.put("id", "Question3");
		expectedValues.put("description", "Highest GDP in the World");
		expectedValues.put("correctAnswer", "United States");
		expectedValues.put("options", new String[] {"India", "Russia", "United States", "China"});
		jsonList.add(gson.toJson(expectedValues));
		
		
		expectedValues = new HashMap<String, Object>();
		expectedValues.put("id", "Question4");
		expectedValues.put("description", "Second largest english speaking country");
		expectedValues.put("correctAnswer", "India");
		expectedValues.put("options", new String[] {"India", "Russia", "United States","China"});
		jsonList.add(gson.toJson(expectedValues));
		
		return jsonList;
	}
}
