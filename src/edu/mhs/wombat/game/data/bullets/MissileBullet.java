package edu.mhs.wombat.game.data.bullets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class MissileBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private Vector2f accel;
	private EntityState state;
	private static Image image;
	private Rectangle shape;

	public MissileBullet(Vector2f source, Vector2f target, float ivel,
			float iaccel) {
		if (image == null) {
			image = ResourceManager.getImage("weps_missile");
			image.setCenterOfRotation(image.getWidth() / 2,
					image.getHeight() / 2);
		}

		shape = new Rectangle(source.x, source.y, 28, 14);

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

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		pos = pos.add(vel);
		vel = vel.add(accel);
		if (!Globals.isInField(pos))
			state = EntityState.DEAD;

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.setRotation((float) vel.getTheta());
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		if (Globals.isInField(pos))
			image.drawCentered(pos.x, pos.y);

	}

	@Override
	public Shape getHitBox() {
		return shape;
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

	@Override
	public float getDamage() {
		return 10;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
