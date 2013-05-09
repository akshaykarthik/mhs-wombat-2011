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

	private float offset = 0;
	private float offset2 = 0;
	private float finish;
	private final Image door;
	private final Image door2;

	public DoorCloseTransition() {
		this.door = ResourceManager.getImage("transition_door");
		this.door2 = ResourceManager.getImage("transition_door2");
	}

	public void init(GameState firstState, GameState secondState) {

	}

	public boolean isComplete() {
		return this.finish > Globals.TRANSITION_FACTOR3;
	}

	public void postRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
		g.drawImage(this.door2, this.offset2 - Globals.WIDTH / 2, 0);
		g.drawImage(this.door2.getFlippedCopy(true, false), Globals.WIDTH
				- this.offset2, 0);

		g.drawImage(this.door, this.offset - Globals.WIDTH / 2, 0);
		g.drawImage(this.door.getFlippedCopy(true, false), Globals.WIDTH
				- this.offset, 0);
	}

	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
	}

	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		if (this.offset >= container.getWidth() / 2) {
			this.offset = container.getWidth() / 2;
			if (this.offset2 >= container.getWidth() / 2) {
				this.offset2 = container.getWidth() / 2;
				this.finish += delta;
			} else {
				this.offset2 += delta * Globals.TRANSITION_FACTOR2;
			}
		} else {
			this.offset += delta * Globals.TRANSITION_FACTOR;
		}
	}

}