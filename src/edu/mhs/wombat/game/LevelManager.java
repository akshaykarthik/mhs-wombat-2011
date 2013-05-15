package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.monsters.Portal;
import edu.mhs.wombat.utils.Globals;

public class LevelManager {
	public float time = 0;
	public float difficulty = 0;

	private ArrayList<Portal> portal = new ArrayList<Portal>();

	public LevelManager() {

	}

	public void update(StateBasedGame game, GameStatus gameStatus, int delta) {
		this.time += delta;
		if (portal == null || portal.isEmpty()/* state == EntityState.DEAD */) {
			difficulty++;
			for (int i = 0; i < difficulty / 4; i++) {
				float portalx = (float) Math.random() * Globals.ARENA_WIDTH;
				float portaly = (float) Math.random() * Globals.ARENA_HEIGHT;
				portal.add(new Portal(portalx, portaly, difficulty));
				for (Portal a : portal) {
					gameStatus.addEntity(a);
				}
			}
		}

	}

}
