package edu.mhs.wombat.game.data.common;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class LinearBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;

	private static Image image;
	private static Hitbox hitbox;;

	public LinearBullet(Vector2f source, Vector2f target, float velocity) {
		if (image == null || hitbox == null) {
			image = ResourceManager.getImage("weps_tiny_bullet");
			hitbox = new Hitbox(image.getWidth(), image.getHeight());

			image.setCenterOfRotation((float) image.getWidth() / 2f,
					(float) image.getHeight() / 2f);

		}
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

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, int delta) {
		pos = pos.add(vel);
		if (pos.x < 0 || pos.x > Globals.WIDTH || pos.y < 0
				|| pos.y > Globals.HEIGHT)
			state = EntityState.DEAD;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		image.setRotation((float) vel.getTheta());
		if (Globals.isInField(pos))
			image.drawCentered(pos.x, pos.y);

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

	@Override
	public float getDamage() {
		return 1;
	}

	@Override
	public void close() {
		
	}

}
