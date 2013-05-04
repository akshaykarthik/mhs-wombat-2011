package edu.mhs.wombat.utils.data;

public class HighScoreSystem {
	private int current_score = 0;
	private int multiplier = 1;

	public int getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getScore() {
		return current_score;
	}

	public void addPoints(int i) {
		current_score += multiplier * i;
	}

	public void reset() {
		current_score = 0;
		multiplier = 1;
	}

}
