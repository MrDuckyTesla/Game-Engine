package engine.data;

public class Data<T extends Serializable> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	private Format format = null;
	
	public Data(T object, Storage s, Compression c, Encryption e, Format f) {
		this.object = object;
	}

	public Data(T object) {
		this.object = object;
	}
	
	public Data(Class<T> type) {
		this.object = this.load(type);
	}
	
	public Data<T> setStorage(Storage s) {this.storage = s; return this;}
	public Data<T> setFormat(Format s) {this.format = s; return this;}
	public Data<T> setCompression(Compression s) {this.compression = s; return this;}
	public Data<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
	public void save() {
		if (this.storage == null) {this.storage = new engine.data.storages.Local("file location");}
		if (this.compression == null) {this.compression = new engine.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.data.encryptions.Raw();}
		if (this.format == null) {this.format = new engine.data.formats.Raw();}
		
		byte[] bytes = object.serialize(this.object);
		bytes = this.compression.compress(bytes);
		bytes = this.encryption.encrypt(bytes);
		this.storage.save(bytes);
	}
	
	public T load(Class<T> type) {
		return null;
	}

}
