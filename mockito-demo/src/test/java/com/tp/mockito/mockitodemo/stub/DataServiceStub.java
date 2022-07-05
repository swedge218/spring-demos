package com.tp.mockito.mockitodemo.stub;

import com.tp.mockito.mockitodemo.DataService;

public class DataServiceStub implements DataService {
	public int[] retrieveAllData() {
		int[] testNumbers = {1,2,3};
		return testNumbers;
	}
}
