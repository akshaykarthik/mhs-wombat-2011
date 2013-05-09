package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.bullets.Bullet;
import edu.mhs.wombat.game.data.monsterbullet.MonsterBullet;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class ShooterMonster extends Monster {

	private static Image image;
	private final Shape hitbox;

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	public float maxvel = 2;

	private float time = 0;
	private final float reset = 5000;

	public ShooterMonster(float ix, float iy) {

		this.collideDoDamage = 5f;
		this.collideTakeDamage = this.health;

		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		this.vel = new Vector2f(0, 0);
		if (image == null) {
			image = ResourceManager.getSpriteSheet("monsters_circle")
					.getSubImage(1, 0).getScaledCopy(0.75f);
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Circle(this.pos.x, this.pos.y, image.getWidth() / 2f);
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

		this.time += delta;
		if (this.time >= this.reset) {
			this.time = 0;
			gs.addEntity(new MonsterBullet(this.pos, gs.player.pos, 3.5f));
		}

		if (!MathU.inBounds(this.pos.x, 0, Globals.ARENA_WIDTH))
			this.vel.x *= -1;

		if (!MathU.inBounds(this.pos.y, 0, Globals.ARENA_WIDTH))
			this.vel.y *= -1;

		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);

		if (this.vel.length() > this.maxvel)
			this.vel.normalise().scale(this.maxvel);
	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.drawCentered(this.pos.x, this.pos.y);
	}

	@Override
	public void collideWith(Entity b) {
		if (b instanceof Bullet) {
			this.takeDamage(((Bullet) b).getDamage());
		}
	}

	@Override
	public Vector2f getPos() {
		return this.pos;
	}

	@Override
	public void playerCollide(Player a) {
		this.takeDamage(this.collideTakeDamage);
	}

	@Override
	public void close(GameStatus gs) {
		gs.scores.addPoints(10);
	}

}
