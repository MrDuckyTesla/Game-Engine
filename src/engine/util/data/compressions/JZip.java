package engine.util.data.compressions;

import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import engine.util.ByteHelper;
import engine.util.data.Compression;
import engine.util.data.Serializable;

public class JZip implements Compression {

	private int strategy, before = 0, after = 0;
	
	public JZip() {
		this.strategy = Deflater.DEFAULT_STRATEGY;
	}
	
	public JZip(int strategy) {
		this.strategy = strategy;
	}

	@Override
	public byte[] compress(byte[] bytes) {
		this.before = bytes.length;
		Deflater d = new Deflater();
		d.setStrategy(this.strategy);
		d.setInput(bytes); d.finish();
		byte[] c = new byte[bytes.length]; 
		this.after = d.deflate(c); d.end();
		return Arrays.copyOf(c, this.after);
	}

	@Override
	public byte[] decompress(byte[] bytes) {
		Inflater i = new Inflater();
		byte[] b = new byte[this.before];
		i.setInput(bytes); 
		try {i.inflate(b); return b;} 
		catch (DataFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getSavedSpace() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compression deserialize(ByteHelper b, Serializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compression[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
