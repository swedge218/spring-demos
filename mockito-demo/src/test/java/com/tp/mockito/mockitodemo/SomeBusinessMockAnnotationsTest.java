package com.tp.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessMockAnnotationsTest {

	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomeBusinessImpl someBusinessImpl;
	
	
	@Test
	void testFindGreatestOfThreeorMoreNumbers() {
		//DataService dataServiceMock = mock(DataService.class); --> dropped because of @Mock
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3, 60, 234,23,5465,65});
		//SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceMock); --> dropped because of @InjectMocks
		assertEquals(5465, someBusinessImpl.findGreatestNumber());
	}
	
	@Test
	void testFindGreatestWithSingleNumber() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {50});
		assertEquals(50, someBusinessImpl.findGreatestNumber());
	}
	
	@Test
	void testFindGreatestOfEmptyValues() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, someBusinessImpl.findGreatestNumber());
	}

}
