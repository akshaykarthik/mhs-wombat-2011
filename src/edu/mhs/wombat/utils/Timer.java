package edu.mhs.wombat.utils;

public class Timer {
	private float current;
	private boolean started;
	private float target;

	public Timer(float target) {
		this(target, true);
	}

	public Timer(float target, boolean startImmediately) {
		this.target = target;
		this.started = startImmediately;
	}

	public void update(float delta) {
		if (this.started) {
			this.current += delta;
		}
	}

	public boolean isComplete() {
		return this.current > this.target;
	}

	public void reset() {
		this.started = false;
		this.current = 0;
	}

	public void resetAndStart() {
		this.started = true;
		this.current = 0;
	}

	public float percent() {
		if (target != 0) {
			return current / target;
		} else {
			return -1;
		}
	}

	public boolean isStarted() {
		return this.started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public float getTarget() {
		return this.target;
	}

	public void setTarget(float target) {
		this.target = target;
	}
}
