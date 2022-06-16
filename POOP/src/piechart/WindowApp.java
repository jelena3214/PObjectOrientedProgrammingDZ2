package piechart;
import xygraph.XYGraph;
import olympicgames.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.*;

public class WindowApp extends JFrame {
	//dodaj u prozor delove za filter, gde se unosi i na dugme prikaz nek prikaze taj grafik
	//removujemo svaki put panel i dodajemo ga
	JRadioButton numOfAth, numOfDisc, avrgHeight, avrgWeight;
	JButton show = new JButton("Prikazi");
	ButtonGroup g1 = new ButtonGroup();
	JTextField sport, year, type, medal, startY, endY;
	JPanel center = new JPanel();
	private boolean ready = true; //TODO vidi oco kako iskorititi
	private String sportF;
	private int yearF, typeF, medalF, endYear, startYear;
	private OlympicGames ol = new OlympicGames();
	private int choice;
	private PieChart loans;
	private XYGraph graph;
	
	public WindowApp() {
		setBounds(400, 100,600,600);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		populateWindow();
		addListeners();
		
		setResizable(false);
		setVisible(true);
	}

	private void addListeners() {
		numOfAth.addActionListener((ae)->{
			choice = 1;
		});
		
		numOfDisc.addActionListener((ae)->{
			choice = 2;
		});
		
		show.addActionListener((ae)->{
			sportF = sport.getText();
			if(!year.getText().isEmpty()) {
				yearF = Integer.parseInt(year.getText());
			}else yearF = 0;
			if(!type.getText().isEmpty()) {
				typeF = Integer.parseInt(type.getText());
			}else typeF = 0;
			if(!medal.getText().isEmpty()) {
				medalF = Integer.parseInt(medal.getText());
			}else medalF = 0;
			if(!startY.getText().isEmpty())startYear = Integer.parseInt(startY.getText());
			if(!endY.getText().isEmpty())endYear = Integer.parseInt(endY.getText());
			if(ready) {
				switch(choice) {
				case 1:
					HashMap<String, Integer> res = ol.numOfParticipants(0L, 0L, sportF, yearF, typeF, medalF);
					List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(res.entrySet());  
					//sorting the list elements  
					Collections.sort(list, new Comparator<Entry<String, Integer>>()   
					{
						public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
						{  
							return o2.getValue().compareTo(o1.getValue());
						}
					});  
					if(loans != null) {
						WindowApp.this.remove(loans);
					}
					
					if(graph != null) {
						WindowApp.this.remove(graph);
					}
					
					loans = new PieChart();
					PieSlice other = new PieSlice(Color.BLUE, 0, "Other");
					int iterator = 1;
					for(Entry<String, Integer> entry: list) {
						if(iterator > 8) {
					    	other.addSize(entry.getValue());
					    	continue;
					    }
					    iterator++;
					    loans.addSlice(new PieSlice(new Color((int)(Math.random() * 0x1000000)), entry.getValue(), entry.getKey()));
					}

					loans.addSlice(other);
					//center.add(loans);
					//center.revalidate();
					WindowApp.this.add(loans, BorderLayout.CENTER);
					loans.revalidate();
					loans.paint(getGraphics());
					validate();
					repaint();
					break;
				case 2: 
					HashMap<Integer, Integer> res0 = ol.numOfDisciplinesSeason(0L, 0L, sportF, startYear, endYear, 0, 0, "Summer");

					HashMap<Integer, Integer> res1 = ol.numOfDisciplinesSeason(0L, 0L, sportF, startYear, endYear, 0, 0, "Winter");

					List<Entry<Integer, Integer>> list0 = new LinkedList<Entry<Integer, Integer>>(res0.entrySet());  
					Collections.sort(list0, new Comparator<Entry<Integer, Integer>>()   
					{
						public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
						{  
							return o2.getValue().compareTo(o1.getValue());
						}
					}); 
					List<Entry<Integer, Integer>> list1 = new LinkedList<Entry<Integer, Integer>>(res1.entrySet());  
					Collections.sort(list1, new Comparator<Entry<Integer, Integer>>()   
					{
						public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
						{  
							return o2.getValue().compareTo(o1.getValue());
						}
					}); 

					if(loans != null) {
						WindowApp.this.remove(loans);
					}
					if(graph != null) {
						WindowApp.this.remove(graph);
					}
					
					graph = new XYGraph(list0, list1, 400, 400, startYear - 1, endYear);
					
					
					WindowApp.this.add(graph, BorderLayout.CENTER);
					graph.revalidate();
					graph.paint(getGraphics());
					validate();
					repaint();
					
					break;
				}
			}
		});
		
	}

