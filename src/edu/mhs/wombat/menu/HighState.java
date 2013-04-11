package edu.mhs.wombat.menu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int[] scores = new int[10];
		try {
			Scanner f = new Scanner(new FileReader("Highscores.txt"));
			int i = 0;
			while (f.hasNext() && i < 10) {
				scores[i] = Integer.parseInt(f.nextLine().substring(
						f.nextLine().indexOf('=')));
				g.drawString("Highscore " + i + "\t" + scores[i], 225, 225 + 50 * i);
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			StateUtils.switchTo(gm, States.MENU);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return States.HIGHSCORE.ordinal();
	}

}
