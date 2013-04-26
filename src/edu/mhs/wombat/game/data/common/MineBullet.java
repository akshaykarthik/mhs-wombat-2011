package edu.mhs.wombat.game.data.common;

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
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class MineBullet extends Bullet {
	private Vector2f pos;
	private EntityState state;

	private static Image image;
	private Shape hitbox;

	public static float MaxMines = 10;
	public static float CurrentMines = 0;

	public MineBullet(Vector2f source) {
		if (image == null) {
			image = ResourceManager.getImage("weps_mine_1");
			image.setCenterOfRotation((float) image.getWidth() / 2f,
					(float) image.getHeight() / 2f);
		}
		hitbox = new Rectangle(source.x, source.y, image.getWidth(),
				image.getHeight());
		pos = source.copy();
		CurrentMines++;
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
		if (!(MathU.inBounds(pos.x, 0, Globals.WIDTH) && MathU
				.inBounds(pos.y, 0, Globals.HEIGHT))) {
			state = EntityState.DEAD;
		}

		hitbox.setCenterX(pos.x);
		hitbox.setCenterY(pos.y);
		if (state == EntityState.DYING) {
			state = EntityState.DEAD;
		}

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {
		g.setColor(Color.red);

		image.rotate(0.5f);
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
		if (!(b instanceof Bullet))
			state = EntityState.DYING;
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
		return 100;
	}

	@Override
	public void close(GameStatus gs) {
		CurrentMines--;
	}

}
