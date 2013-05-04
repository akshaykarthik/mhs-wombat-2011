package edu.mhs.wombat.game.data.bullets;

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
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class TimeBombBullet extends Bullet {
	private Vector2f pos;
	private Vector2f vel;
	private EntityState state;

	private int bomb = 2000;
	private int time = 0;

	private float subDamage = 10;
	private int projectiles = 30;

	private static Image image;
	private static Image image1;
	private static Image image2;
	private static Image image3;
	private static Image image4;
	private Shape hitbox;;

	public TimeBombBullet(Vector2f source, Vector2f target, float velocity) {
		if (image == null) {
			image1 = ResourceManager.getSpriteSheet("weps_bomb").getSubImage(0,
					0);
			image2 = ResourceManager.getSpriteSheet("weps_bomb").getSubImage(1,
					0);
			image3 = ResourceManager.getSpriteSheet("weps_bomb").getSubImage(2,
					0);
			image4 = ResourceManager.getSpriteSheet("weps_bomb").getSubImage(3,
					0);
			image = image1;
		}
		hitbox = new Rectangle(source.x, source.y, 15, 15);
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
		time += delta;
		if (time >= bomb) {
			state = EntityState.DYING;
		}

		float fourth = bomb / 4;
		if (time < fourth) {
			image = image1;
		} else if (time > fourth && time < 2 * fourth) {
			image = image2;
		} else if (time > 2 * fourth && time < 3 * fourth) {
			image = image3;
		} else {
			image = image4;
		}

		pos = pos.add(vel.copy().scale((bomb - time) / bomb));

		hitbox.setCenterX(pos.x);
		hitbox.setCenterY(pos.y);
		if (!Globals.isInField(pos))
			state = EntityState.DEAD;

		if (state == EntityState.DYING) {
			for (int i = 0; i < projectiles; i += 1) {
				gs.addEntity(new ShrapnelBullet(pos, pos.copy().add(
						vel.copy().add(360 / projectiles * i)), 12.5f,
						subDamage));
			}
			state = EntityState.DEAD;
		}
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		if (Globals.isInField(pos))
			image.drawCentered(pos.x, pos.y);

		g.setColor(Color.white);
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
		return 0;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
