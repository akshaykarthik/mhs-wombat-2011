package edu.mhs.wombat.utils.data;

public class HighScoreSystem {
	private int current_score = 0;
	private int multiplier = 1;

	public int getScore() {
		return current_score;
	}

	public void reset() {
		current_score = 0;
		multiplier = 1;
	}

	public void addPoints(int i) {
		current_score += multiplier * i;
	}

}
