package org.juoksu.data;

import java.util.ArrayList;
import java.util.List;

public class SerieHandler {
	
	private List<Serie> series;
	
	
	public SerieHandler() {
		series = new ArrayList<Serie>();
	}
	
	public void addRunner(int number, String name, String team) {
		Serie s = findSerieByRange(number);
		
		if (s != null) {
			s.addRunner(new Runner(number, name, team));
		}
		
	}
	
	public void addRunner(int number, String name, String team, String time) {
		Serie s = findSerieByRange(number);
		if (s != null) {
			s.addRunner(new Runner(number, name, team, time));
		}
		
	}
	
	public Serie findSerieByRange(int number) {
		for (Serie s : series) {
			if(s.inRange(number)){
				return s;
			}
		}
		return null;
	}

	/** Add new serie
	 * 
	 * @param s serie
	 * @.pre s.getLow() - s.getHigh() range NOT IN series 
	 * 
	 */
	public void addSerie(String seriesName, int low, int high, String desc) {
		series.add(new Serie(seriesName, low, high, desc));
	}

	/** Returns all series in list
	 * 
	 * @return List of series
	 */
	public List<Serie> getSeries() {
		return series;
	}

	public boolean isRunnerIn(Runner r) {
		for (Serie s : series) {
			if (s.contains(r)) {
				return true;
			}
		}
		return false;
	}
	
	public Runner findRunnerByNumber(int n) {
		System.out.println("Etsit‰‰n juoksijaa nro " + n);
		List<Runner> runners = null;
		boolean found = false;
	
		for ( Serie s : series) {
			if (s.inRange(n)) {
				System.out.println("Juoksija on sarjassa " + s.getName());
				runners = s.getRunners();
				found = true;
				break;
			}
		}

		if (!found) {
			return null;
		}
		
		for ( Runner r : runners) {
			if(r.getNumber() == n) {
				System.out.println("Juoksijan nimi on " + r.getName());
				return r;
			}
		}
		return null;
	}

	public Serie findSerie(String name) {
		for(Serie s : series) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

}
