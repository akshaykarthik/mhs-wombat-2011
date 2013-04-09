package edu.mhs.wombat.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;

import edu.mhs.wombat.States;


public class MenuState extends BasicGameState {
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
		// TODO Auto-generated method stub
		g.drawString("Test State: Menu", 160, 160);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
	}
	
	public void keyReleased(int key, char c) {

		if (key == Input.KEY_2) {
			GameState target = gm.getState(States.CREDITS.ordinal());

			gm.enterState(States.CREDITS.ordinal(), new EmptyTransition(), new EmptyTransition());
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.MENU.ordinal();
	}

}
