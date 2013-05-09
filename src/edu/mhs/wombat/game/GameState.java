package edu.mhs.wombat.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.game.data.monsters.PullMonster;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonster;
import edu.mhs.wombat.game.data.monsters.ShooterMonster;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;

public class GameState extends BasicGameState {
	private StateBasedGame gm;
	private GameStatus gs;
	private final HUDSystem hs = new HUDSystem();
	private boolean paused = false;
	private boolean firstTime = true;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.gm = game;
		this.gs = new GameStatus();

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		if (this.firstTime) {
<<<<<<< HEAD
			int NUMTEST = 10;
=======
			int NUMTEST = 4;
>>>>>>> Pull Monsters + HP for Shooters
			int sqrtNumTest = (int) Math.sqrt(NUMTEST);
			for (int i = 0; i < sqrtNumTest; i++) {
				for (int j = 0; j < sqrtNumTest; j++) {
					this.gs.addEntity(new PullMonster(i
							* Globals.ARENA_WIDTH / sqrtNumTest, j
							* Globals.ARENA_HEIGHT / sqrtNumTest));
				}
			}
			this.firstTime = false;
		}

	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setAntiAlias(!Globals.GAME_DEBUG);

		// draw with camera
		Camera.preDraw(g, this.gs);

		g.drawImage(ResourceManager.getImage("background2"), -25, -15);

		// camera debugging
		if (Globals.GAME_DEBUG)
			this.hs.debugCamRender(game, this.gs, g);

		this.gs.render(game, g);
		this.hs.camRender(game, this.gs, g);

		Camera.postDraw(g, this.gs);
		this.hs.render(game, this.gs, g);

		// non-camera debugging
		if (Globals.GAME_DEBUG)
			this.hs.debugRender(game, this.gs, g);

		// pause screen
		if (this.paused)
			g.drawImage(ResourceManager.getImage("pause_screen"), 0, 0);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		this.hs.update(game, this.gs, delta);
		if (!this.paused)
			this.gs.update(game, delta);
	}

	@Override
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			if (this.paused)
				StateUtils.switchTo(this.gm, States.MENU);
			else
				this.pause();
		}
		if (this.paused && key != Input.KEY_ESCAPE) {
			this.resume();
		}

		if (key == Input.KEY_F1) {
			Globals.GAME_DEBUG = !Globals.GAME_DEBUG;
		}

		if (key == Input.KEY_F6) {
			this.gs.player.energy -= 10;
		}

		if (key == Input.KEY_F8) {
			this.gs.player.health += 10;
		}
		if (key == Input.KEY_F7) {
			this.gs.player.health -= 10;
		}

		if (key == Input.KEY_F12) {
			this.gs.entities.clear();
		}
		if (key == Input.KEY_F11) {
			for (int i = 0; i < 100; i++) {
				this.gs.addEntity(new RandomWalkerMonster(150, 150));
			}
		}
	}

	public void pause() {
		this.paused = true;
	}

	public void resume() {
		this.paused = false;
	}

	@Override
	public int getID() {
		return States.GAME.ordinal();
	}
}
