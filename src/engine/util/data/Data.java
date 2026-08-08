package engine.util.data;

import java.io.IOException;

import engine.util.ByteHelper;

public class Data<T extends Serializable<T>> implements Serializable<Data<T>> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	
	public Data(T object, Storage s, Compression c, Encryption e) {
		this.object = object; this.storage = s; 
		this.compression = c; this.encryption = e;
	}

	public Data(T object) {
		this.object = object;
	}
	
	public Data(Storage s, Compression c, Encryption e) throws IOException, ReflectiveOperationException {
		this.storage = s; this.compression = c; this.encryption = e;
		byte[] bytes = this.storage.load();
		bytes = this.encryption.decrypt(bytes);
		bytes = this.compression.decompress(bytes);
		this.object = this.deserialize(new ByteHelper(bytes)).get();
	}
	
	public Data() throws IOException, ReflectiveOperationException {
		this.checkNullExists();
		byte[] bytes = this.storage.load();
		bytes = this.encryption.decrypt(bytes);
		bytes = this.compression.decompress(bytes);
		Data<T> d = this.deserialize(new ByteHelper(bytes));
		this.storage = d.storage; this.compression = d.compression;
		this.encryption = d.encryption; this.object = d.object;
	}
	
	public Data<T> setStorage(Storage s) {this.storage = s; return this;}
	public Data<T> setCompression(Compression s) {this.compression = s; return this;}
	public Data<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
	public void save() throws IOException {
		this.checkNullExists();
		byte[] bytes = this.serialize();
		bytes = this.compression.compress(bytes);
		bytes = this.encryption.encrypt(bytes);
		this.storage.save(bytes);
	}
	
	public T load() throws IOException, ReflectiveOperationException {
		this.checkNullExists();
		byte[] bytes = this.storage.load();
		bytes = this.encryption.decrypt(bytes);
		bytes = this.compression.decompress(bytes);
		
		return this.deserialize(new ByteHelper(bytes)).object;
	}
	
	public T get() {return this.object;}
	
	private void checkNullExists() {
		if (this.storage == null) {this.storage = new engine.util.data.storages.Local("data/unnamed.mdt");}
		if (this.compression == null) {this.compression = new engine.util.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.util.data.encryptions.Raw();}
	}

	@Override
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.object),
			ByteHelper.toBytes(this.storage),
			ByteHelper.toBytes(this.compression),
			ByteHelper.toBytes(this.encryption)
		);
	}

	@Override
	public Data<T> deserialize(ByteHelper bytes) throws ReflectiveOperationException {
		return new Data<T> (
			bytes.readObj(),
			bytes.readObj(),
			bytes.readObj(),
			bytes.readObj()
		);
	}

}
