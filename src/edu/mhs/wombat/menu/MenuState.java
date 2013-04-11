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
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;

public class MenuState extends BasicGameState {
	private GameContainer gc;
	private StateBasedGame gm;
	private Image logo;

	private int current_selection = 0;
	private MenuOption[] options = { new MenuOption("Play", 160, 160),
			new MenuOption("Options", 260, 160),
			new MenuOption("Credits", 410, 160),
			new MenuOption("Quit", 540, 160) };

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
		g.setFont(ResourceManager.getFont("60"));
		g.drawString("Menu", 160, 100);

		g.setFont(ResourceManager.getFont("40"));

		for (int i = 0; i < options.length; i++) {
			if (current_selection == i)
				g.setColor(Color.red);
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

		if (key == Input.KEY_RIGHT) {
			current_selection++;
		}

		if (key == Input.KEY_LEFT) {
			current_selection--;
		}

		if (current_selection > options.length - 1)
			current_selection = 0;
		if (current_selection < 0)
			current_selection = options.length - 1;
		
		if(key == Input.KEY_ENTER){
			switch (current_selection) {
			case 0:
			
				break;
			case 1:
				break;
			case 2:
				StateUtils.switchTo(gm, States.CREDITS);
				break;
			case 3:
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
