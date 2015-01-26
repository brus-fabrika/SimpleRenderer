package com.fabrika.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RendererView extends JFrame{
	RendererView() {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		setResizable(false);
		setTitle("Simple renderer");
	
		ImagePanel panel = new ImagePanel();
		panel.setBackground(Color.BLACK);
		this.add(panel, BorderLayout.CENTER);
		
		JButton button = new JButton("Draw");
		this.add(button, BorderLayout.NORTH);
		button.addActionListener(panel);
	}
}

class ImagePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L; // just to suppress warning

	private void doDrawing(Graphics g){
		drawLine(g, 113, 120, 180, 140, Color.WHITE);
		drawLine(g, 120, 113, 140, 180, Color.RED);
		drawLine(g, 180, 140, 113, 120, Color.RED);
	}
	
	private void drawLine(Graphics g, int x0, int y0, int x1, int y1, Color c) {

		boolean steep = false;
		
		if(Math.abs(x0-x1) < Math.abs(y0-y1)){
			int t = x0; x0 = y0; y0 = t;
				t = x1; x1 = y1; y1 = t;
			steep = true;
		}
		
		if(x0 > x1){
			int t = x1; x1 = x0; x0 = t;
				t = y1; y1 = y0; y0 = t;
		}
		
		for(int x = x0; x <= x1; ++x){
			double t = (x - x0) / (double)(x1 - x0);
			int y = (int) (y0*(1 - t) + y1*t);
			if(steep){
				drawPoint(g, new Point(y, 768 - x), c);
			} else {
				drawPoint(g, new Point(x, 768 - y), c);
			}
		}
	}
	
	private void drawLine(Graphics g, Point p1, Point p2, Color c) {
		drawLine(g, p1.x, p1.y, p2.x, p2.y, c);
	}
	
	private void drawPoint(Graphics g, Point p, Color c){
		g.setColor(c);
		g.fillRect(p.x, p.y, 1, 1);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		WavefrontParser objParser = new WavefrontParser("e:\\cube.obj");
		
		objParser.parse();
		
		for(Vec3f v: objParser.vertexes){
			System.out.println(v);
		}
		
		for(Face f: objParser.faces){
			System.out.println(f);
		}
		
	}
}