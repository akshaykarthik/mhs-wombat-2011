package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;

public class GameStatus {
	public Player player;
	public ArrayList<Entity> entities;
	private ArrayList<Entity> markForAdd;
	

	public GameStatus() {
		player = new Player();
		player.init(this);
		entities = new ArrayList<Entity>();
		markForAdd = new ArrayList<Entity>();
	}
	
	public void addEntityInstance(Entity ei){
		markForAdd.add(ei);
	}

	public void update(StateBasedGame game, int delta) {
		// logic updates
		
		ArrayList<Entity> markForRemoval = new ArrayList<Entity>();
		for(Entity e: entities){
			e.update(game, this, delta);
			if(e.getState() == EntityState.DEAD){
				markForRemoval.add(e);
			}
		}
		player.update(game, this, delta);
		
		for(Entity e: markForRemoval){
			e.close();
			entities.remove(e);
		}
		
		// collision updates
		for(Entity a: entities){
			for(Entity b: entities){
				if(a != b && a.getHitBox().intersects(b.getHitBox())){
					a.collideWith(b);
				}
			}
			
			if(a.getHitBox().intersects(player.getHitBox())){
				a.playerCollide(player);
				player.collideWith(a);
			}
		}
		
		entities.addAll(markForAdd);
		markForAdd.clear();
		
	}

	public void render(StateBasedGame game, Graphics g) {
		for(Entity e: entities){
			e.render(game, g);
			if(Globals.GAME_DEBUG){
				g.draw(e.getHitBox());
			}
		}
		player.render(game, g);
		
		if(Globals.GAME_DEBUG){
			g.draw(player.getHitBox());
		}
	}
}
