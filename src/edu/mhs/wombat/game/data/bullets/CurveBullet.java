package edu.mhs.wombat.game.data.bullets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;

public class CurveBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;

	private float damage = 5;
	private float time = 0;
	private final float wobble = 500;
	private final float reset = 1000;
	private final Circle shape = new Circle(0, 0, 4);

	public CurveBullet(Vector2f source, Vector2f target, float velocity) {
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

		this.shape.setCenterX(this.pos.x);
		this.shape.setCenterY(this.pos.y);
		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.draw(this.shape);
		g.setColor(Color.white);
	}

	@Override
	public Shape getHitBox() {
		return this.shape;
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
