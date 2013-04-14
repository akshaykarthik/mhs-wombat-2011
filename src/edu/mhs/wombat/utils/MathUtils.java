package edu.mhs.wombat.utils;

public class MathUtils {
	public static double clamp(double val, double min, double max) {
		return (val > max) ? max : // set to max if val > max
				(val < min) ? min : // set to min if val < min
						val; // otherwise return val
	}
	
	public static double loop(double val, double min, double max){
		return (val > max) ? min : // set to min if val > max
			(val < min) ? max : // set to max if val < min
					val; // otherwise return val
	}
}
