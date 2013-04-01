package org.juoksu.data;

import java.util.ArrayList;
import java.util.List;

public class Serie {

		private String name;
		private int low, high;
		private ArrayList<Runner> runners;
		private ArrayList<Integer> exceptions;
		
		public Serie(String n, int l, int h) {
			this.name = n;
			this.low = l;
			this.high = h;
			runners = new ArrayList<Runner>();
			exceptions = new ArrayList<Integer>();
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
			return n >= low && n <= high;
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
		return "<table id=\""+name+" sortable\">";
	}

	public String HTMLcaption() {
		return "<caption>" + name + "</caption>";
	}
}