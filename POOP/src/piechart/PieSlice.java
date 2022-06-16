package piechart;

import java.awt.*;
import javax.swing.*;

public class PieSlice {
	private Color color;
	private double size;
	private String name;
	
	public PieSlice(Color c, double d, String n) {
		color = c;
		size = d;
		name = n;
	}

	public Color getColor() {
		return color;
	}

	public double getSize() {
		return size;
	}

	public String getName() {
		return name;
	}
	
	public void addSize(double i) {
		size += i;
	}
	
}
