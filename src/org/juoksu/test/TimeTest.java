package org.juoksu.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.juoksu.data.Time;

public class TimeTest {
	
	@Test
	public void shouldZeroTimesComparedReturnZero() {
		Time a = new Time(0,0,0);
		Time b = new Time(0,0,0);
		assertEquals(a.compareTo(b), 0);
	}
	
	@Test
	public void shouldZeroTimesComparedReturnZero2() {
		Time a = new Time(0,0,0);
		Time b = new Time(0,0,0);
		assertEquals(b.compareTo(a), 0);
	}
	
	@Test
	public void shouldPositiveVsZeroReturnMinusOne() {
		Time a = new Time(0,0,1);
		Time b = new Time(0,0,0);
		assertEquals(-1, a.compareTo(b));
	}
	
	@Test
	public void shouldZeroVsPositiveReturnOne() {
		Time a = new Time(0,0,0);
		Time b = new Time(0,0,1);
		assertEquals(1, a.compareTo(b));
	}
	
	@Test
	public void shouldLargerSecondsBeOne() {
		Time a = new Time(0,0,2);
		Time b = new Time(0,0,1);
		assertEquals(1, a.compareTo(b));
	}
	
	@Test
	public void shouldSmallerSecondsBeMinusOne() {
		Time a = new Time(0,0,3);
		Time b = new Time(0,0,4);
		assertEquals(-1, a.compareTo(b));
	}
	
	@Test
	public void shouldLargerMinsBeOne() {
		Time a = new Time(0,1,0);
		Time b = new Time(0,0,1);
		assertEquals(1, a.compareTo(b));
	}
	

}
