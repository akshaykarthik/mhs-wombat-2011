package edu.mhs.wombat.game.data.common;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.VectorU;

public class CurveBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;

	private float time = 0;
	private float wobble = 120;
	private float reset = 240;
	private Circle shape = new Circle(0, 0, 4);

	public CurveBullet(Vector2f source, Vector2f target, float velocity) {
		pos = source.copy();
		Vector2f norm = target.copy().sub(pos.copy());
		vel = norm.normalise().scale(velocity);
		vel.sub(49);
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

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		time += delta;
		pos = pos.add(vel);
		
		if(time < wobble){
			vel = vel.add(15);			
		}
		else if(time < reset){
			vel.sub(15);
		}
		else{
			time = 0;
		}

		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		if (VectorU.inBounds(pos, VectorU.Zero, Globals.Size))
			state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.draw(shape);
		g.setColor(Color.white);
	}

	@Override
	public Shape getHitBox() {
		return shape;
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

	@Override
	public float getDamage() {
		return 5;
	}

	@Override
	public void close() {
		
	}

}
