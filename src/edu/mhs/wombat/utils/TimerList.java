package edu.mhs.wombat.utils;

import java.util.ArrayList;

public class TimerList {
	private ArrayList<Timer> timers;

	public TimerList() {
		timers = new ArrayList<Timer>();
	}

	public void addTimer(Timer a) {
		timers.add(a);
	}

	public void removeTimer(Timer timer) {
		timers.remove(timer);
	}

	public void resetAll() {
		for (Timer t : timers) {
			t.reset();
		}
	}

	public int size() {
		return timers.size();
	}

	public boolean allDefault() {
		for (Timer t : timers) {
			if (t.percent() != 0) {
				return false;
			}
		}
		return true;
	}

	public boolean allValue(float value) {
		for (Timer t : timers) {
			if (t.percent() != value) {
				return false;
			}
		}
		return true;
	}

	public boolean anyActive() {
		for (Timer t : timers) {
			if (t.isStarted()) {
				return true;
			}
		}
		return false;
	}
	public boolean anyComplete() {
		for(Timer t : timers){
			if(t.isComplete()){
				return true;
			}
		}
		return false;
	}

	public Timer get(int a) {
		try {
			return timers.get(a);
		} catch (Exception e) {
			return new Timer(0);
		}
	}

}
