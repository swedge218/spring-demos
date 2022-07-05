package com.tp.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;


class SomeBusinessMockTest {

	@Test
	void testFindGreatestNumber() {
		DataService dataServiceMock = mock(DataService.class);
		
		int[] testNumbers = {1,2,3};
		when(dataServiceMock.retrieveAllData()).thenReturn(testNumbers);
		
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceMock);
		assertEquals(3, someBusinessImpl.findGreatestNumber());
	}

}
