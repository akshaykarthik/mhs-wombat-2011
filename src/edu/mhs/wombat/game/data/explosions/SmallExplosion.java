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
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

public class SmallExplosion extends Bullet {

	private final Vector2f pos;
	private EntityState state;

	private final float damage = 100f / 60f;

	private static Image image;
	private Circle hitbox;

	private float time = 0;

	private float wobble = 500;
	private float reset = 1000;
	
	private static Image[] images = new Image[10];


	public SmallExplosion(Vector2f pos) {
		
		if(images[0] == null){
			for(int i = 0; i < images.length; i++){
				images[i] = ResourceManager.getSpriteSheet("expl_mine").getSubImage(i, 0);
			}
		}
		
		this.pos = pos.copy();
		this.hitbox = new Circle(pos.x, pos.y, 10);
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

		time += delta;
		float tenth = reset / images.length;
		if (time > reset){
			setState(EntityState.DEAD);
		} 
		
		for(int i = 0; i < images.length; i++){
			if(time > (i+1)*tenth && i+1 < images.length){
				image = images[i+1];
			} else if (time < tenth){
				image = images[0];
			}
		}
		
		hitbox = new Circle(pos.x, pos.y,
				(float) (100 * Math.sin((Math.PI * time) / (1000f))));
		System.out.println(hitbox.radius);

	}

	@Override
	public void render(StateBasedGame game, Graphics g) {

//		g.setColor(new Color((float) Math.random(), (float) Math.random(),
//				(float) Math.random(), 1f));
//		g.fill(hitbox);
		if(Globals.isInField(pos)){
			image.drawCentered(pos.x, pos.y);
		}

		g.setColor(Color.white);
	}

	@Override
	public void close(GameStatus gs) {

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
		return this.damage;
	}

}
