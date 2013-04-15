package edu.mhs.wombat.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.game.data.MonsterFactory;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;
import edu.mhs.wombat.utils.effects.Starfield;

public class GameState extends BasicGameState {
	private StateBasedGame gm;
	private Starfield bg;
	private GameStatus gs;
	private boolean paused = false;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gm = game;
		gs = new GameStatus();

	}

	public void enter(GameContainer container, StateBasedGame game) {
		bg = new Starfield(-10, -10);
		int NUMTEST = 100;
		for (int i = 0; i < NUMTEST; i++) {
			gs.addEntityInstance(MonsterFactory
					.newRandomWalkerMonster(150, 150));
		}

	}

	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bg.render(g);
		gs.render(game, g);
		if (paused)
			g.drawImage(ResourceManager.getImage("pause_screen"), 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (!paused)
			gs.update(game, delta);
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			if (paused)
				StateUtils.switchTo(gm, States.MENU);
			else
				pause();
		}
		if(paused && key != Input.KEY_ESCAPE){
			resume();
		}
		
		if (key == Input.KEY_F1) {
			Globals.GAME_DEBUG = !Globals.GAME_DEBUG;
		}
	}

	public void pause() {
		paused = true;
		bg.pause();
	}

	public void resume() {
		paused = false;
		bg.resume();
	}

	@Override
	public int getID() {
		return States.GAME.ordinal();
	}
}
