package edu.mhs.wombat;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.credits.CreditsState;
import edu.mhs.wombat.menu.MenuState;
import edu.mhs.wombat.utils.Globals;

public class Wombat extends StateBasedGame {

	/**
	 * Create a new test
	 */
	public Wombat() {
		super("Wombat");
	}

	/**
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	public void initStatesList(GameContainer container) {
		addState(new MenuState());
		addState(new CreditsState());
		// addState();
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv
	 *            The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Wombat());
			container.setDisplayMode(Globals.WIDTH, Globals.HEIGHT, false);
			container.setTargetFrameRate(Globals.TARGET_FPS);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}