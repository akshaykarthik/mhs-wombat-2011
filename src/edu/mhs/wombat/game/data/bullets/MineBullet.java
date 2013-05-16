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
import edu.mhs.wombat.game.data.explosions.SmallExplosion;
import edu.mhs.wombat.game.data.player.Player;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class MineBullet extends Bullet {
	private final Vector2f pos;
	private EntityState state;

	private static Image image;
	private final Shape hitbox;

	public static float MaxMines = 10;
	public static float CurrentMines = 0;

	public MineBullet(Vector2f source) {
		if (image == null) {
			image = ResourceManager.getImage("weps_mine");
			image.setCenterOfRotation(image.getWidth() / 2f,
					image.getHeight() / 2f);
		}
		this.hitbox = new Rectangle(source.x, source.y, image.getWidth(),
				image.getHeight());
		this.pos = source.copy();
		CurrentMines++;
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
		if (!Globals.isInField(this.pos))
			this.state = EntityState.DEAD;

		this.hitbox.setCenterX(this.pos.x);
		this.hitbox.setCenterY(this.pos.y);
		if (this.state == EntityState.DYING) {
			gs.addEntity(new SmallExplosion(this.pos));
			this.state = EntityState.DEAD;
		}

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);

		image.rotate(0.25f);
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
		if (!(b instanceof Bullet)){
			this.state = EntityState.DYING;
		}
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
		return _BulletData.Mine_Damage;
	}

	@Override
	public void close(GameStatus gs) {
		CurrentMines--;
	}

}
