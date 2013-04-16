package edu.mhs.wombat.game.data.common;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;

public class LinearBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;
//
//	private float time = 0;
//	private float wobble = 500;
//	private float reset = 1000;

	private Circle shape = new Circle(0, 0, 4);
	private Hitbox hitbox = new Hitbox(8, 8);

	public LinearBullet(Vector2f source, Vector2f target, float velocity) {
		pos = source.copy();
		Vector2f norm = target.copy().sub(pos.copy());
		vel = norm.normalise().scale(velocity);

		state = EntityState.ALIVE;
	}

	@Override
	public EntityState getState() {
		return state;
	}

	@Override
	public void setState(EntityState es) {
		state = es;

	}

	@Override
	public void init(GameStatus gs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
//		time += 1000/delta;
//		
		pos = pos.add(vel);
//		if(time < wobble){
//			vel = vel.add(10);			
//		}
//		else if(time < reset){
//			vel.sub(10);
//		}
//		else{
//			time = 0;
//		}

		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		if (pos.x < 0 || pos.x > Globals.WIDTH || pos.y < 0
				|| pos.y > Globals.HEIGHT)
			state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.draw(shape);
		g.setColor(Color.white);
	}

	@Override
	public Hitbox getHitBox() {
		return hitbox;
	}

	@Override
	public void collideWith(Entity b) {
		if (!(b instanceof Bullet))
			state = EntityState.DEAD;
	}

	@Override
	public void playerCollide(Player a) {

	}

	@Override
	public Vector2f getPos() {
		return pos;
	}

}
