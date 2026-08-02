package engine.neural;

public interface Reconstructible {
	
	/**
	 * This interface is used to save objects as strings and to reconstruct them later
	 * @return a String in the utilizing getClass().getName() and then all following
	 * variables should be formatted with a new line before the raw value. Example:
	 * "return this.getClass().getName() + "\n" + this.param1 + "\n" + this.param2;"
	 */
	public default String getClassInfo() {
		return this.getClass().getName();
	}

}
