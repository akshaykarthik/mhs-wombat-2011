package edu.mhs.wombat.game;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.monsters.Boss;
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
			boolean bosses = false;
			for (Entity es : gameStatus.entities) {
				if (es instanceof Boss) {
					bosses = true;
				}
			}
			if (!bosses) {
				difficulty++;
				for (int i = 0; i < difficulty / 4; i++) {
					// if (difficulty % 3 == 0) {
					float portalx = (float) Math.random() * Globals.ARENA_WIDTH;
					float portaly = (float) Math.random()
							* Globals.ARENA_HEIGHT;
					portal.add(new Portal(portalx, portaly, difficulty));
					// }
					for (Portal a : portal) {
						gameStatus.addEntity(a);
					}
				}
			}
		}
		for (Iterator<Portal> iterator = portal.iterator(); iterator.hasNext();) {
			Portal p = iterator.next();
			if (p.state == EntityState.DEAD) {
				iterator.remove();
			}
		}
	}
}
