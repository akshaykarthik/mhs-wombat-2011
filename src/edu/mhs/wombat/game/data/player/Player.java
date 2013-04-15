package edu.mhs.wombat.game.data.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathUtils;

public class Player {
	public float x, dx;
	public float y, dy;
	public float health, energy;
	public PlayerState state;
	private Input input = new Input(Globals.HEIGHT);

	private Circle shape = new Circle(0, 0, 15, 15);

	public Player() {
		x = 150;
		y = 150;
	}

	public void init(GameStatus gs) {
		state = PlayerState.ALIVE;

	}

	public void update(StateBasedGame game, GameStatus gs, int delta) {
		switch (state) {
		case ALIVE:
			boolean right = (input.isKeyDown(Input.KEY_RIGHT));
			boolean left = (input.isKeyDown(Input.KEY_LEFT));
			boolean up = (input.isKeyDown(Input.KEY_UP));
			boolean down = (input.isKeyDown(Input.KEY_DOWN));

			if (left && !right)
				dx = -10;
			else if (right && !left)
				dx = 10;
			else
				dx = 0;

			if (up && !down)
				dy = -10;
			else if (down && !up)
				dy = 10;
			else
				dy = 0;

			if (input.isKeyDown(Input.KEY_SPACE)) {
				dx = 0;
				dy = 0;
			}

			break;
		case DEAD:
			dx = 0;
			dy = 0;
			break;
		case DYING:
			dx = 0;
			dy = 0;
			break;
		case SPAWNING:
			dx = 0;
			dy = 0;
			break;
		case STUNNED:
			dx = 0;
			dy = 0;
			break;
		}
		x += dx;
		y += dy;

		x = (float) MathUtils.loop(x, 0, Globals.WIDTH);
		y = (float) MathUtils.loop(y, 0, Globals.HEIGHT);
	}

	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.magenta);
		shape.setCenterX(x);
		shape.setCenterY(y);
		g.fill(shape);
		g.setColor(Color.cyan);
		float x2 = input.getAbsoluteMouseX();
		float y2 = input.getAbsoluteMouseY();
		g.drawLine(x, y, x2, y2);
		g.setColor(Color.white);

	}

	public void collideWith(EntityInstance a, Entity ba, EntityInstance bb) {

	}

}
