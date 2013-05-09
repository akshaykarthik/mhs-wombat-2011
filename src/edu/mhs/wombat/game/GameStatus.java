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
	private final ArrayList<Entity> markForAdd;
	private final ArrayList<Entity> markForRemove;

	public GameStatus() {
<<<<<<< HEAD
		this.player = new Player();
		this.player.init(this);

		this.scores = new HighScoreSystem();
		this.entities = new ArrayList<Entity>();
		this.markForAdd = new ArrayList<Entity>();
		this.markForRemove = new ArrayList<Entity>();

=======
		player = new Player();
		player.init(this);

		scores = new HighScoreSystem();
		entities = new ArrayList<Entity>();
		markForAdd = new ArrayList<Entity>();
		markForRemove = new ArrayList<Entity>();
		
		QuadGrid baseGrid = new QuadGrid(Globals.Arena_Size, this);
		grids.addAll(baseGrid.subdivideN(4));
>>>>>>> tset
	}

	public void addEntity(Entity ei) {
		this.markForAdd.add(ei);
	}

	public void update(StateBasedGame game, int delta) {
<<<<<<< HEAD
		this.player.update(game, this, delta);
		for (Entity a : this.entities) {
=======
		player.update(game, this, delta);
		
		// collision updates
		
		
		//grids.addAll(baseGrid.subdivideN(4));
		
		for (Entity a : entities) {
>>>>>>> tset
			a.update(game, this, delta);
			if (a.getState() == EntityState.DEAD) {
				this.markForRemove.add(a);
			}
<<<<<<< HEAD
=======
			for(QuadGrid aGrid: grids){
				aGrid.removeEntities();
				aGrid.addEntities(entities);
				for(Entity b: aGrid.getEntities()){
					if(a != b && a.getHitBox().intersects(b.getHitBox())){
						a.collideWith(b);
					}
				}
			}
			
>>>>>>> tset

			for (Entity b : this.entities) {
				if (a != b && a.getHitBox().intersects(b.getHitBox())) {
					a.collideWith(b);
				}
			}

			if (a.getHitBox().intersects(this.player.getHitBox())) {
				a.playerCollide(this.player);
				this.player.collideWith(a);
			}
		}

		this.entities.addAll(this.markForAdd);
		this.markForAdd.clear();

		for (Entity e : this.markForRemove) {
			e.close(this);
			this.entities.remove(e);
		}
		this.markForRemove.clear();

	}

	public void render(StateBasedGame game, Graphics g) {
		for (Entity e : this.entities) {
			e.render(game, g);
			if (Globals.GAME_DEBUG) {
				g.draw(e.getHitBox());
			}
		}
		this.player.render(game, g);

		if (Globals.GAME_DEBUG) {
			g.draw(this.player.getHitBox());
		}
	}
}
