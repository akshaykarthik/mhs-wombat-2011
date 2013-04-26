package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.data.HighScoreSystem;

public class GameStatus {
	public Player player;
	public ArrayList<Entity> entities;
	public HighScoreSystem scores;
	private ArrayList<Entity> markForAdd;
	private ArrayList<Entity> markForRemove;

	public GameStatus() {
		player = new Player();
		player.init(this);
		
		scores = new HighScoreSystem();
		entities = new ArrayList<Entity>();
		markForAdd = new ArrayList<Entity>();
		markForRemove = new ArrayList<Entity>();
	}

	public void addEntity(Entity ei) {
		markForAdd.add(ei);
	}

	public void update(StateBasedGame game, int delta) {
		player.update(game, this, delta);

		// collision updates
		for (Entity a : entities) {
			a.update(game, this, delta);
			if (a.getState() == EntityState.DEAD) {
				markForRemove.add(a);
			}

			for (Entity b : entities) {
				if (a != b && a.getHitBox().intersects(b.getHitBox())) {
					a.collideWith(b);
				}
			}

			if (a.getHitBox().intersects(player.getHitBox())) {
				a.playerCollide(player);
				player.collideWith(a);
			}
		}

		entities.addAll(markForAdd);
		markForAdd.clear();

		for (Entity e : markForRemove) {
			e.close(this);
			entities.remove(e);
		}
		markForRemove.clear();

		
	}

	public void render(StateBasedGame game, Graphics g) {
		for (Entity e : entities) {
			e.render(game, g);
			if (Globals.GAME_DEBUG) {
				g.draw(e.getHitBox());
			}
		}
		player.render(game, g);

		if (Globals.GAME_DEBUG) {
			g.draw(player.getHitBox());
		}
	}
}
