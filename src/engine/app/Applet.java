package engine.app;

import javax.swing.*;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

import java.awt.*;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Stack;

public abstract class Applet implements FastSerializable<Applet> {
	
	private Stack<Graphics2D> stack = new Stack<>();
	private Graphics2D g;
	private JFrame j = new JFrame("Game");
	private final Settings s = new Settings();
	private long time = 0L, lastTick = 0L, lastFrame = 0L;
	private boolean isOpen = true;
	
	public abstract void setup();
	
	public abstract void update();
	
	public abstract void render();
	
	public byte[] save() {return new byte[] {};}
	public <T extends Applet> T load(byte[] bytes) {return null;}
	
	public Settings getSettings() {return this.s;}
	
	public class Settings implements FastSerializable<Settings> {
		private String winName = "Game";
		private int wid = 800, hgt = 800, fps = 60, tps = 100;
		private boolean showMouse = true, fillShape = true;
		
		public void setWindowName(String name) {this.winName = name; j.setTitle(this.winName);}
		public void setWindowWidth(int wid) {this.wid = wid;}
		public void setWindowHeight(int hgt) {this.hgt = hgt;}
		public void setFill(boolean fillShape) {this.fillShape = fillShape;}
		public void setPixelScale() {g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);}
		public void setSmoothScale() {g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);}
		public void setFastScale() {g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);}
		
		@Override
		public byte[] serialize() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Settings deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Settings[] getProtoArray(int length) {
			return new Settings[length];
		}
		
	}
	
	public void run() {
		Canvas c = new Canvas();
		
		c.setSize(s.wid, s.hgt);
		
		this.j.add(c);
		this.j.pack();
		
		this.j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.j.setLocationRelativeTo(null);  // Centered window
		this.j.setVisible(true);
		
		c.createBufferStrategy(2);
		BufferStrategy bs = c.getBufferStrategy();
		while (this.isOpen) {
			this.time = System.nanoTime();
			
			if (this.time - this.lastTick >= 1_000_000_000L / s.tps) {
				this.update();
				this.lastTick = this.time;
			}
			
			if (this.time - this.lastFrame >= 1_000_000_000L / s.fps) {
				this.g = (Graphics2D) bs.getDrawGraphics();
				
				this.render();
				
				this.g.dispose();
				bs.show();
				
				this.lastFrame = this.time;
			}
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
	
	public void background() {
		Color clr = this.g.getColor();
		this.g.setColor(new Color(123, 123, 123));
		this.g.fillRect(0, 0, s.wid, s.hgt);
		this.fill(clr);
	}
	
	public void background(int r, int g, int b) {
		Color clr = this.g.getColor();
		this.g.setColor(new Color(r, g, b));
		this.g.fillRect(0, 0, s.wid, s.hgt);
		this.fill(clr);
	}
	
	public void image(BufferedImage img, int x, int y) {
		this.g.drawImage(img, x, y, j);
	}
	
	public void image(BufferedImage img, int x, int y, int wid, int hgt) {
		this.g.drawImage(img, x, y, wid, hgt, j);
	}
	
	public void image(BufferedImage img, int sx, int sy, int dx, int dy, int x, int y, int wid, int hgt) {
		this.g.drawImage(img, x, y, wid, hgt, j);
	}
	
	public void rect(int x, int y, int wid, int hgt) {
		if (this.s.fillShape) {this.g.fillRect(x, y, wid, hgt);}
		else {this.g.drawRect(x, y, wid, hgt);}
	}
	
	public void clearRect(int x, int y, int wid, int hgt) {
		this.g.clearRect(x, y, wid, hgt);
	}
	
	public void ellipse(int x, int y, int wid, int hgt) {
		if (this.s.fillShape) {this.g.fillOval(x, y, wid, hgt);}
		else {this.g.drawOval(x, y, wid, hgt);}
	}
	
	public void circ(int x, int y, int r) {
		if (this.s.fillShape) {this.g.fillOval(x, y, r, r);}
		else {this.g.drawOval(x, y, r, r);}
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
	
//	public Graphics2D getGraphics() {return this.g;}
//	public JFrame getFrame() {return this.j;}

	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		this.save();
		return null;
	}

	@Override
	public Applet deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		this.load(null);
		return null;
	}

	@Override
	public Applet[] getProtoArray(int length) {
		return new Applet[length];
	}

}
