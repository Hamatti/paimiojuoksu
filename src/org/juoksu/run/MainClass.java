package org.juoksu.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;

import org.juoksu.data.Logger;
import org.juoksu.data.Runner;
import org.juoksu.data.Serie;
import org.juoksu.data.SerieHandler;

public class MainClass {

	private final String HTML_HEADER = "<!doctype html>\n<html>\n<head>\n<title>39. Paimio-juoksu</title>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n";
	private final String HTML_STYLE = "<style> \n h1 { font-size: 4.2em; margin: 0px; } \n h2 { font-size: 1.6em;margin-top: 0px; }\n table { page-break-after: always; border: 1px solid black;	border-collapse:collapse; width: 50%; margin-top: 10px; margin-bottom: 10px; }\n caption { text-align: left;font-weight: bold;font-size: 1.2em; }\n td { border: 1px solid black; border-collapse:collapse; text-align: center; }\n thead { font-weight: bolder; } \n</style><script src=\"sorttable.js\"></script></head>\n";
	private final String HTML_BODY_START = "<body>\n<h1> 39. Paimio-juoksu </h1>\n<h2>sunnuntai 28.4.2013</h2>\n\n";
	private final String TABLE_HEADER = "\t<thead><tr><td>Sij.</td><td>Numero</td><td>Nimi</td><td>Seura</td><td>Aika</td></tr></thead>\n";
	private final String HTML_BODY_END = "</body></html>";
	private final String ENCODING = "UTF-8";
	private final String PARTICIPANTS_FILE = "C:\\paimio-juoksu-osallistujat.txt";
	private final String HTML_FILENAME = "C:\\paimio-juoksu.html";
	
	private SerieHandler sh;
	private String name;
	private Logger logger;
	
	public void setUp(String name) {
		this.name = name;
		sh = new SerieHandler();
		logger = new Logger("C:\\log.txt");
	}
	
	public void addRunner(String runner) {
		String[] runner_info = runner.split(",");
		sh.addRunner(Integer.parseInt(runner_info[0]), runner_info[1], runner_info[2]);
		printRunners();		
	}

	public SerieHandler getSh() {
		return sh;
	}

	
	private  void sortAll() {
		for (Serie s : sh.getSeries()) {
			if(s.getRunners() != null)
				Collections.sort(s.getRunners());
		}
	}
	
	public void addTime(int runnerNumber, String time) throws NullPointerException {
		Runner r;
	
		r = sh.findRunnerByNumber(runnerNumber);
		if (r == null) {
			logger.log("Juoksijaa #" + runnerNumber + " ei löytynyt");
			throw new NullPointerException("Juoksijaa #" + runnerNumber + " ei löydy");
			
		}
		r.setTime(time);	
	}
	

	@SuppressWarnings("unused")
	private  int[] getNumbers() {
		int runnersCount = 0;
		for (Serie s : sh.getSeries()) {
			runnersCount += s.getRunners().size();
		}
		int[] nums = new int[runnersCount];
		int i = 0;
		for (Serie s : sh.getSeries()) {
			for (Runner r : s.getRunners()) {
				nums[i] = r.getNumber();
				++i;
			}
		}
		
		return nums;
	}

