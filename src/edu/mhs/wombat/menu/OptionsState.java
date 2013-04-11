package edu.mhs.wombat.menu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;
import edu.mhs.wombat.utils.effects.Starfield;

public class OptionsState extends BasicGameState {
	private StateBasedGame gm;
	private Starfield bg;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gm = game;

	}

	public void enter(GameContainer container, StateBasedGame game) {
		bg = new Starfield(0, 10);
	}

	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bg.render(g);
		// Options Data
		Properties options = new Properties();
		try {
			options.load(new FileReader("Options.ini"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}

		// **********************
		g.setFont(ResourceManager.getFont("font60"));
		g.drawString("Options", 160, 160);
		g.setFont(ResourceManager.getFont("font40"));
		String user = options.getProperty("Username","AAA");
		g.drawString("User : " + user, 160, 255);
		g.drawString("Volume : " + options.getProperty("Volume", "100"), 160, 305);
		//Not currently reading from file.  Think it's Options.ini.txt right now
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
		return States.OPTIONS.ordinal();
	}
}
