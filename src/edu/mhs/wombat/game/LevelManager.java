package edu.mhs.wombat.game;

import org.newdawn.slick.state.StateBasedGame;

public class LevelManager {
	private final GameStatus gs;
	public float time = 0;
	public float difficulty = 1;

	public LevelManager(GameStatus gameStatus) {
		this.gs = gameStatus;
	}

	public void update(StateBasedGame game, GameStatus gameStatus, int delta) {
		this.time += delta;

	}

}
