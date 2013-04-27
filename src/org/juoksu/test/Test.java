package org.juoksu.test;

import static org.junit.Assert.*;

import org.juoksu.data.*;

public class Test {

	@org.junit.Test
	public void testRunnerCreation() {
		Runner test = new Runner(120, "Mikko Mallikas", "Paimion Urheilijat");
		assertEquals("Name", test.getName(), "Mikko Mallikas");		
		assertEquals("Number", test.getNumber(), 120);
		assertEquals("Team", test.getTeam(), "Paimion Urheilijat");
	}
	
	@org.junit.Test
	public void testgetRunner() {
		SerieHandler sh = new SerieHandler();
		sh.addRunner(120, "Mikko Mallikas", "Paimion Urheilijat");
		sh.addSerie("M", 101, 160);
		sh.addSerie("N", 161, 200);
		assertTrue("Juoksija lisätty", sh.isRunnerIn(new Runner(120, "Mikko Mallikas", "Paimion Urheilijat")));
	}
	

}
