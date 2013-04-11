package edu.mhs.wombat.utils;

public class Timer {
	private float current;
	private boolean repeat;
	private boolean started;
	private float target;

	public Timer(float target) {
		this(target, true, false);
	}

	public Timer(float target, boolean startImmediately) {
		this(target, startImmediately, false);
	}

	public Timer(float target, boolean startImmediately, boolean repeat) {
		this.target = target;
		this.started = startImmediately;
		this.repeat = repeat;
	}

	public void update(int delta) {
		if (started) {
			current += delta;

			if (repeat && current > target) {
				current = 0;
			}
		}
	}

	public boolean isComplete() {
		return current > target;
	}

	public void reset() {
		current = 0;
		target = 0;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public float getTarget() {
		return target;
	}

	public void setTarget(float target) {
		this.target = target;
	}
}
