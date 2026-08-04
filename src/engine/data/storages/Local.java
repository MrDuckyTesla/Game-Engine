package engine.data.storages;

import engine.data.Storage;

public class Local implements Storage {
	
	private final String fileLocation;

	public Local(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@Override
	public void save(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] load(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
