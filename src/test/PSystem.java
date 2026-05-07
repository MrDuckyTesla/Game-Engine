package test;

import processing.core.PApplet;

public class PSystem {
	
	private Particle[] body;
	
	public PSystem(Particle[] body) {
		this.body = body;
	}
	
	public PSystem() {
		this.body = new Particle[] {new Particle(300, 200, 40), new Particle(200, 400, 40), new Particle(400, 400, 40)};
	}
	
	public void update(PApplet app) {
		for (int i = 0; i < this.body.length; i++) {
			for (int j = 0; j < this.body.length; j++) {
				if (j != i) {
					// F = Gmm / r^2
				}
			}
			this.body[i].update(app);
		}
	}

}
