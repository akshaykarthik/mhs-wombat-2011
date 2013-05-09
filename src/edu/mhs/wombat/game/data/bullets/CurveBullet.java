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
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class CurveBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;

	private static Image image;
	private final Shape hitbox;

	private float damage = 5;
	private float time = 0;
	private final float wobble = 500;
	private final float reset = 1000;

	public CurveBullet(Vector2f source, Vector2f target, float velocity) {
		if (image == null) {
			image = ResourceManager.getImage("weps_curve_bullet");
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Rectangle(source.x, source.y, 30, 10);
		this.pos = source.copy();

		Vector2f norm = target.copy().sub(this.pos.copy());
		this.vel = norm.normalise().scale(velocity);
		this.vel.sub(83);
		this.state = EntityState.ALIVE;
	}

	public CurveBullet(Vector2f source, Vector2f target, float velocity,
			float damage) {
		this(source, target, velocity);
		this.damage = damage;
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
		this.time += delta;
		this.pos = this.pos.add(this.vel);
		float angle = 360f;
		System.out.println(delta);
		if (this.time < this.wobble) {
			this.vel = this.vel.add(angle * (delta / 1000f));
		} else if (this.time < this.reset) {
			this.vel.sub(angle * (delta / 1000f));
		} else {
			this.time = 0;
		}

		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);
		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.setRotation((float) this.vel.getTheta());
		if (Globals.isInField(this.pos)) {
			this.image.drawCentered(this.pos.x, this.pos.y);
		}
	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

	@Override
	public void collideWith(Entity b) {
		if (!(b instanceof Bullet))
			this.state = EntityState.DEAD;
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
		return this.damage;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
