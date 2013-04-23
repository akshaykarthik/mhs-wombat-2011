package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.common.Bullet;
import edu.mhs.wombat.game.data.player.Player;

public class SlowChaserMonster extends Monster {

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	private Shape shape = new Polygon(new float[] { 0, 0, 4, 8, 8, 0 });

	public SlowChaserMonster(float ix, float iy) {
		state = EntityState.ALIVE;
		pos = new Vector2f(ix, iy);
		vel = new Vector2f(0, 0);
		this.maxHealth = 20;
		this.health = 20;
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
		switch (state) {
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
		vel = gs.player.pos.copy().sub(pos.copy()).normalise();
		vel.x = ((float) (vel.x + (Math.random() < 0.5 ? -.50 : .50)));
		vel.y = ((float) (vel.y + (Math.random() < 0.5 ? -.50 : .50)));
		pos.add(vel.copy().scale(moveScale));

	}

	@Override
	public Shape getHitBox() {
		return shape;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(state == EntityState.DYING ? Color.red : Color.white);
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		g.draw(shape);
		g.setColor(Color.white);
	}

	@Override
	public void collideWith(Entity b) {
		if (b instanceof Bullet) {
			this.takeDamage(((Bullet) b).getDamage());
		}
		// this.setState(EntityState.DEAD);
	}

	@Override
	public Vector2f getPos() {
		return pos;
	}

	@Override
	public void playerCollide(Player a) {

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
