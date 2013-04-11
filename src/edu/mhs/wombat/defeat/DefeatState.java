package edu.mhs.wombat.defeat;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.StateUtils;

public class DefeatState extends BasicGameState {
	
	private GameContainer gc;
	private StateBasedGame gm;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gc = container;
		gm = game;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("GAME OVER", Globals.WIDTH/2-20, Globals.HEIGHT/2);

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}
	
	public void keyReleased(int key, char c){
		if(key==Input.KEY_ESCAPE){
			StateUtils.switchTo(gm, States.MENU);
		}
		
	}
	
	@Override
	public int getID() {
		return States.DEFEAT.ordinal();
	}

}
