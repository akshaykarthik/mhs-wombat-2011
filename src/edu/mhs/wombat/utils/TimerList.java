package edu.mhs.wombat.utils;

import java.util.ArrayList;

public class TimerList {
	private ArrayList<Timer> timers;
	
	public TimerList(int num){
		timers = new ArrayList<Timer>(num);
		for(Timer t: timers){
			t.setTarget(0);
		}
	}
	
	public void addTimer(Timer a){
		timers.add(a);
	}
	
	public void removeTimer(Timer timer){
		timers.remove(timer);
	}
	
	public void resetAll(){
		for(Timer t: timers){
			t.reset();
		}
	}
	
	public int size(){
		return timers.size();
	}
	
	public boolean allDefault(){
		for(Timer t: timers){
			if(t.percent() != 0){
				return false;
			}
		}
		return true;
	}
	
	public boolean allValue(float value){
		for(Timer t: timers){
			if(t.percent() != value){
				return false;
			}
		}
		return true;
	}
	

}
