package edu.mhs.wombat.credits;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.StateUtils;

public class CreditsState extends BasicGameState {
	private GameContainer gc;
	private StateBasedGame gm;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gc = container;
		gm = game;
		
	}

	public void enter(GameContainer container, StateBasedGame game) {
		System.out.println("ENTERED STATE CREDITS");
		
	}

	public void leave(GameContainer container, StateBasedGame game) {
		System.out.println("LEAVING STATE CREDITS");
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Test State: Credits", 160, 160);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_2) {
			StateUtils.switchTo(gm, States.MENU);
		}

	}

	@Override
	public int getID() {
		return States.CREDITS.ordinal();
	}

}
