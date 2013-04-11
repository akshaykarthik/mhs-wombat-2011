package edu.mhs.wombat.utils.transition;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class DoorCloseTransition implements Transition {

	protected static SGL GL = Renderer.get();

	private GameState next;
	private GameState prev;
	private float offset;
	private boolean finish;
	private Image door;

	public DoorCloseTransition() {
		door = ResourceManager.getImage("transition_door");
	}

	public void init(GameState firstState, GameState secondState) {
		prev = secondState;
		next = firstState;
	}

	public boolean isComplete() {
		return finish;
	}

	public void postRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
		g.drawImage(door, offset - Globals.WIDTH / 2, 0);
		g.drawImage(door.getFlippedCopy(true, false), Globals.WIDTH - offset, 0);

	}

	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
	}

	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		offset += delta * Globals.TRANSITION_FACTOR;
		if (offset > container.getWidth() / 2) {
			finish = true;
		}
	}

}