	private void printRunners() {
		for (Serie s : sh.getSeries()) {
			System.out.println(s.getName());
			for (Runner r : s.getRunners()) {
				System.out.println(r);
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private  void printTopThree() {
		for (Serie s : sh.getSeries()) {
			System.out.println(s.getName());
			int i = 1;
			for (Runner r : s.getTopThree()) {
				System.out.println(i + ". " + r);
				++i;
			}
			
		}
	}
	
	public void readSeriesFromFile(String filename) {
		/* Input in format [series],[low],[high],[desc] */
		try {
			BufferedReader reader = new BufferedReader( new FileReader (filename));
			String line = "";
			while( (line = reader.readLine() ) != null ) {
				String[] series_parts = line.split(",");
				sh.addSerie(series_parts[0], Integer.parseInt(series_parts[1]), Integer.parseInt(series_parts[2]), series_parts[3]);
			}
			reader.close();
		}
		catch (IOException e) {
			return;
		}
		
	}
	
	
	public void readRunnersFromFile(String filename) {
		/* Input in format [number],[name],[team],[time] */
		try {
			BufferedReader reader = new BufferedReader( new FileReader (filename));
			String line = "";
			while( (line = reader.readLine() ) != null ) {
				String[] runner_parts = line.split(",");
				Runner r = new Runner(Integer.parseInt(runner_parts[0]), runner_parts[1], runner_parts[2]);
				sh.addRunner(r.getNumber(), r.getName(), r.getTeam());
				if (runner_parts.length > 3) {
					r.setTime(runner_parts[3].trim());						
				}
			}
			reader.close();
		}
		catch (IOException e) {
			return;
		}
		
	}
	
	public void writeHTML() {
		try {
			FileWriter fstream = new FileWriter(HTML_FILENAME);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(HTML_HEADER);
			out.write(HTML_STYLE);
			out.write(HTML_BODY_START);
			sortAll();
			for (Serie s : sh.getSeries()) {
				out.write(s.HTMLtableheader() + "\n");
				out.write(s.HTMLcaption() + "\n");
				out.write(TABLE_HEADER);
				out.write("<tbody>");
				List<Runner> runners = s.getRunners();
				for (int i = 0; i < runners.size(); i++) {
					Runner r = runners.get(i);
					out.write("\t<tr><td>"+ (i+1) +".</td>" + r.toHTMLRow() + "</tr>\n");
				}
				out.write("</tbody>");
				out.write("</table>\n\n");
			}
			out.write(HTML_BODY_END);
			out.close();
		}
		catch (IOException e) {
			return;
		}
		
	}

	public List<Serie> getSeries() {
		return sh.getSeries();
	}

	public void readFromFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new
					FileInputStream(file), ENCODING));
			String line = "";
			boolean series = true;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#")) continue;
				if (line.equals("_SERIES")) {
					series = true;
					continue;
				}
				if (line.equals("_RUNNERS")) {
					series = false;
					continue;
				}
				if(series) {
					String[] parts = line.split(":");
					if (sh.findSerie(parts[0]) == null) {
						sh.addSerie(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
						System.out.println("Lisättiin sarja " + parts[0]);
					}
					
				}
				else {
					String[] parts = line.split(":");
					sh.addRunner(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
					System.out.println("Lisättiin juoksija " + parts[0] + ": " + parts[1]);
				}
			}
			//printRunners();
			reader.close();
			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	public void saveToFile(File file) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),ENCODING));
		out.write("# Paimio-juoksu-tulospalvelu-tiedosto. Kaikki #-merkillä alkavat rivit kommentteja.\n");
		out.write("_SERIES\n");
		for (Serie s : sh.getSeries()) {
			System.out.println(s.getName() + ":" + s.getLow() + ":" + s.getHigh() + ":" + s.getDescription() +"\n");
			out.write(s.getName() + ":" + s.getLow() + ":" + s.getHigh() + ":" + s.getDescription() +"\n");
		}
		out.write("_RUNNERS\n");
		for (Serie s : sh.getSeries()) {
			for (Runner r : s.getRunners()) {
				out.write(r.getNumber() + ":" + r.getName() + ":" + r.getTeam() + ":" + r.getTime() +"\n");
			}
		}
		out.close();
	}

	public void writeParticipantsList() {
		try {
			FileWriter fstream = new FileWriter(PARTICIPANTS_FILE);
			BufferedWriter out = new BufferedWriter(fstream);
			sortAll();
			for (Serie s : sh.getSeries()) {
				List<Runner> runners = s.getRunners();
				for (int i = 0; i < runners.size(); i++) {
					Runner r = runners.get(i);
					out.write(s.getName() + " " + r.getNumber() + " " + r.getName() + "\n");
				}
			}
			out.close();
		}
		catch (IOException e) {
			return;
		}
		
	}

			
}

