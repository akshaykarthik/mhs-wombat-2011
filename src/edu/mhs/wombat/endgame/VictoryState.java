package edu.mhs.wombat.endgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;

public class VictoryState extends BasicGameState {

	private GameContainer gc;
	private StateBasedGame gm;
	private Image background;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gc = container;
		gm = game;

	}

	public void enter(GameContainer container, StateBasedGame game) {
		background = ResourceManager.getImage("background_victory");
	}

	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		g.setFont(ResourceManager.getFont("font100"));
		g.drawString("Victory!", Globals.WIDTH / 2 - 20, Globals.HEIGHT / 2);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			StateUtils.switchTo(gm, States.MENU);
		}
	}

	@Override
	public int getID() {
		return States.VICTORY.ordinal();
	}

}
