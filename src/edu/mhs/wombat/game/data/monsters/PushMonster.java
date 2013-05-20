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

public class PushMonster extends Monster {
	private static Image image;
	private final Shape hitbox;
	private final Circle pushCircle;

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	public float maxvel = 2;

	public float playerDist;
	public float pushSpeed = .15f;
	public float pushRadius = 100f;
	public float basepushRadius = 50f;

	private Vector2f scaling = new Vector2f(0, 0);
	private Vector2f playerPos = new Vector2f(0, 0);
	private boolean player_in_range;

	public PushMonster(float ix, float iy) {

		this.collideDoDamage = 5f;
		this.collideTakeDamage = this.health;

		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		this.vel = new Vector2f(0, 0);
		this.maxHealth = _MonsterData.Push_Health;
		this.health = this.maxHealth;

		if (image == null) {
			image = ResourceManager.getSpriteSheet("monsters_circle")
					.getSubImage(3, 0).getScaledCopy(1.25f);
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Circle(this.pos.x, this.pos.y, image.getWidth() / 2f);

		this.pushCircle = new Circle(this.pos.x, this.pos.y, this.pushRadius
				+ this.basepushRadius);
	}

	public PushMonster(Vector2f pos) {
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
		this.pushCircle.setCenterX(this.pos.x);
		this.pushCircle.setCenterY(this.pos.y);
		
		this.pushCircle.setRadius(this.pushRadius
				* (this.health / this.maxHealth) + this.basepushRadius);

		this.pushSpeed = 0.15f * (gs.player.pos.distance(this.pos) / this.pushRadius);
		Vector2f distance = gs.player.pos.copy().sub(this.pos.copy());
		this.scaling = distance.normalise().scale(this.pushSpeed);

		this.vel = gs.player.pos.copy().sub(this.pos.copy()).normalise()
				.scale(0.2f);

		this.player_in_range = gs.player.shape.intersects(this.pushCircle);

		if (this.player_in_range) {
			gs.player.vel = gs.player.vel.copy().add(this.scaling);
		}

		switch (this.state) {
		case ALIVE:
			break;
		case DEAD:
			break;
		case DYING:
			this.pushSpeed = this.pushSpeed / 2;
			break;
		case SPAWNING:
			break;
		case STUNNED:
			break;
		default:
			break;
		}

		if (!MathU.inBounds(this.pos.x, 1, Globals.ARENA_WIDTH - 1))
			this.vel.x *= -1;

		if (!MathU.inBounds(this.pos.y, 1, Globals.ARENA_HEIGHT - 1))
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
		g.draw(this.pushCircle);
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
		gs.scores.addPoints(_MonsterData.Push_Points, gs);
	}

}
