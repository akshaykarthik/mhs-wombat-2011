package edu.mhs.wombat.game.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.player.Player;

public interface Entity {
	public EntityState getState();
	public void setState(EntityState es);
	
	public void init(GameStatus gs);
	public void update(StateBasedGame game, GameStatus gs, int delta);
	public void render(StateBasedGame game, Graphics g);
	
	public Hitbox getHitBox();
	public void collideWith(Entity b);
	public void playerCollide(Player a);
	
	public Vector2f getPos();

}
