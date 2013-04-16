package edu.mhs.wombat.game.data.common;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;

public class AccelBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private Vector2f accel;
	private EntityState state;

	private float acc_const = 0.5f;

	private Circle shape = new Circle(0, 0, 6);
	private Hitbox hitbox = new Hitbox(11, 11);

	public AccelBullet(Vector2f source, Vector2f target, float ivel,
			float iaccel) {
		pos = source.copy();
		Vector2f target_vel = target.copy().sub(pos.copy()).normalise();
		vel = target_vel.copy().normalise().scale(ivel);
		accel = target_vel.scale(iaccel);
	}

	@Override
	public EntityState getState() {
		return state;
	}

	@Override
	public void setState(EntityState es) {
		state = es;

	}

	@Override
	public void init(GameStatus gs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		pos = pos.add(vel);
		vel = vel.add(accel);
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		float buffer = 1000;
		if (pos.x < -buffer || pos.x > Globals.WIDTH + buffer //
				|| pos.y < -buffer || pos.y > Globals.HEIGHT + buffer)
			state = EntityState.DEAD;

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		if (Globals.isInField(pos))
			g.draw(shape);

	}

	@Override
	public Hitbox getHitBox() {
		return hitbox;
	}

	@Override
	public void collideWith(Entity b) {
		if (!(b instanceof Bullet))
			state = EntityState.DEAD;
	}

	@Override
	public void playerCollide(Player a) {

	}

	@Override
	public Vector2f getPos() {
		return pos;
	}

}
