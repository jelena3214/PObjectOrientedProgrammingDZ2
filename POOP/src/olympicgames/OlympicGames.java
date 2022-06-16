package olympicgames;

import java.util.HashMap;

public class OlympicGames {
	public static Long athletes, events;
	static {
		
		System.loadLibrary("Proba");
		System.out.println("Ucitano");
		events = initEvents(new String("C:\\Users\\Lenovo\\source\\repos\\Proba\\Proba\\events.txt"));
		athletes = initAthletes(new String("C:\\Users\\Lenovo\\source\\repos\\Proba\\Proba\\athletes.txt"));
	}
	
	//type 1 - individual, 2 - team
	//medaltype 1 - gold, 2- silver, 3 - bronze
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
		
		/*HashMap<String, Integer> res = ol.numOfParticipants(athletes, events, "Athletics", 2016, 0, 0);
		System.out.println("Mapiralo");
		int num = 0;
		res.entrySet().forEach(entry -> {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		});
		for(Integer value: res.values()) {
			num += value;
		}
		System.out.println(num);*/
		
		HashMap<Integer, Integer> res = ol.averageWeightSeason(athletes, events, "", 1992, 2000, 0, 0, "Summer");
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
		});
	}
	
}
