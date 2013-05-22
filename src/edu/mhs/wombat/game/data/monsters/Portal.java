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
import edu.mhs.wombat.utils.Timer;

public class Portal extends Monster {
	private static Image image;
	private final Shape hitbox;

	private static final Color lineColor = new Color(0.4f, 0.4f, 0.4f, 0.4f);
	public Vector2f pos;
	public EntityState state;

	private Timer _timer;
	private Vector2f playerPos = new Vector2f();
	private float difficulty;
	private static float maxSpawnedEntities = 10;
	private float spawnedEntities = 0;

	private boolean new_level = true;

	public Portal(float ix, float iy, float difficulty) {
		this.difficulty = difficulty;
		maxSpawnedEntities = 5 + 2.5f * difficulty;
		this.maxHealth = 250 + 25 * difficulty;
		this.health = maxHealth;

		this._timer = new Timer(3000 + difficulty * 50);

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
	public void init(GameStatus gs) {

	}

	@Override
	public void close(GameStatus gs) {
		gs.scores.addPoints(1000, gs);
	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		this.playerPos = gs.player.pos.copy();
		this._timer.update(delta);
		if (this._timer.isComplete() && spawnedEntities < maxSpawnedEntities) {
			this._timer.resetAndStart();
			double val = Math.random();
			switch ((int) difficulty) {
			case 3:
				if (this.new_level) {
					for (int i = 0; i < 40; i++) {
						gs.addEntity(new RandomWalkerMonster(this.pos));
					}
				}
				this.new_level = false;
				break;
			default:
				Entity es = null;
				float bag = 0;
				while (bag <= this.difficulty * 1.0f) {
					int tempID = (int) (Math.random() * 5);
					System.out.println("looping " + tempID + " " + bag + " " + this.difficulty * 1.0f);
					if (_MonsterData.getDifficultyOnID(tempID) <= difficulty) {
						es = _MonsterData.getMonsterOnId(tempID, this.pos);
						bag += _MonsterData.getDifficultyOnID(tempID);
						System.out.println("spawning");
						
					}
					if (es != null) {
						gs.addEntity(es);
						spawnedEntities++;
					}
				}
				/* @formatter:off */
				/*
				Entity es = (val < 0.450) ? new RandomWalkerMonster(this.pos): // 45 %
							(val < 0.550) ? new SlowChaserMonster(this.pos): // 10 %
							(val < 0.650) ? new BumperMonster(this.pos): // 10%
							(val < 0.850) ? new ShooterMonster(this.pos): // 20%
							(val < 0.925) ? new PullMonster(this.pos) : // 7.5%
							(val < 1.000) ? new PushMonster(this.pos) : // 7.5%
											null;
				*/
	
				
				this.new_level = false;
				/* @formatter:on */
			}

		}

		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		image.drawCentered(this.pos.x, this.pos.y);
		Shape shape = this.hitbox;
		Vector2f pos = this.pos;

		float healthHeight = 3;
		float health = (this.health / this.maxHealth);
		float healthX = pos.x - shape.getWidth() / 2;
		float healthY = pos.y - shape.getHeight() / 2 - 5 - healthHeight;

		g.fillRect(healthX, healthY,
				MathU.clamp(health, 0, 1) * image.getWidth(), healthHeight);
		g.drawRect(healthX, healthY, image.getWidth(), healthHeight);
		g.setColor(new Color(0.4f, 0.4f, 0.4f, 0.4f));
		g.drawLine(playerPos.x, playerPos.y, pos.x, pos.y);
		g.setColor(Color.white);

		if (Globals.GAME_DEBUG) {
			float itimer = this._timer.percent();
			float timerX = pos.x - shape.getWidth() / 2;
			float timerY = pos.y + shape.getHeight() / 2 + 5;
			float timerHeight = 3;
			g.fillRect(timerX, timerY,
					MathU.clamp(itimer, 0, 1) * image.getWidth(), timerHeight);
			g.drawRect(timerX, timerY, image.getWidth(), timerHeight);

		}
		g.setColor(Portal.lineColor);
		g.drawLine(playerPos.x, playerPos.y, pos.x, pos.y);
		g.setColor(Color.white);
	}

	@Override
	public void collideWith(Entity b) {
		if (b instanceof Bullet) {
			this.takeDamage(((Bullet) b).getDamage());
		}
	}

	@Override
	public void playerCollide(Player a) {
		this.takeDamage(this.collideTakeDamage);
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
	public Vector2f getPos() {
		return this.pos;
	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

}
