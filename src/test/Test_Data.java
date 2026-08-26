package test;

import java.io.IOException;

import engine.data.datatypes.FastData;
import engine.data.serializations.FastSerializable;
import engine.data.storages.*;
import engine.data.util.ByteHelper;
import engine.neural.util.Matrix;
import engine.neural.util.Vector;

public class Test_Data {

	public static void main(String[] args) {
		
		Test1 test = new Test1();
		
		FastData<Test1> d = new FastData<>(test);
		try {
//			d.save();
			Test1 test2 = d.load();
			System.out.println(test2);
		} catch (IOException e) {e.printStackTrace();} 
		
		Matrix m = new Vector(7);
		m.propagate();
//		System.out.println(m);
		FastData<Matrix> a = new FastData<>(m).setStorage(new Local("data/matrix test.mdt"));
		try {
//			a.save();
			System.out.println(a.load());
		} 
		catch (IOException e) {e.printStackTrace();}
		
//		System.out.println(test.deserialize(new ByteHelper(test.serialize())));
		
	}
	
	private static class Test1 implements FastSerializable<Test1> {
		private Test2 test = new Test2();
		private String s = "MrDuckyTesla";
		private float[] f = new float[] {3, 4, 5, 6, 7};
		private int i = 79134;
		@Override
		public String toString() {
			String s2 = s + " ";
			for (float j : f) {s2 += j + " ";}
			return this.test + s2 + i + " ";
		}
		@Override
		public byte[] serialize() {
			return ByteHelper.mergeBytes(
				ByteHelper.toBytes(test), 
				ByteHelper.toBytes(s), 
				ByteHelper.toBytes(f), 
				ByteHelper.toBytes(i)
			);
		}
		@Override
		public Test1 deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
			Test1 t = new Test1();
			t.test = b.readObject(new Test2());
			t.s = b.readString();
			t.f = b.readFloatArr();
			t.i = b.readInt();
			return t;
		}
		@Override
		public Test1[] getProtoArray(int length) {
			// TODO Auto-generated method stub
			return null;
		}
	} 
	
	private static class Test2 implements FastSerializable<Test2> {
		private String s = "Im recursive";
		private float[] f = new float[] {0, 1, 2};
		private int i = 999;
		@Override
		public String toString() {
			String s2 = s + " ";
			for (float j : f) {s2 += j + " ";}
			return s2 + i + " ";
		}
		@Override
		public byte[] serialize() {
			return ByteHelper.mergeBytes(
				ByteHelper.toBytes(s),
				ByteHelper.toBytes(f), 
				ByteHelper.toBytes(i)
			);
		}
		@Override
		public Test2 deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
			Test2 t = new Test2();
			t.s = b.readString();
			t.f = b.readFloatArr();
			t.i = b.readInt();
			return t;
		}
		@Override
		public Test2[] getProtoArray(int length) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	

}
