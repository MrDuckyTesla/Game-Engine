package test;

import java.io.IOException;

import engine.neural.*;
import engine.neural.activations.*;
import engine.neural.costs.*;
import engine.neural.initializers.*;
import engine.neural.networks.*;
import engine.neural.optimizers.*;
import engine.util.ByteHelper;
import engine.util.Timer;
import engine.util.Vector;
import engine.util.data.Data;
import engine.util.data.compressions.RLE;
import engine.util.data.storages.Local;
import obsolete.ToolKit;
import processing.core.PApplet;

public class Test_Neural {

	public static void main(String[] args) {
//		PApplet.main(Test.class);
//		Timer.start(false);
//		Matrix m = new Matrix(2, 3);
//		m.propagate();
//		m.set(1, 1, 1);
//		m.set(0, 1, 2);
//		m.set(0, 0, 3);
//		m.set(1, 0, 4);
//		m.set(9, 1, 4);
//		m.set(5, 5, 400);
//		System.out.println(m);
		
//		Matrix n = new Matrix(3, 2);
//		n.propagate();
//		n.set(1, 1, 4);
//		n.set(0, 1, 3);
//		n.set(0, 0, 2);
//		n.set(1, 0, 1);
//		System.out.println(n);
		
//		m.swapRow(0, 2);
//		System.out.println(m);
		
//		System.out.println(m.multiply(n));
		
//		Vector[] inputs = {
//		    new Vector(new float[]{0, 0, 0}),
//		    new Vector(new float[]{1, 0, 0}),
//		    new Vector(new float[]{0, 1, 0}),
//		    new Vector(new float[]{0, 0, 1}),
//		    new Vector(new float[]{1, 1, 0}),
//		    new Vector(new float[]{0, 1, 1}),
//		    new Vector(new float[]{1, 0, 1}),
//		    new Vector(new float[]{1, 1, 1})
//		};
//
//		Vector[] expected = {
//		    new Vector(new float[]{0}),
//		    new Vector(new float[]{1}),
//		    new Vector(new float[]{1}),
//		    new Vector(new float[]{1}),
//		    new Vector(new float[]{0}),
//		    new Vector(new float[]{0}),
//		    new Vector(new float[]{0}),
//		    new Vector(new float[]{1})
//		};
		
//		System.out.println(test.predict(new Vector(new float[] {0, 0, 0})));
//		System.out.println(test.predict(new Vector(new float[] {0, 0, 1})));
//		System.out.println(test.predict(new Vector(new float[] {0, 1, 0})));
//		System.out.println(test.predict(new Vector(new float[] {1, 0, 0})));
//		System.out.println(test.predict(new Vector(new float[] {1, 1, 0})));
//		System.out.println(test.predict(new Vector(new float[] {0, 1, 1})));
//		System.out.println(test.predict(new Vector(new float[] {1, 0, 1})));
//		System.out.println(test.predict(new Vector(new float[] {1, 1, 1})));
		int trainingSize = 1000;
		Vector[] inputs = new Vector[trainingSize], expected = new Vector[trainingSize];
		
		Initializer initializer = new Kaiming(false);
		Activation activation = new GELU();
		Cost cost = new MeanSquaredError();
		Optimizer optimizer = new SGDMomentum(0.005f, 0.8f);
		
		Network test = null; 
		int batches = 1;
//		try {test = new Feedforward("Test");} 
//		catch (Exception e) {
			test = new Feedforward(new int[] {2, 10, 20, 30, 1}, initializer, activation, cost, optimizer);
//		}
		
		for (int j = 0; j < 10; j++) {
		
			for (int i = 0; i < inputs.length; i++) {
				float x = (float) (Math.random() * 2 - 1);
				float y = (float) (Math.random() * 2 - 1);

				inputs[i] = new Vector(new float[] {x, y});
				expected[i] = new Vector(new float[] {x*y});
			}
			
			test.train(inputs, expected, 100);
			System.out.println("0 * 1 = "+test.predict(new Vector(new float[] {0, 1})));
			System.out.println("0.5 * 0.5 = "+test.predict(new Vector(new float[] {0.5f, 0.5f})));
			System.out.println("0.5 * 1 = "+test.predict(new Vector(new float[] {0.5f, 1})));
			System.out.println("0 * 0 = "+test.predict(new Vector(new float[] {0, 0})));
			System.out.println("1 * 1 = "+test.predict(new Vector(new float[] {1, 1})));
			System.out.println("1 * 0.99 = "+test.predict(new Vector(new float[] {1, 0.99f})));
			System.out.println("0.333333 * 0.666666 = "+test.predict(new Vector(new float[] {0.333333f, 0.666666f})));
			System.out.println("2 * 1 = "+test.predict(new Vector(new float[] {2, 1})));
			System.out.println("0.2 * 1 = "+test.predict(new Vector(new float[] {0.2f, 1})));
			System.out.println("10 * 1 = "+test.predict(new Vector(new float[] {10, 1})));
			System.out.println("2 * 10 = "+test.predict(new Vector(new float[] {2, 10})));
			
			batches++;
		} 
//		test.deserialize(new ByteHelper(test.serialize()));
		Data<Network> d = new Data<>(test);
		try {
			d.setStorage(new Local("data/network.mdt"));
			d.save();
			Feedforward f = (Feedforward) d.load();
			System.out.println("TEST");
			System.out.println();
//			f.train(inputs, expected, 2);
			System.out.println("0 * 1 = "+f.predict(new Vector(new float[] {0, 1})));
			System.out.println("0.5 * 0.5 = "+f.predict(new Vector(new float[] {0.5f, 0.5f})));
			System.out.println("0.5 * 1 = "+f.predict(new Vector(new float[] {0.5f, 1})));
			System.out.println("0 * 0 = "+f.predict(new Vector(new float[] {0, 0})));
			System.out.println("1 * 1 = "+f.predict(new Vector(new float[] {1, 1})));
			System.out.println("1 * 0.99 = "+f.predict(new Vector(new float[] {1, 0.99f})));
			System.out.println("0.333333 * 0.666666 = "+f.predict(new Vector(new float[] {0.333333f, 0.666666f})));
			System.out.println("2 * 1 = "+f.predict(new Vector(new float[] {2, 1})));
			System.out.println("0.2 * 1 = "+f.predict(new Vector(new float[] {0.2f, 1})));
			System.out.println("10 * 1 = "+f.predict(new Vector(new float[] {10, 1})));
			System.out.println("2 * 10 = "+f.predict(new Vector(new float[] {2, 10})));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		test.saveNetwork("Test");
		
	}
}