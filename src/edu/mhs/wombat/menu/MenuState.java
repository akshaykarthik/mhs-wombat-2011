package edu.mhs.wombat.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;
import edu.mhs.wombat.utils.effects.Starfield;

public class MenuState extends BasicGameState {
	private GameContainer gc;
	private StateBasedGame gm;
	private Starfield stars;

	private int current_selection = 0;
	private MenuOption[] options = { // Menu options
			new MenuOption("Play", 160, 200), //0
			new MenuOption("Options", 160, 300),//1
			new MenuOption("Credits", 160, 400),//2
			new MenuOption("High Scores", 160, 500),//3
			new MenuOption("Quit", 160, 600) };//4

	private class MenuOption {
		public String name;
		public float x;
		public float y;

		public MenuOption(String name, float ix, float iy) {
			this.name = name;
			this.x = ix;
			this.y = iy;
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gc = container;
		gm = game;
	}

	public void enter(GameContainer container, StateBasedGame game) {
		stars = new Starfield(-10, 0);
	}

	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// g.drawImage(logo, 0, 0);
		stars.render(g);
		g.setFont(ResourceManager.getFont("font100"));
		g.drawString("Menu", 160, 20);

		g.setFont(ResourceManager.getFont("font40"));

		for (int i = 0; i < options.length; i++) {
			if (current_selection == i) {
				g.setColor(Color.red);
				g.draw(new Polygon(new float[] { options[i].x - 5, // x1
						options[i].y + 25, // y1
						options[i].x - 10, // x2
						options[i].y + 30, // y2
						options[i].x - 10, // x3
						options[i].y + 20 // y3
						}));
			}
			g.drawString(options[i].name, options[i].x, options[i].y);
			g.setColor(Color.white);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

	}

	public void keyReleased(int key, char c) {

		if (key == Input.KEY_4) {
			StateUtils.switchTo(gm, States.DEFEAT);
		}

		if (key == Input.KEY_5) {
			StateUtils.switchTo(gm, States.VICTORY);
		}
		
		if (key == Input.KEY_6) {
			StateUtils.switchTo(gm, States.HIGHSCORE);
		}
		
		if (key == Input.KEY_RIGHT || key == Input.KEY_DOWN) {
			current_selection++;
		}

		if (key == Input.KEY_LEFT || key == Input.KEY_UP) {
			current_selection--;
		}

		if (current_selection > options.length - 1)
			current_selection = 0;
		if (current_selection < 0)
			current_selection = options.length - 1;

		if (key == Input.KEY_ENTER) {
			switch (current_selection) {
			case 0:
				StateUtils.switchTo(gm,  States.GAME);
				break;
			case 1:
				StateUtils.switchTo(gm, States.OPTIONS);
				break;
			case 2:
				StateUtils.switchTo(gm, States.CREDITS);
				break;
			case 3:
				StateUtils.switchTo(gm,  States.HIGHSCORE);
				break;
			case 4:
				gc.exit();
				break;
			default:
				break;
			}
		}

	}

	@Override
	public int getID() {
		return States.MENU.ordinal();
	}

}
