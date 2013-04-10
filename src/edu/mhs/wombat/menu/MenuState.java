package edu.mhs.wombat.menu;

import org.newdawn.slick.Color;
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

public class MenuState extends BasicGameState {
	private GameContainer gc;
	private StateBasedGame gm;
	private Image logo;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gc = container;
		gm = game;

	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		System.out.println("ENTERED STATE MENU");
		logo = ResourceManager.getImage("logo");
	}

	public void leave(GameContainer container, StateBasedGame game) {
		System.out.println("LEAVING STATE MENU");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(logo, 0, 0);
		g.setColor(Color.yellow);
		g.drawString("Test State: Menu", 160, 160);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_2) {
			StateUtils.switchTo(gm, States.CREDITS);
		}

	}

	@Override
	public int getID() {

		return States.MENU.ordinal();
	}

}
