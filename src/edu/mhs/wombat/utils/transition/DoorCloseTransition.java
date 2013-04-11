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
	private float offset = 0;
	private float offset2 = 0;
	private float finish;
	private Image door;
	private Image door2;

	public DoorCloseTransition() {
		door = ResourceManager.getImage("transition_door");
		door2 = ResourceManager.getImage("transition_door2");
	}

	public void init(GameState firstState, GameState secondState) {
		prev = secondState;
		next = firstState;
	}

	public boolean isComplete() {
		return finish > Globals.TRANSITION_FACTOR3;
	}

	public void postRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
		g.drawImage(door2, offset2 - Globals.WIDTH / 2, 0);
		g.drawImage(door2.getFlippedCopy(true, false), Globals.WIDTH - offset2,
				0);

		g.drawImage(door, offset - Globals.WIDTH / 2, 0);
		g.drawImage(door.getFlippedCopy(true, false), Globals.WIDTH - offset, 0);
	}

	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
	}

	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		if (offset >= container.getWidth() / 2) {
			offset = container.getWidth() / 2;
			if (offset2 >= container.getWidth() / 2) {
				offset2 = container.getWidth() / 2;
				finish += delta;
			} else {
				offset2 += delta * Globals.TRANSITION_FACTOR2;
			}
		} else {
			offset += delta * Globals.TRANSITION_FACTOR;
		}
	}

}