package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;

public class GameStatus {
	public Player player;
	public ArrayList<Entity> entities;

	public GameStatus() {
		player = new Player();
		player.init(this);
		entities = new ArrayList<Entity>();
	}
	
	public void addEntityInstance(Entity ei){
		entities.add(ei);
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
		
		for(Entity e: markForRemoval)
			entities.remove(e);
		
		// collision updates
		for(Entity a: entities){
			for(Entity b: entities){
				if(a != b && Hitbox.collides(a, b)){
					a.collideWith(b);
				}
			}
			
			if(Hitbox.collides(a, player)){
				a.playerCollide(player);
				player.collideWith(a);
			}
		}
		
		
	}

	public void render(StateBasedGame game, Graphics g) {
		for(Entity e: entities){
			e.render(game, g);
			if(Globals.GAME_DEBUG){
				Hitbox.renderHitbox(e, g);
			}
		}
		player.render(game, g);
		
		if(Globals.GAME_DEBUG){
			Hitbox.renderHitbox(player, g);
		}
	}
}
