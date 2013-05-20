package edu.mhs.wombat.game.data.bullets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
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
import edu.mhs.wombat.utils.Timer;

public class TimeBombBullet extends Bullet {
	private Vector2f pos;
	private final Vector2f vel;
	private EntityState state;

	private Timer timer;
	
	private final float subDamage = 10;
	private final int projectiles = 30;

	private static Image image;
	private static Image image1;
	private static Image image2;
	private static Image image3;
	private static Image image4;
	private final Shape hitbox;;

	public TimeBombBullet(Vector2f source, Vector2f target, float velocity) {
		if (image == null) {
			SpriteSheet bomb = ResourceManager.getSpriteSheet("weps_bomb");
			image1 = bomb.getSubImage(0, 0);
			image2 = bomb.getSubImage(1, 0);
			image3 = bomb.getSubImage(2, 0);
			image4 = bomb.getSubImage(3, 0);
			image = image1;
		}
		this.timer = new Timer(2000, true);
		this.hitbox = new Rectangle(source.x, source.y, 15, 15);
		this.pos = source.copy();
		Vector2f norm = target.copy().sub(this.pos.copy());
		this.vel = norm.normalise().scale(velocity);
		this.state = EntityState.ALIVE;
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
		timer.update(delta);
		if(timer.isComplete()){
			this.state = EntityState.DYING;
		}

		float percent = timer.percent();
		
		/* @formatter:off */
		image = (percent < 0.25) ?  image1:
			 	(percent < 0.50) ?  image2:
			 	(percent < 0.75) ?  image3:
			 						image4;
		/* @formatter:on */
	

		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);
		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

		if (this.state == EntityState.DYING) {
			for (int i = 0; i < this.projectiles; i += 1) {
				gs.addEntity(new ShrapnelBullet(this.pos, this.pos.copy().add(
						this.vel.copy().add(360 / this.projectiles * i)),
						12.5f, this.subDamage));
			}
			this.state = EntityState.DEAD;
		}
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		if (Globals.isInField(this.pos))
			image.drawCentered(this.pos.x, this.pos.y);

		g.setColor(Color.white);
	}

	@Override
	public Shape getHitBox() {
		return this.hitbox;
	}

	@Override
	public void collideWith(Entity b) {

	}

	@Override
	public void playerCollide(Player a) {

	}

	@Override
	public Vector2f getPos() {
		return this.pos;
	}

	@Override
	public float getDamage() {
		return 0;
	}

	@Override
	public void close(GameStatus gs) {

	}

}
