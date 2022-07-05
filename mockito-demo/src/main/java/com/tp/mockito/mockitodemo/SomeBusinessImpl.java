package com.tp.mockito.mockitodemo;


public class SomeBusinessImpl {
	
	DataService dataService;
	
	public SomeBusinessImpl (DataService dataService) {
		this.dataService = dataService;
	}
	
	public int findGreatestNumber() {
		int greatest = Integer.MIN_VALUE;
		int[] numbers = dataService.retrieveAllData();
		
		for(int number: numbers) {
			if(number > greatest)
				greatest = number;
		}
		
		return greatest;
	}
}
