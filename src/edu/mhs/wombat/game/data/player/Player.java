package edu.mhs.wombat.game.data.player;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.Camera;
import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathUtils;

public class Player implements Entity {
	public Vector2f pos;
	public Vector2f vel;

	public float dv = 10;
	public float mv = 10;

	public float health, energy;
	public PlayerState state;
	private Input input = new Input(Globals.HEIGHT);
	public WeaponSystem weps = new WeaponSystem();

	private Circle shape = new Circle(0, 0, 15, 15);

	public Player() {
		pos = new Vector2f(250, 250);
		vel = new Vector2f(0, 0);
	}

	public void init(GameStatus gs) {
		state = PlayerState.ALIVE;

	}

	public void update(StateBasedGame game, GameStatus gs, int delta) {
		switch (state) {
		case ALIVE:
			handleMovementInput(delta / 1000f);
			weps.update(delta);
			if (Mouse.isButtonDown(0)) {
				weps.fire(gs);
			}

			break;
		case DEAD:
			vel.x = 0;
			vel.y = 0;
			break;
		case DYING:
			vel.x = 0;
			vel.y = 0;
			break;
		case SPAWNING:
			vel.x = 0;
			vel.y = 0;
			break;
		case STUNNED:
			vel.x = 0;
			vel.y = 0;
			break;

		}

		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		pos = pos.add(vel);
		pos.x = (float) MathUtils.loop(pos.x, 0, Globals.WIDTH);
		pos.y = (float) MathUtils.loop(pos.y, 0, Globals.HEIGHT);
	}

	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.magenta);
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		g.fill(shape);
		g.setColor(Color.cyan);
		float x2 = input.getAbsoluteMouseX();
		float y2 = input.getAbsoluteMouseY();
		Vector2f mousepos = Camera.worldToScreen(new Vector2f(x2, y2));
		g.drawLine(pos.x, pos.y, mousepos.x, mousepos.y);
		g.setColor(Color.green);
		g.drawLine(pos.x, pos.y, pos.x + vel.x * 10, pos.y + vel.y * 10);
		g.setColor(Color.white);
		g.fillRect(pos.x - shape.getWidth() / 2, pos.y + shape.getHeight(),
				(float) (MathUtils.clamp((weps.getAttackTimer() / weps.getAttackCD()),
						0, 1) * 30), 4);
		g.drawRect(pos.x - shape.getWidth() / 2, pos.y + shape.getHeight(), 30,
				4);

	}

	public void collideWith(Entity b) {

	}

	@Override
	public EntityState getState() {
		return null; // uses playerstate instead
	}

	@Override
	public void setState(EntityState es) {
		// uses playerstate instead
	}

	@Override
	public Shape getHitBox() {
		return shape;
	}

	@Override
	public void playerCollide(Player a) {
		// ignore, can't collide with self

	}

	@Override
	public Vector2f getPos() {
		return pos;
	}

	private boolean prevQ = false;
	private boolean prevE = false;

	private void handleMovementInput(float delta) {
		boolean right = (input.isKeyDown(Input.KEY_RIGHT) || input
				.isKeyDown(Input.KEY_D));
		boolean left = (input.isKeyDown(Input.KEY_LEFT) || input
				.isKeyDown(Input.KEY_A));
		boolean up = (input.isKeyDown(Input.KEY_UP) || input
				.isKeyDown(Input.KEY_W));
		boolean down = (input.isKeyDown(Input.KEY_DOWN) || input
				.isKeyDown(Input.KEY_S));

		if (left && !right)
			vel.x += -dv * delta;
		else if (right && !left)
			vel.x += dv * delta;

		if (up && !down)
			vel.y += -dv * delta;
		else if (down && !up)
			vel.y += dv * delta;

		float velocity = vel.length();
		if (velocity > mv)
			vel.normalise().scale(mv);

		if (input.isKeyDown(Input.KEY_SPACE)) {
			vel.scale(0.95f);
		}

		if (!prevE && input.isKeyDown(Input.KEY_E)) {
			weps.current_weapon = (int) MathUtils.loop(weps.current_weapon + 1,
					0, weps.weapons.length - 1);
		}
		if (!prevQ && input.isKeyDown(Input.KEY_Q)) {
			weps.current_weapon = (int) MathUtils.loop(weps.current_weapon - 1,
					0, weps.weapons.length - 1);
		}

		prevQ = input.isKeyDown(Input.KEY_Q);
		prevE = input.isKeyDown(Input.KEY_E);
	}

	@Override
	public void close() {

	}
}
