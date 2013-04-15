package edu.mhs.wombat.game.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;

public interface Entity {
	public void init(GameStatus gs);
	public void update(StateBasedGame game, EntityInstance ei, int delta);
	public void render(StateBasedGame game, EntityInstance ei, Graphics g);
	public void collideWith(EntityInstance a, Entity ba, EntityInstance bb);

}
