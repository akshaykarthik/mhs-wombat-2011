package edu.mhs.wombat.game.data.monsterbullet;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.bullets.Bullet;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class MonsterBullet implements Entity {
	private Vector2f pos;
	private final Vector2f vel;
	private EntityState state;

	private float damage = 10;

	private static Image image;
	private final Shape hitbox;;

	public MonsterBullet(Vector2f source, Vector2f target, float vel,
			float damage) {
		this(source, target, vel);
		this.damage = damage;
	}

	public MonsterBullet(Vector2f source, Vector2f target, float velocity) {
		if (image == null) {
			image = ResourceManager.getImage("weps_tiny_bullet");
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Rectangle(source.x, source.y, 18, 10);
		this.pos = source.copy();
		Vector2f norm = target.copy().sub(this.pos.copy());
		this.vel = norm.normalise().scale(velocity);
		this.state = EntityState.ALIVE;
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

		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);

		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.setRotation((float) this.vel.getTheta());
		if (Globals.isInField(this.pos))
			image.drawCentered(this.pos.x, this.pos.y);
	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

	@Override
	public void collideWith(Entity b) {
		if (b instanceof Bullet) {
			this.state = EntityState.DEAD;
		}
	}

	@Override
	public void playerCollide(Player a) {
		this.state = EntityState.DEAD;
	}

	@Override
	public Vector2f getPos() {
		return this.pos;
	}

	public float getDamage() {
		return this.damage;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
