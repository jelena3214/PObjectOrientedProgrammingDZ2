package xygraph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.*;

public class XYGraph extends JPanel {
	List<Entry<Integer, Integer>> summer, winter;
	private int width, height, numOfDevisionsX = 8, numOfDevisionsY = 8;
	private int upperLeftX = 0, upperLeftY = 0;
	private int downLeftX, downLeftY, upperRightX, upperRightY, downRightX, downRightY;
	private int startYear, endYear;
	
	public XYGraph(List<Entry<Integer, Integer>> a, List<Entry<Integer, Integer>> b, int h, int w, int sy, int ey) {
		summer = a;
		winter = b;
		setBackground(Color.WHITE);
		height = h;
		width = w;
		upperRightX = width;
		upperRightY = 0;
		downLeftX = 0;
		downLeftY = height;
		downRightX = width;
		downRightY = height;
		startYear = sy;
		endYear = ey;
	}
	
	private int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	private int getMaxY() {
		int s = summer.get(0).getValue();
		int w = winter.get(0).getValue();
		if(s > w)return s;
		return w;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.translate(40, 40);
		Graphics2D comp2D = (Graphics2D) g;
		comp2D.setColor(Color.BLACK);
		comp2D.drawLine(upperLeftX, upperLeftY, downLeftX, downLeftY);
		
		
		System.out.println(getMaxY());
		//Y osa i labele
		int stepY = height / numOfDevisionsY + (height%numOfDevisionsY != 0?1:0);
		int stepNum = getMaxY() / numOfDevisionsY + (getMaxY()%numOfDevisionsY != 0?1:0);
		System.out.println(stepNum + "  STEP NUM");
		int num = 0;
		int startY = 0;
		for(int i = 0; i <= numOfDevisionsY; i++) {
			comp2D.fillOval(-3, (height - 1) - startY, 6, 6);
			comp2D.drawString("" + num, -20, (height - 1) - startY);
			startY += stepY;
			num += stepNum;
		}
		
		//X osa i labele
		comp2D.drawLine(downLeftX, downLeftY, downRightX, downRightY);
		int scale = endYear - startYear;
		
		if(scale > numOfDevisionsX) {
			scale = numOfDevisionsX;
		}
		int stepYear = (endYear - startYear)/scale + ((endYear - startYear)%scale != 0?1:0);
		int stepX = width / scale;
		System.out.println("STEPP " + stepX);
		int startX = 0;
		int year = startYear;
		for(int i = 0; i <= scale; i++) {
			comp2D.fillOval(startX - 3, height - 1, 6, 6);
			comp2D.drawString("" + year, startX, (height - 1));
			startX += stepX;
			year += stepYear;
		}
		
		
		System.out.println("STEP X" + stepX + "Step y" + stepY);
		
		//Plot summer dots
		comp2D.setColor(Color.RED);
		for(Entry<Integer, Integer> entry: summer) {
			int year1 = entry.getKey();
			int val1 = entry.getValue();
			if(val1 == 0)continue;
			//System.out.println("y: " + year1 + " v: " + val1);
			double x1 = (year1 - startYear)*(stepX/stepYear);
			double st = (stepY*1.0/stepNum*1.0)*val1;
			double y1 = height - st;
			//System.out.println("x:" + x1 + " y:" + y1);
			comp2D.fillOval((int)x1 - 3, (int)y1 - 3, 6, 6);
		}
		
		//Plot winter dots
		comp2D.setColor(Color.BLUE);
		for(Entry<Integer, Integer> entry: winter) {
			int year1 = entry.getKey();
			int val1 = entry.getValue();
			if(val1 == 0)continue;
			double x1 = (year1 - startYear)*(stepX/stepYear);
			double st = (stepY*1.0/stepNum*1.0)*val1;
			double y1 = height - st;
			comp2D.fillOval((int)x1 - 3, (int)y1 - 3, 6, 6);
		}
		
	}
	
}
