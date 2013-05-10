package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
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

public class SlowChaserMonster extends Monster {

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	private final Shape shape = new Polygon(new float[] { 0, 0, 4, 8, 8, 0 });

	public SlowChaserMonster(float ix, float iy) {
		this.state = EntityState.ALIVE;
		this.pos = new Vector2f(ix, iy);
		this.vel = new Vector2f(0, 0);
		this.maxHealth = 20;
		this.health = 20;
	}

	public SlowChaserMonster(Vector2f pos2) {
		this(pos2.x, pos2.y);
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
		float moveScale = 1;
		switch (this.state) {
		case ALIVE:
			moveScale = 1;
			break;
		case DEAD:
			break;
		case DYING:
			moveScale = 2;
			break;
		case SPAWNING:
			break;
		case STUNNED:
			break;
		default:
			break;
		}

		this.vel = gs.player.pos.copy().sub(this.pos.copy()).normalise();
		this.vel.x = ((float) (this.vel.x + (Math.random() < 0.5 ? -.50 : .50)));
		this.vel.y = ((float) (this.vel.y + (Math.random() < 0.5 ? -.50 : .50)));
		if (!MathU.inBounds(this.pos.x, 0, Globals.ARENA_WIDTH))
			this.vel.x *= -1;

		if (!MathU.inBounds(this.pos.y, 0, Globals.ARENA_WIDTH))
			this.vel.y *= -1;

		this.pos.add(this.vel.copy().scale(moveScale));

		this.shape.setCenterX(this.pos.x);
		this.shape.setCenterY(this.pos.y);

	}

	@Override
	public Shape getHitBox() {
		return this.shape;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {

		if (Globals.isInField(this.pos))
			g.draw(this.shape);
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
		gs.scores.addPoints(100);
	}

}
