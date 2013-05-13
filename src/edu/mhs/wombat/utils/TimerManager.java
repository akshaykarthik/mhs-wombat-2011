package edu.mhs.wombat.utils;

import java.util.ArrayList;

public class TimerManager {
	public static ArrayList<Timer> timers = new ArrayList<Timer>();

	
	public static Timer makeTimer(float endTime, boolean startNow){
		Timer t = new Timer(endTime, startNow);
		TimerManager.addTimer(t);
		return t;
	}
	
	
	public static void addTimer(Timer t) {
		timers.add(t);
	}

	public static void removeTimer(Timer t) {
		timers.remove(t);
	}

	public static void update(float delta) {
		for (Timer t : timers) {
			if (t.isStarted()) {
				t.update(delta);
			}
		}
	}
}
