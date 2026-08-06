package engine.graphics;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;
import java.util.Stack;

public abstract class Game {
	
	private Stack<Graphics2D> stack = new Stack<>();
	private Graphics2D g;
	private final Settings s = new Settings();
	private boolean isOpen = true;
	
	public abstract void loop();
	
	public Settings getSettings() {return this.s;}
	
	public class Settings {
		private String winName = "Game";
		private int wid = 800, hgt = 800, stroke = 1;
		private boolean showMouse = true, fillShape = true;
		
		public void setWindowName(String name) {this.winName = name;}
		public void setWindowWidth(int wid) {this.wid = wid;}
		public void setWindowHeight(int hgt) {this.hgt = hgt;}
		public void setStroke(int stroke) {this.stroke = stroke;}
		public void setFill(boolean fillShape) {this.fillShape = fillShape;}
		
	}
	
	public void main() {
		JFrame j = new JFrame(s.winName);
		Canvas c = new Canvas();
		
		c.setSize(s.wid, s.hgt);
		
		j.add(c);
		j.pack();
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);  // Centered window
		j.setVisible(true);
		
		c.createBufferStrategy(2);
		BufferStrategy bs = c.getBufferStrategy();
		
		while (this.isOpen) {
			this.g = (Graphics2D) bs.getDrawGraphics();
			
			Color clr = this.g.getColor();
			
			this.g.setColor(new Color(123, 123, 123));
			this.g.fillRect(0, 0, s.wid, s.hgt);
			
			this.fill(clr);
			
			this.loop();
			
			this.g.dispose();
			bs.show();
			
		}
	}
	
	public void push() {
		this.stack.push(this.g);
		this.g = (Graphics2D) this.g.create();
	}
	
	public void pop() {
		this.g.dispose();
		this.g = this.stack.pop();
	}
	
	public void rect(int x, int y, int wid, int hgt) {
		if (this.s.fillShape) {this.g.fillRect(x, y, wid, hgt);}
		else {this.g.fillRect(x, y, wid, hgt);}
	}
	
	public void clearRect(int x, int y, int wid, int hgt) {
		this.g.clearRect(x, y, wid, hgt);
	}
	
	public void copy(int x, int y, int wid, int hgt, int dx, int dy) {
		this.g.copyArea(x, y, wid, hgt, dx, dy);
	}
	
	public void fill(Color c) {this.g.setColor(c);}
	public void fill(float r, float g, float b, float a) {this.g.setColor(new Color(r, g, b, a));}
	public void fill(float r, float g, float b) {this.g.setColor(new Color(r, g, b));}
	public void fill(float g, float a) {this.g.setColor(new Color(g, g, g, a));}
	public void fill(float g) {this.g.setColor(new Color(g, g, g));}
	public void fill(int rgb, boolean hasAlpha) {this.g.setColor(new Color(rgb, hasAlpha));}
	public void fill(int rgb) {this.g.setColor(new Color(rgb));}

}
