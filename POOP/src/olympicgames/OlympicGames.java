package olympicgames;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class OlympicGames {
	public static Long athletes, events;
	static {
		
		System.loadLibrary("Proba");
		System.out.println("Ucitano");
		events = initEvents(new String("C:\\Users\\Lenovo\\source\\repos\\Proba\\Proba\\events.txt"));
		athletes = initAthletes(new String("C:\\Users\\Lenovo\\source\\repos\\Proba\\Proba\\athletes.txt"));
	}
	
	//type 1 - individual, 0 - team -1 nista
	//medaltype 0 - gold, 1- silver, 2 - bronze, 3-NA -1 nista
	public native HashMap<String, Integer> numOfParticipants(Long athletes, Long events, String sport, int year, int type, int medaltype);
	public native HashMap<Integer, Integer> numOfDisciplinesSeason(Long athletes, Long events, String sport, int startYear, int endYear, int type, int medaltype, String season);
	public native HashMap<Integer, Integer> averageHeightSeason(Long athletes, Long events, String sport, int startYear, int endYear, int type, int medaltype, String season);
	public native HashMap<Integer, Integer> averageWeightSeason(Long athletes, Long events, String sport, int startYear, int endYear, int type, int medaltype, String season);
	public static native long initEvents(String filename);
	public native static long initAthletes(String filename);
	
	public static void main(String[] args) {
		System.out.println();
		OlympicGames ol = new OlympicGames();
		System.out.println("Uslo");
		//OlympicGames.init();
		System.out.println(events);
		System.out.println(athletes);
		System.out.println("Proslo");
		
		HashMap<String, Integer> res = ol.numOfParticipants(athletes, events, "Athletics", 2016, -1, -1);
		System.out.println("Mapiralo");
		int num = 0;
		
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(res.entrySet());
		// sorting the list elements
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		for(Entry<String, Integer> entry: list) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		System.out.println(num);
		
		/*HashMap<Integer, Integer> res = ol.averageWeightSeason(athletes, events, "", 1992, 2000, 0, 0, "Summer");
		System.out.println("Mapiralo");
		int num = 0;
		res.entrySet().forEach(entry -> {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		});
		System.out.println(res.size());
		
		HashMap<Integer, Integer> res1 = ol.averageWeightSeason(athletes, events, "", 1990, 2000, 1, 3, "Winter");
		System.out.println("Mapiralo1");
		
		res1.entrySet().forEach(entry -> {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		});*/
	}
	
}
