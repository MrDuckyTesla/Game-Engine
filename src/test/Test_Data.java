package test;

import java.nio.ByteBuffer;

import engine.data.*;

import engine.data.storages.*;
import engine.data.compressions.*;
import engine.data.formats.*;
import engine.data.encryptions.*;

public class Test_Data {

	public static void main(String[] args) {
		
		Test1 test = new Test1();
		
		Data<Test1> d = new Data<>(test);  //.setCompression(null).setEncryption(null).setFormat(null).setStorage(null);
		d.save();
		
		
	}
	
	private static class Test1 implements Serializable<Test1> {
		private Test2 test = new Test2();
		private String s = "MrDuckyTesla";
		private float[] f = new float[] {3, 4, 5, 6, 7};
		private int i = 79134;
		@Override
		public String toString() {
			String s2 = s + i;
			for (float j : f) {s2 += j;}
			return s2 + this.test;
		}
		@Override
		public byte[] serialize(Test1 o) {
			return ByteHelper.mergeBytes(
				ByteHelper.toBytes(s),
				ByteHelper.toBytes(f), 
				ByteHelper.toBytes(i)
			);
		}
		@Override
		public Test1 deserialize(byte[] bytes, Class<?> type) {
			// TODO Auto-generated method stub
			return null;
		}
	} 
	private static class Test2 implements Serializable<Test2> {
		private String s = "Im recursive";
		private float[] f = new float[] {0, 1, 2};
		private int i = 999;
		@Override
		public String toString() {
			String s2 = s + i;
			for (float j : f) {s2 += j;}
			return s2;
		}
		@Override
		public byte[] serialize(Test2 o) {
			return ByteHelper.mergeBytes(
				ByteHelper.toBytes(s),
				ByteHelper.toBytes(f), 
				ByteHelper.toBytes(i)
			);
		}
		@Override
		public Test2 deserialize(byte[] bytes, Class<?> type) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	

}
