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
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;
import edu.mhs.wombat.utils.Timer;
import edu.mhs.wombat.utils.TimerList;

public class MonkeyBossMonster extends Monster {
	private static Image image;
	private final Shape hitbox;

	private int currentAttack;
	private Timer cdTimer;
	private TimerList attacks;
	
	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	public float maxvel = 2;

	public MonkeyBossMonster(Vector2f pos) {
		this(pos.x, pos.y);
	}

	public MonkeyBossMonster(float ix, float iy) {

		this.collideDoDamage = 0.5f;
		this.collideTakeDamage = this.health;

		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		this.vel = new Vector2f(0, 0);
		
		cdTimer = new Timer(2500);
		attacks = new TimerList(2);
		
		if (image == null) {
			image = ResourceManager.getSpriteSheet("monsters_circle")
					.getSubImage(3, 0).getScaledCopy(0.5f);
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Circle(this.pos.x, this.pos.y, image.getWidth() / 2f);
		this.maxHealth = _MonsterData.RandomWalker_Health;
		this.health = this.maxHealth;
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
		
		if(attacks.allDefault() && cdTimer.isComplete()){
			currentAttack = (int) Math.floor(Math.random() * attacks.size() + 0.5);
			
		}
		
		
		
		this.vel.x = ((float) (this.vel.x + (Math.random() < 0.5 ? -1.0 : 1.0)));
		this.vel.y = ((float) (this.vel.y + (Math.random() < 0.5 ? -1.0 : 1.0)));
		this.pos = this.pos.add(this.vel);

		if (!MathU.inBounds(this.pos.x, 1, Globals.ARENA_WIDTH - 1))
			this.vel.x *= -1;

		if (!MathU.inBounds(this.pos.y, 1, Globals.ARENA_HEIGHT - 1))
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
		gs.scores.addPoints(_MonsterData.RandomWalker_Points, gs);
	}

}
