package edu.mhs.wombat.game.data.bullets;

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

public class WobblyBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;
	//
	private float time = 0;
	private float wobble = 500;
	private float reset = 1000;

	private Circle shape = new Circle(0, 0, 4);

	public WobblyBullet(Vector2f source, Vector2f target, float velocity) {
		pos = source.copy();
		Vector2f norm = target.copy().sub(pos.copy());
		vel = norm.normalise().scale(velocity);
		vel.add(10);
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
		time += 1000 / delta;

		pos = pos.add(vel);
		if (time < wobble) {
			vel = vel.add(delta/100);
		} else if (time < reset) {
			vel.sub(delta/100);
		} else {
			time = 0;
		}
		

		shape.setCenterX(pos.x);
		shape.setCenterY(pos.y);
		if (!Globals.isInField(pos))
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
	public void close(GameStatus gs) {
		
	}

}
