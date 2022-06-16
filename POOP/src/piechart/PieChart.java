package piechart;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PieChart extends JPanel {
	private ArrayList<PieSlice> slices = new ArrayList<>();
	private double totalSize;
	
	public PieChart() {
		setBackground(Color.WHITE);
		setBounds(100, 50, 500, 500);
	}
	
	public void addSlice(PieSlice p) {
		if(p.getSize() > 0) {
			slices.add(p);
			totalSize += p.getSize();
		}
		
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D comp2D = (Graphics2D) g;
		System.out.println(getSize());
		int r = getSize().width - 200;
		int xInset = 100;
		int yInset = 100;
		
		int cenX = xInset + r/2;
		int cenY = yInset + r/2;
		
		comp2D.setColor(getBackground());
		comp2D.fillRect(0, 0, getSize().width, getSize().height);
		comp2D.setColor(Color.lightGray);
		Ellipse2D.Double pie = new Ellipse2D.Double(xInset, yInset, r, r);
		comp2D.fill(pie);
		double start = 0;
		for (int i = 0; i < slices.size(); i++) {
			double extent = slices.get(i).getSize() * 360D / totalSize;
			comp2D.setColor(slices.get(i).getColor());
			Arc2D.Double drawSlice = new Arc2D.Double(xInset, yInset, r, r, start, extent,Arc2D.Double.PIE);
			
			Point2D pe = drawSlice.getEndPoint();
			Point2D ps = drawSlice.getStartPoint();
			int x = (int)(ps.getX() + pe.getX())/2;
			int y = (int)(ps.getY() + pe.getY())/2;
			int addX = 20;
			int addY = 10;
			if(x <= cenX && y >= cenY) {
				x -= addX;
				y += addY + 5;
			}else if(x <= cenX && y <= cenY) {
				x -= addX;
				y -= addY;
			}else if(x >= cenX && y <= cenY) {
				x += addX;
				y -= addY;
			}else if(x >= cenX && y >= cenY) {
				y += addY;
			}
			//System.out.println("EXTENT : " + extent);
			//double middle = pe.distance(drawSlice.getStartPoint()) / 2;
			start += extent;
			comp2D.fill(drawSlice);
			comp2D.setColor(Color.BLACK);
			comp2D.drawString(slices.get(i).getName(), x, y);
			
		}
	}
	
}
