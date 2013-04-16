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
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.common.Bullet;
import edu.mhs.wombat.game.data.player.Player;

public class SlowChaserMonster extends Monster{

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	private static Shape shape = new Polygon(new float[]{0, 0, 4, 8, 8, 0});
	private Hitbox hitbox = new Hitbox(8, 8);
	public SlowChaserMonster(float ix, float iy){
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
		switch (state) {
		case ALIVE:
			break;
		case DEAD:
			break;
		case DYING:
			break;
		case SPAWNING:
			break;
		case STUNNED:
			break;
		default:
			break;
		}
		vel = gs.player.pos.copy().sub(pos.copy()).normalise().scale(1);
		vel.x = ((float) (vel.x + (Math.random() < 0.5 ? -.750 : .750)));
		vel.y = ((float) (vel.y + (Math.random() < 0.5 ? -.750 : .750)));
		pos.add(vel);
		
	}
	
	@Override
	public Hitbox getHitBox() {
		return hitbox;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(health <= 10 ? Color.yellow : Color.white);
		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		g.draw(shape);
		g.setColor(Color.white);
	}

	@Override
	public void collideWith(Entity b) {
		if(b instanceof Bullet){
			this.takeDamage(1);
		}
		//this.setState(EntityState.DEAD);
	}

	@Override
	public Vector2f getPos() {
		return pos;
	}


	@Override
	public void playerCollide(Player a) {
		
	}

}
