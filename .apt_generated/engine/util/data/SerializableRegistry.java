
		package engine.util.data;

		import java.util.HashMap;
		import java.util.Map;

		public final class SerializableRegistry {

			public static final Class<?>[] REGISTRY = {
				engine.neural.activations.Dynamic.class,
				engine.neural.activations.GELU.class,
				engine.neural.activations.Linear.class,
				engine.neural.activations.ReLU.class,
				engine.neural.activations.Sigmoid.class,
				engine.neural.activations.Softmax.class,
				engine.neural.activations.Tanh.class,
				engine.neural.costs.BinaryCrossEntropy.class,
				engine.neural.costs.MeanAbsoluteError.class,
				engine.neural.costs.MeanSquaredError.class,
				engine.neural.costs.RootMeanSquare.class,
				engine.neural.initializers.Glorot.class,
				engine.neural.initializers.Kaiming.class,
				engine.neural.initializers.RandomNormal.class,
				engine.neural.initializers.RandomUniform.class,
				engine.neural.networks.Convolutional.class,
				engine.neural.networks.DeepQ.class,
				engine.neural.networks.Evolutionary.class,
				engine.neural.networks.Feedforward.class,
				engine.neural.networks.Recurrent.class,
				engine.neural.networks.Transformer.class,
				engine.neural.optimizers.Adam.class,
				engine.neural.optimizers.AdamW.class,
				engine.neural.optimizers.RMSProp.class,
				engine.neural.optimizers.SGD.class,
				engine.neural.optimizers.SGDMomentum.class,
				engine.util.Matrix.class,
				engine.util.Vector.class,
				engine.util.data.compressions.Huffman.class,
				engine.util.data.compressions.LZW.class,
				engine.util.data.compressions.RLE.class,
				engine.util.data.compressions.Raw.class,
				engine.util.data.encryptions.Caesar.class,
				engine.util.data.encryptions.Hash.class,
				engine.util.data.encryptions.Raw.class,
				engine.util.data.encryptions.Symmetric.class,
				engine.util.data.encryptions.XOR.class,
				engine.util.data.storages.Local.class,
		};

		public static final Map<Class<?>, Short> IDS = new HashMap<>();

		static{
				IDS.put(engine.neural.activations.Dynamic.class, (short)0);
				IDS.put(engine.neural.activations.GELU.class, (short)1);
				IDS.put(engine.neural.activations.Linear.class, (short)2);
				IDS.put(engine.neural.activations.ReLU.class, (short)3);
				IDS.put(engine.neural.activations.Sigmoid.class, (short)4);
				IDS.put(engine.neural.activations.Softmax.class, (short)5);
				IDS.put(engine.neural.activations.Tanh.class, (short)6);
				IDS.put(engine.neural.costs.BinaryCrossEntropy.class, (short)7);
				IDS.put(engine.neural.costs.MeanAbsoluteError.class, (short)8);
				IDS.put(engine.neural.costs.MeanSquaredError.class, (short)9);
				IDS.put(engine.neural.costs.RootMeanSquare.class, (short)10);
				IDS.put(engine.neural.initializers.Glorot.class, (short)11);
				IDS.put(engine.neural.initializers.Kaiming.class, (short)12);
				IDS.put(engine.neural.initializers.RandomNormal.class, (short)13);
				IDS.put(engine.neural.initializers.RandomUniform.class, (short)14);
				IDS.put(engine.neural.networks.Convolutional.class, (short)15);
				IDS.put(engine.neural.networks.DeepQ.class, (short)16);
				IDS.put(engine.neural.networks.Evolutionary.class, (short)17);
				IDS.put(engine.neural.networks.Feedforward.class, (short)18);
				IDS.put(engine.neural.networks.Recurrent.class, (short)19);
				IDS.put(engine.neural.networks.Transformer.class, (short)20);
				IDS.put(engine.neural.optimizers.Adam.class, (short)21);
				IDS.put(engine.neural.optimizers.AdamW.class, (short)22);
				IDS.put(engine.neural.optimizers.RMSProp.class, (short)23);
				IDS.put(engine.neural.optimizers.SGD.class, (short)24);
				IDS.put(engine.neural.optimizers.SGDMomentum.class, (short)25);
				IDS.put(engine.util.Matrix.class, (short)26);
				IDS.put(engine.util.Vector.class, (short)27);
				IDS.put(engine.util.data.compressions.Huffman.class, (short)28);
				IDS.put(engine.util.data.compressions.LZW.class, (short)29);
				IDS.put(engine.util.data.compressions.RLE.class, (short)30);
				IDS.put(engine.util.data.compressions.Raw.class, (short)31);
				IDS.put(engine.util.data.encryptions.Caesar.class, (short)32);
				IDS.put(engine.util.data.encryptions.Hash.class, (short)33);
				IDS.put(engine.util.data.encryptions.Raw.class, (short)34);
				IDS.put(engine.util.data.encryptions.Symmetric.class, (short)35);
				IDS.put(engine.util.data.encryptions.XOR.class, (short)36);
				IDS.put(engine.util.data.storages.Local.class, (short)37);
			}
		}
