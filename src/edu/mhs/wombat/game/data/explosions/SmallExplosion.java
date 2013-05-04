package edu.mhs.wombat.game.data.explosions;

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

public class SmallExplosion extends Bullet {

	private Vector2f pos;
	private EntityState state;

	private float damage = 100f / 60f;

	private static Image image;
	private Circle hitbox;

	private float time = 0;
	private float wobble = 500;
	private float reset = 1000;

	public SmallExplosion(Vector2f pos) {
		this.pos = pos.copy();
		hitbox = new Circle(pos.x, pos.y, 10);
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
		if (time > reset)
			setState(EntityState.DEAD);

		hitbox = new Circle(pos.x, pos.y,
				(float) (100 * Math.sin((Math.PI * time) / (1000f))));
		System.out.println(hitbox.radius);
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random(), 1f));
		g.fill(hitbox);
		g.setColor(Color.white);
	}

	@Override
	public void close(GameStatus gs) {

	}

	@Override
	public Shape getHitBox() {
		return hitbox;
	}

	@Override
	public void collideWith(Entity b) {

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
		return damage;
	}

}
