package engine.neural.activations;

import engine.data.ByteHelper;
import engine.neural.Activation;

public class GELU implements Activation {

	@Override
	public float function(float x) {  // Approximation of Gaussian Distribution found online
		return x * 0.5f * (1 + (float) Math.tanh(Math.sqrt(2.0/Math.PI)*(x+0.044715*x*x*x)));
	}

	@Override
	public float derivative(float x) {
		float temp = (float) Math.tanh((Math.sqrt(2/Math.PI)*(x+0.044715*x*x*x)));
		return (float) (0.5*(1+temp)+x*0.5*(1-temp*temp)*(Math.sqrt(2.0/Math.PI)*(1+0.134145*x*x)));
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Activation deserialize(ByteHelper bytes) {
		return new GELU();
	}

}
