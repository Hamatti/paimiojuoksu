package org.juoksu.data;

public class Runner implements Comparable<Runner> {

	private int number;
	private String name;
	private String team;
	private Time time;
	
	public Runner() {
		number = 0;
		name = "";
		team = "";
		time = new Time(0,0,0);
	}
	
	public Runner(int num, String n, String t) {
		this.number = num;
		this.name = n;
		this.team = t;
		this.time = new Time(0,0,0);
	}
	
	public Runner(int num, String n, String t, String time) {
		this.number = num;
		this.name = n;
		this.team = t;
		setTime(time);
	}
	
	/* Getters and setters */
	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public void setTime(int h, int m, int s) {
		time = new Time(h,m,s);
	}
	
	public void setTime(String s) {
		String[] table = s.split("\\.");
        int hrs = 0;
        int mins = 0;
        int secs = 0;
        if (table.length > 1) {
            hrs = Integer.parseInt(table[0]);
            table = table[1].split(",");
        }
        else {
            table = table[0].split(",");
        }
        mins = Integer.parseInt(table[0]);
        secs = Integer.parseInt(table[1]);

		time = new Time(hrs, mins, secs);
		
	}
	
	/* Other functions */
	
	/* Overridden functions */
	@Override
	public String toString() {
		return number + "\t" + name + "\t" + team + "\t" + time.toString();
	}
	
	
	@Override
	public int compareTo(Runner o2) {
		return this.getTime().compareTo(o2.getTime());
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
		if (!(obj instanceof Runner)) {
			return false;
		}
		Runner other = (Runner) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (team == null) {
			if (other.team != null) {
				return false;
			}
		} else if (!team.equals(other.team)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	public String toHTMLRow() {
		return "<td>"+number + "</td><td>"+ name + "</td><td>" + team + "</td><td>" + time.toString() + "</td>";
	}
	
	
	

	
}