	private void populateWindow() {
		JPanel left = new JPanel(new GridLayout(0,1));
		numOfAth = new JRadioButton("Broj ucesnika");
		numOfDisc = new JRadioButton("Broj disciplina");
		avrgHeight = new JRadioButton("Prosecna visina");
		avrgWeight = new JRadioButton("Prosecna tezina");
		g1.add(numOfAth);
		g1.add(numOfDisc);
		g1.add(avrgHeight);
		g1.add(avrgWeight);
		left.add(numOfAth);
		left.add(numOfDisc);
		left.add(avrgHeight);
		left.add(avrgWeight);
		add(left, BorderLayout.WEST);
		
		JPanel bottom = new JPanel(new GridLayout(2, 0));
		sport = new JTextField();
		year = new JTextField();
		type  = new JTextField();
		medal = new JTextField();
		startY = new JTextField();
		endY = new JTextField();
		
		bottom.add(new Label("Sport:"));
		bottom.add(sport);
		bottom.add(new Label("Year:"));
		bottom.add(year);
		bottom.add(new Label("Event type:"));
		bottom.add(type);
		bottom.add(new Label("Medal:"));
		bottom.add(medal);
		bottom.add(new Label("Start year:"));
		bottom.add(startY);
		bottom.add(new Label("End year:"));
		bottom.add(endY);
		JPanel help = new JPanel();
		help.add(show);
		JPanel allBottom = new JPanel();
		allBottom.add(bottom);
		allBottom.add(help);
		add(allBottom, BorderLayout.SOUTH);
		center.setBackground(Color.white);
		//center.add(new Label("Prazno"));
		//add(center, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new WindowApp();
	}

}



/*OlympicGames ol = new OlympicGames();
HashMap<String, Integer> res = ol.numOfParticipants(0L, 0L, "Athletics", 2016, 0, 1);


List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(res.entrySet());  
//sorting the list elements  
Collections.sort(list, new Comparator<Entry<String, Integer>>()   
{
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
	{  
		return o2.getValue().compareTo(o1.getValue());
	}
});  

PieChart loans = new PieChart();
PieSlice other = new PieSlice(Color.BLUE, 0, "Other");
int iterator = 1;
for(Entry<String, Integer> entry: list) {
	if(iterator > 8) {
    	other.addSize(entry.getValue());
    	continue;
    }
    iterator++;
    loans.addSlice(new PieSlice(new Color((int)(Math.random() * 0x1000000)), entry.getValue(), entry.getKey()));
}

loans.addSlice(other);
this.add(loans);
OlympicGames ol = new OlympicGames();
HashMap<Integer, Integer> res = ol.averageHeightSeason(0L, 0L, "Ice Hockey", 1900, 2016, 0, 0, "Summer");

HashMap<Integer, Integer> res1 = ol.averageHeightSeason(0L, 0L, "Ice Hockey", 1900, 2016, 0, 0, "Winter");

List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(res.entrySet());  
Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
{
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
	{  
		return o2.getValue().compareTo(o1.getValue());
	}
}); 

List<Entry<Integer, Integer>> list1 = new LinkedList<Entry<Integer, Integer>>(res1.entrySet());  
Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
{
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
	{  
		return o2.getValue().compareTo(o1.getValue());
	}
}); 

XYGraph a = new XYGraph(list, list1, 400, 400, 1899, 2016);
add(a);*/