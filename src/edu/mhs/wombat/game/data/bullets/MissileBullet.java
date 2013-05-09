package edu.mhs.wombat.game.data.bullets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.explosions.SmallExplosion;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class MissileBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private final Vector2f accel;
	private EntityState state;
	private static Image image;
	private final Rectangle shape;

	public MissileBullet(Vector2f source, Vector2f target, float ivel,
			float iaccel) {
		if (image == null) {
			image = ResourceManager.getImage("weps_missile");
			image.setCenterOfRotation(image.getWidth() / 2,
					image.getHeight() / 2);
		}

		this.shape = new Rectangle(source.x, source.y, 28, 14);

		this.pos = source.copy();
		Vector2f target_vel = target.copy().sub(this.pos.copy()).normalise();
		this.vel = target_vel.copy().normalise().scale(ivel);
		this.accel = target_vel.scale(iaccel);
	}

	@Override
	public EntityState getState() {
		return this.state;
	}

	@Override
	public void setState(EntityState es) {
		this.state = es;

	}

	@Override
	public void init(GameStatus gs) {

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		this.pos = this.pos.add(this.vel);
		this.vel = this.vel.add(this.accel);
		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

		if (this.state == EntityState.DYING) {
			this.state = EntityState.DEAD;
			gs.addEntity(new SmallExplosion(this.pos, 300));
		}

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.setRotation((float) this.vel.getTheta());
		this.shape.setCenterX(this.pos.x);
		this.shape.setCenterY(this.pos.y);
		if (Globals.isInField(this.pos))
			image.drawCentered(this.pos.x, this.pos.y);

	}

	@Override
	public Shape getHitBox() {
		return this.shape;
	}

	@Override
	public void collideWith(Entity b) {
		if (!(b instanceof Bullet))
			this.state = EntityState.DYING;
	}

	@Override
	public void playerCollide(Player a) {

	}

	@Override
	public Vector2f getPos() {
		return this.pos;
	}

	@Override
	public float getDamage() {
		return 10;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
