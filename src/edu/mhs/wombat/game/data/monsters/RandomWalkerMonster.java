package edu.mhs.wombat.game.data.monsters;

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
import edu.mhs.wombat.game.data.common.Bullet;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathUtils;
import edu.mhs.wombat.utils.ResourceManager;

public class RandomWalkerMonster extends Monster {
	private static Image image;
	private static Hitbox hitbox;

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;
	public float maxvel = 2;

	public RandomWalkerMonster(float ix, float iy) {
		state = EntityState.ALIVE;
		pos = new Vector2f(ix, iy);
		vel = new Vector2f(0, 0);
		if (image == null || hitbox == null) {
			image = ResourceManager.getImage("monsters_red_swirly").getScaledCopy(0.5f);
			hitbox = new Hitbox(image.getWidth(), image.getHeight());
			image.setCenterOfRotation((float) image.getWidth() / 2f,
					(float) image.getHeight() / 2f);
			
		}
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

		vel.x = ((float) (vel.x + (Math.random() < 0.5 ? -1.0 : 1.0)));
		vel.y = ((float) (vel.y + (Math.random() < 0.5 ? -1.0 : 1.0)));
		pos = pos.add(vel);
		pos.x = (float) MathUtils.loop(pos.x, 0, Globals.WIDTH);
		pos.y = (float) MathUtils.loop(pos.y, 0, Globals.HEIGHT);
		
		if (vel.length() > maxvel)
			vel.normalise().scale(maxvel);
	}

	@Override
	public Hitbox getHitBox() {
		return hitbox;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(state == EntityState.DEAD ? Color.red : Color.white);
		image.drawCentered(pos.x, pos.y);
		g.setColor(Color.white);
	}

	@Override
	public void collideWith(Entity b) {
		if (b instanceof Bullet) {
			this.takeDamage(10);
		}
	}

	@Override
	public Vector2f getPos() {
		return pos;
	}

	@Override
	public void playerCollide(Player a) {
		this.setState(EntityState.ALIVE);
	}

}
