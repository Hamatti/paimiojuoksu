package org.juoksu.data;

import java.util.ArrayList;
import java.util.List;

public class Serie {

		private String name, description;
		private int low, high;
		private List<Integer> removed_numbers;
		private ArrayList<Runner> runners;
		private ArrayList<Integer> exceptions;
			
		public Serie(String n, int l, int h, String desc) {
			this.name = n;
			this.low = l;
			this.high = h;
			this.description = desc;
			
			runners = new ArrayList<Runner>();
			exceptions = new ArrayList<Integer>();
			removed_numbers = new ArrayList<Integer>();
		}
		
		public String getName() {
			return name;
		}
		
		public int getLow() {
			return low;
		}
		
		public int getHigh() {
			return high;
		}
		
		public String getDescription() {
			return description;

		}
		
		public void addRunner(Runner r) {
			runners.add(r);
		}
		
		public List<Runner> getRunners() {
			return runners;
		}
		
		public List<Runner> getTopThree() {
			return runners.subList(0, runners.size() >= 3 ? 3 : runners.size());
		}

		public boolean inRange(int n) {
			return (n >= low && n <= high && !removed_numbers.contains(n)) || exceptions.contains(n);
		}
		
		public void addException(int n) {
			exceptions.add(n);
		}
		
		public void removeException(int n) {
			exceptions.remove(n);
		}
		
	@Override
		public String toString() {
			return name + ": " + low + "-" + high;
		}
		
		
		/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + high;
		result = prime * result + low;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((runners == null) ? 0 : runners.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Serie)) {
			return false;
		}
		Serie other = (Serie) obj;
		if (high != other.high) {
			return false;
		}
		if (low != other.low) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (runners == null) {
			if (other.runners != null) {
				return false;
			}
		} else if (!runners.equals(other.runners)) {
			return false;
		}
		return true;
	}

	public boolean contains(Runner r) {
		return runners.contains(r);
	}

	public String HTMLtableheader() {
		return "<table class=\""+name+" sortable\">";
	}

	public String HTMLcaption() {
		return "<caption>" + description + "</caption>";
	}

	public void removeNumber(int number) {
		removed_numbers.add(number);
	}

	public Runner getRunnerByNumber(int n) {
		for(Runner r : runners) {
			if(r.getNumber() == n) {
				return r;
			}
		}
		return null;
	}

	public void removeRunner(Runner r) {
		runners.remove(r);
		
	}
}
