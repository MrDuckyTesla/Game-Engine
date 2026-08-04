package engine.data.formats;

import engine.data.Format;

public class Raw implements Format {

	@Override
	public String format(byte[] bytes) {String s = ""; for (byte b : bytes) {s += b;} return s;}

}
