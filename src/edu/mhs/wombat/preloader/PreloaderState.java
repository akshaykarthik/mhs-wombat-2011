package edu.mhs.wombat.preloader;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.shader.ShaderProgram;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.States;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.StateUtils;
import edu.mhs.wombat.utils.XMLResourceLoader;

public class PreloaderState extends BasicGameState {

	private float percent = 0;
	private float numResources = 1;
	private boolean isComplete = false;
	private XMLResourceLoader loader;
	@Override
	public void init(GameContainer gc, StateBasedGame gm)
			throws SlickException {
		try {
			loader = ResourceManager.loadResources("assets/resources.xml");
			numResources = loader.remainingElements();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gm, Graphics g)
			throws SlickException {
		g.drawString("LOADING" + loader.remainingElements(), 250, 250);
		g.drawString("" + ShaderProgram.isSupported(), 250, 270);
		g.drawRect(100, 500, Globals.WIDTH - 200, 10);
		g.fillRect(100, 500, (Globals.WIDTH-200)*percent, 10);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gm, int delta)
			throws SlickException {
		isComplete = percent >= 1.0;
		percent = loader.remainingElements()/numResources;
		if(isComplete) StateUtils.switchTo(gm, States.MENU);
		else{
			loader.loadNext();
		}
	}

	@Override
	public int getID() {
		return States.PRELOADER.ordinal();
	}	

}
