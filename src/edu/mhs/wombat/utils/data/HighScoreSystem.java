package edu.mhs.wombat.utils.data;

public class HighScoreSystem {
	private int current_score = 0;
	private int multiplier = 1;

	public int getMultiplier() {
		return this.multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getScore() {
		return this.current_score;
	}

	public void addPoints(int i) {
		this.current_score += this.multiplier * i;
	}

	public void reset() {
		this.current_score = 0;
		this.multiplier = 1;
	}

}
