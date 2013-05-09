package edu.mhs.wombat.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.StateUtils;

public class HighState extends BasicGameState {
	private StateBasedGame gm;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			StateUtils.switchTo(this.gm, States.MENU);
		}
	}

	@Override
	public int getID() {
		return States.HIGHSCORE.ordinal();
	}

}
