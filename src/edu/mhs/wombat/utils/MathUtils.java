package edu.mhs.wombat.utils;

public class MathUtils {
	public static float clamp(float val, float min, float max) {
		return (val > max) ? max : // set to max if val > max
				(val < min) ? min : // set to min if val < min
						val; // otherwise return val
	}

	public static float loop(float val, float min, float max) {
		return (val > max) ? min : // set to min if val > max
				(val < min) ? max : // set to max if val < min
						val; // otherwise return val
	}
}
