package edu.mhs.wombat.game;

import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.monsters.Portal;
import edu.mhs.wombat.utils.Globals;

public class LevelManager {
	public float time = 0;
	public float difficulty = 0;

	private Portal portal;

	public LevelManager() {

	}

	public void update(StateBasedGame game, GameStatus gameStatus, int delta) {
		this.time += delta;
		if (portal == null || portal.state == EntityState.DEAD) {
			difficulty++;
			float portalx = (float) Math.random() * Globals.ARENA_WIDTH;
			float portaly = (float) Math.random() * Globals.ARENA_HEIGHT;
			portal = new Portal(portalx, portaly, difficulty);
			gameStatus.addEntity(portal);
		}

	}

}
