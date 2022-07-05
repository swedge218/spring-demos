package com.tp.mockito.mockitodemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tp.mockito.mockitodemo.stub.DataServiceStub;

class SomeBusinessStubTest {

//	@Test
//	void testFindGreatestNumber() {
//		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(new DataServiceStub());
//		assertEquals(3, someBusinessImpl.findGreatestNumber());
//	}
	
	@Test
	void testFindGreatestNumber() {
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(new DataServiceStub());
		assertEquals(3, someBusinessImpl.findGreatestNumber());
	}

}
