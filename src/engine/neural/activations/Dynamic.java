package engine.neural.activations;

import engine.neural.Activation;

public class Dynamic implements Activation {
	
	private Activation[] layer;
	private int layerNum = 0;

	public Dynamic(Activation[] layer) {this.layer = layer;}
	
	public void next() {
		this.layerNum = this.layerNum == this.layer.length? 0 : this.layerNum++;
	}

	@Override
	public float function(float x) {
		return this.layer[this.layerNum].function(x);
	}

	@Override
	public float derivative(float x) {
		return this.layer[this.layerNum].derivative(x);
	}
	
	@Override
	public String getClassInfo() {
		return this.getClass().getName() + "\n" + this.layer;
	}

}
