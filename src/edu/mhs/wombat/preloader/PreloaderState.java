package edu.mhs.wombat.preloader;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
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
	private String description = "";

	private Image background;
	private UnicodeFont font;
	private UnicodeFont font2;

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame gm) throws SlickException {
		try {
			loader = ResourceManager.loadResources("assets/resources.xml");
			numResources = loader.remainingElements();
		} catch (IOException e) {
			e.printStackTrace();
		}

		background = new Image("assets/background_1.png");
		UnicodeFont unicodeFont = new UnicodeFont(
				"assets/fonts/AliquamREG.ttf", 60, false, false);
		unicodeFont.getEffects().add(new ColorEffect());
		unicodeFont.addAsciiGlyphs();
		unicodeFont.loadGlyphs();
		unicodeFont.setDisplayListCaching(true);

		font = unicodeFont;
		unicodeFont = new UnicodeFont("assets/fonts/AliquamREG.ttf", 40, false,
				false);
		unicodeFont.getEffects().add(new ColorEffect());
		unicodeFont.addAsciiGlyphs();
		unicodeFont.loadGlyphs();
		unicodeFont.setDisplayListCaching(true);
		font2 = unicodeFont;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gm, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		g.setFont(font);
		g.drawString("Currently Loading ", 150, 250);
		g.setFont(font2);
		g.drawString(description, 250, 350);
		g.drawRoundRect(100, 500, Globals.WIDTH - 200, 10, 10);
		g.fillRoundRect(100, 500, (Globals.WIDTH - 200) * percent, 10, 10);
		g.setColor(Color.red);
		g.fillArc(98, 450, 100, 100, 0, 360 * percent);
		g.setColor(Color.white);
		g.drawArc(99, 450, 99, 99, 0, 360 * percent);

	}

	private float timer = 0.0f;

	@Override
	public void update(GameContainer gc, StateBasedGame gm, int delta)
			throws SlickException {
		isComplete = percent >= 1.0 && timer > 500f;

		if (percent >= 1.0)
			timer += delta;

		percent = (numResources - loader.remainingElements()) / numResources;

		if (isComplete) {
			StateUtils.switchToNoTransition(gm, States.MENU);
		} else {
			description = loader.loadNext();
		}
	}

	@Override
	public int getID() {
		return States.PRELOADER.ordinal();
	}

}
