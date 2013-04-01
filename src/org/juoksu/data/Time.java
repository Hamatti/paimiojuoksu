package org.juoksu.data;

public class Time implements Comparable<Time>{
	
	private int hours, mins, secs;
	
	public Time(int hours, int mins, int secs) {
		this.hours = hours;
		this.mins = mins;
		this.secs = secs;
	}
	
	/* Getters and setters */
	
	public int getSecs() {
		return this.secs;
	}

	public int getMins() {
		return this.mins;
	}

	public int getHours() {
		return this.hours;
	}
	
	public void setSecs(int s) {
		this.secs = s;
	}
	
	public void setMins(int m) {
		this.mins = m;
	}
	
	public void setHours(int h) {
		this.hours = h;
	}
	
	/* Other functions */
	
	
	
	/* Overridden functions */
	@Override
	public String toString() {
		String format = "";
		
		if(hours > 0) {
			format += hours + ".";
		}
		
		if (mins < 10 && hours > 0) {
			format += "0" + mins + ",";
		}
		else {
			format += mins + ",";
		}
		
		if (secs < 10) {
			format += "0" + secs;
		}
		else {
			format += secs;
		}

		return format;	
		
	}

	@Override
	public int compareTo(Time o) {
		if (this.getHours() > o.getHours()) {
			return 1;
		}
		else if (this.getHours() == o.getHours()) {
			if (this.getMins() > o.getMins()) {
				return 1;
			}
			else if (this.getMins() == o.getMins()) {
				if (this.getSecs() > o.getSecs()) {
					return 1;
				}
				else if (this.getSecs() == o.getSecs()) {
					return 0;
				}
				else { // this.secs() < o.secs()
					return -1;
				}
			}
			else { // this.mins() < o.mins()
				return -1;
				
			}
		}

		else { // this.hours() < o.hours()
			return -1;
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hours;
		result = prime * result + mins;
		result = prime * result + secs;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Time))
			return false;
		Time other = (Time) obj;
		if (hours != other.hours)
			return false;
		if (mins != other.mins)
			return false;
		if (secs != other.secs)
			return false;
		return true;
	}
	


}
