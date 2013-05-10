package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Color;
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
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class PullMonster extends Monster {
	private static Image image;
	private final Shape hitbox;
	private final Circle pullCircle;

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	public float maxvel = 2;

	public float playerDist;
	public float pullSpeed = .15f;
	public float pullRadius = 100f;
	public float basePullRadius = 50f;

	private Vector2f scaling = new Vector2f(0, 0);
	private Vector2f playerPos = new Vector2f(0, 0);
	private boolean player_in_range;

	public PullMonster(float ix, float iy) {

		this.collideDoDamage = 5f;
		this.collideTakeDamage = this.health;

		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		this.vel = new Vector2f(0, 0);
		this.maxHealth = 30;
		this.health = 30;

		if (image == null) {
			image = ResourceManager.getSpriteSheet("monsters_circle")
					.getSubImage(4, 0).getScaledCopy(1.25f);
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Circle(this.pos.x, this.pos.y, image.getWidth() / 2f);

		this.pullCircle = new Circle(this.pos.x, this.pos.y, this.pullRadius
				+ this.basePullRadius);
	}

	public PullMonster(Vector2f pos) {
		this(pos.x, pos.y);
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
		this.playerPos = gs.player.pos.copy();
		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);
		this.pullCircle.setCenterX(this.pos.x);
		this.pullCircle.setCenterY(this.pos.y);

		this.pullCircle.setRadius(this.pullRadius
				* (this.health / this.maxHealth) + this.basePullRadius);

		this.pullSpeed = 0.15f * (gs.player.pos.distance(this.pos) / this.pullRadius);
		Vector2f distance = gs.player.pos.copy().sub(this.pos.copy());
		this.scaling = distance.normalise().scale(this.pullSpeed);

		this.vel = gs.player.pos.copy().sub(this.pos.copy()).normalise()
				.scale(0.2f);

		this.player_in_range = gs.player.shape.intersects(this.pullCircle);

		if (this.player_in_range) {
			gs.player.vel = gs.player.vel.copy().sub(this.scaling);
		}

		switch (this.state) {
		case ALIVE:
			break;
		case DEAD:
			break;
		case DYING:
			this.pullSpeed = this.pullSpeed / 2;
			break;
		case SPAWNING:
			break;
		case STUNNED:
			break;
		default:
			break;
		}

		if (!MathU.inBounds(this.pos.x, 0, Globals.ARENA_WIDTH))
			this.vel.x *= -1;

		if (!MathU.inBounds(this.pos.y, 0, Globals.ARENA_WIDTH))
			this.vel.y *= -1;

		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

		this.pos = this.pos.add(this.vel);

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
		g.setColor(new Color(1f, 1f, 1f, 0.4f));
		g.draw(this.pullCircle);
		if (this.player_in_range)
			g.drawLine(this.pos.x, this.pos.y, this.playerPos.x,
					this.playerPos.y);
		g.setColor(Color.white);
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
