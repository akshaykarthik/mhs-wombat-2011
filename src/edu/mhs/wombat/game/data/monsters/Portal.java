package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Color;
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
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class Portal extends Monster {
	private static Image image;
	private final Shape hitbox;

	public Vector2f pos;
	public EntityState state;

	private final float timer;
	private float time;
	private Vector2f playerPos = new Vector2f();

	public Portal(float ix, float iy, float itimer) {
		this.maxHealth = 100;
		this.health = 100;

		this.timer = itimer;
		this.time = 0;

		this.collideDoDamage = 0.5f;
		this.collideTakeDamage = 0.1f;

		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		if (image == null) {
			image = ResourceManager.getSpriteSheet("monsters_square")
					.getSubImage(3, 0).getScaledCopy(1.8f);
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Rectangle(this.pos.x, this.pos.y, image.getWidth(),
				image.getHeight());
		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);
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
		this.time += delta;
		if (this.time > this.timer) {
			this.time = 0;
			double val = Math.random();
			/* @formatter:off */
			Entity es = (val < 0.450) ?  new RandomWalkerMonster(this.pos) :// 45 %
				 		(val < 0.550) ?  new SlowChaserMonster(this.pos) :	// 10 %
				 		(val < 0.650) ?  new BumperMonster(this.pos) :		// 10%
				 		(val < 0.850) ?  new ShooterMonster(this.pos):  	// 20%
				 		(val < 0.925) ?  new PullMonster(this.pos):			// 7.5%
				 		(val < 1.000) ?  new PushMonster(this.pos):			// 7.5%
				 						 null;
			/* @formatter:on */
			if (es != null) {
				gs.addEntity(es);
			}
		}

		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.drawCentered(this.pos.x, this.pos.y);
		Shape shape = this.hitbox;
		Vector2f pos = this.pos;
		float health = (this.health / this.maxHealth);
		float healthX = pos.x - shape.getWidth() / 2;
		float healthY = pos.y - shape.getHeight();
		float healthHeight = 3;
		g.fillRect(healthX, healthY,
				MathU.clamp(health, 0, 1) * image.getWidth(), healthHeight);
		g.drawRect(healthX, healthY, image.getWidth(), healthHeight);
		g.setColor(new Color(0.4f, 0.4f, 0.4f, 0.4f));
		g.drawLine(playerPos.x, playerPos.y, pos.x, pos.y);
		g.setColor(Color.white);d
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
