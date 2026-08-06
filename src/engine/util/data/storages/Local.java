package engine.util.data.storages;

import java.io.IOException;
import java.nio.file.*;

import engine.util.ByteHelper;
import engine.util.data.Storage;

public class Local implements Storage {
	
	private final Path path;

	public Local(String fileLocation) {
		this.path = Paths.get(fileLocation);
	}

	@Override
	public void save(byte[] bytes) throws IOException {
		Files.write(this.path, bytes);
	}

	@Override
	public byte[] load() throws IOException {
		return Files.readAllBytes(this.path);
	}

	@Override
	public byte[] serialize() {
		return ByteHelper.toBytes(this.path.toString());
	}

	@Override
	public Storage deserialize(ByteHelper bytes) throws ReflectiveOperationException {
		return new Local(bytes.readString());
	}

}
