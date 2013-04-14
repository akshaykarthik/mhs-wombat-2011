package edu.mhs.wombat.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.core.EntityMapper;
import edu.mhs.wombat.game.data.MonsterFactory;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;
import edu.mhs.wombat.utils.effects.Starfield;

public class GameState extends BasicGameState {
	private StateBasedGame gm;
	private Starfield bg;
	private ArrayList<EntityInstance> entities;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gm = game;

	}

	public void enter(GameContainer container, StateBasedGame game) {
		bg = new Starfield(-10, -10);
		entities = new ArrayList<EntityInstance>();
		int NUMTEST = 1000;
		for (int i = 0; i < NUMTEST ; i++) {
			entities.add(MonsterFactory.newRandomWalkerMonster());
		}

	}

	public void leave(GameContainer container, StateBasedGame game) {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bg.render(g);
		g.setFont(ResourceManager.getFont("font100"));
		g.drawString("In Game Now", Globals.WIDTH / 2 - 20, Globals.HEIGHT / 2);
		for (EntityInstance ei : entities)
			EntityMapper.getEntity(ei).render(game, ei, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		for (EntityInstance ei : entities)
			EntityMapper.getEntity(ei).update(game, ei, delta);
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			StateUtils.switchTo(gm, States.MENU);
		}
	}

	@Override
	public int getID() {
		return States.GAME.ordinal();
	}
}
