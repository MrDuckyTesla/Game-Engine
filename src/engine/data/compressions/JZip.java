package engine.data.compressions;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import engine.data.Compression;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

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
		d.setInput(bytes, 4, bytes.length-4); 
		d.finish(); byte[] c = new byte[bytes.length]; 
		this.after = d.deflate(c); d.end();
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.before),
			Arrays.copyOf(c, this.after)
		);
	}

	@Override
	public byte[] decompress(byte[] bytes) {
		this.before = ByteBuffer.wrap(bytes).getInt();
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
		return this.after - this.before;
	}
	
	@Override
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.strategy),
			ByteHelper.toBytes(this.before),
			ByteHelper.toBytes(this.after)
		);
	}

	@Override
	public Compression deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		JZip j = new JZip(b.readInt());
		j.before = b.readInt();
		j.after = b.readInt();
		return j;
	}

	@Override
	public Compression[] getProtoArray(int length) {
		return new JZip[length];
	}

}
