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
		if (this.started) {
			this.current += delta;

			if (this.repeat && this.current > this.target) {
				this.current = 0;
			}
		}
	}

	public boolean isComplete() {
		return this.current > this.target;
	}

	public void reset() {
		this.current = 0;
		this.target = 0;
	}

	public boolean isRepeat() {
		return this.repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
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
