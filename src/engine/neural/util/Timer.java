package engine.neural.util;

/**
 * Simple class that can be used to track how long something takes
 */
public class Timer {
	
	/**
	 * Keeps track of initial time
	 */
	private static long time = -1;
	/**
	 * Tracks if milliseconds or nanoseconds are being used
	 */
	private static boolean millis = true;
	
	/**
	 * Starts the timer in either milliseconds or nanoseconds
	 * @param millis boolean representing if timer is milliseconds or not
	 */
	public static void start(boolean millis) {
		Timer.time = millis? System.currentTimeMillis() : System.nanoTime();
		Timer.millis = millis;
	}
	
	/**
	 * Starts the timer in milliseconds
	 */
	public static void start() {Timer.start(true);}

	/**
	 * Ends the timer and returns now long its been
	 * @return total time elapsed, otherwise -1 if end() is called before start()
	 */
	public static long end() {
		if (Timer.time != -1) {  // Make sure timer was started
			long deltaTime = (millis? System.currentTimeMillis() : System.nanoTime()) - Timer.time;
			Timer.time = -1; return deltaTime;
		} return -1;
	}

}
