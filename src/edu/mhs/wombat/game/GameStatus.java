package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.core.EntityMapper;
import edu.mhs.wombat.game.data.player.Player;

public class GameStatus {
	public Player player;
	public ArrayList<EntityInstance> entities;

	public GameStatus() {
		player = new Player();
		player.init(this);
		entities = new ArrayList<EntityInstance>();
	}
	
	public void addEntityInstance(EntityInstance ei){
		entities.add(ei);
	}

	public void update(StateBasedGame game, int delta) {
		for(EntityInstance e: entities){
			EntityMapper.getEntity(e).update(game, this, e, delta);
		}
		player.update(game, this, delta);
	}

	public void render(StateBasedGame game, Graphics g) {
		for(EntityInstance e: entities){
			EntityMapper.getEntity(e).render(game, e, g);
		}
		player.render(game, g);
	}
}
