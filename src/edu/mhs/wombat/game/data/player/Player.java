package edu.mhs.wombat.game.data.player;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.Camera;
import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.data.monsterbullet.MonsterBullet;
import edu.mhs.wombat.game.data.monsters.Monster;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class Player implements Entity {
	public Vector2f pos;
	public Vector2f vel;

	public float dv = 10;
	public float mv = 10;

	public float health = 100;
	public float energy = 100;
	public float maxEnergy = 100;
	public float maxHealth = 100;

	public PlayerState state;
	private final Input input = new Input(Globals.HEIGHT);

	public WeaponSystem weps = new WeaponSystem();
	public WeaponSystem specialWeps = new WeaponSystem();	

	public Image image;
	public Image image2;
	public Circle shape = new Circle(0, 0, 15, 15);

	public Player() {
		this.pos = new Vector2f(250, 250);
		this.vel = new Vector2f(0, 0);
		specialWeps.current_weapon = 3;
	}

	public void init(GameStatus gs) {
		this.state = PlayerState.ALIVE;

	}

	public void update(StateBasedGame game, GameStatus gs, int delta) {
		switch (this.state) {
		case ALIVE:
			this.handleMovementInput(delta / 1000f);
			this.weps.update(delta);
			if (Mouse.isButtonDown(0)) {
				this.weps.fire(gs);
			} else if(Mouse.isButtonDown(1)){
				this.specialWeps.fire(gs);
			}
			
			float eps = 5;
			float hps = 0.5f;
			this.energy += eps * (delta / 1000f);
			this.health += hps * (delta / 1000f);
			this.energy = MathU.clamp(this.energy, 0, this.maxEnergy);
			this.health = MathU.clamp(this.health, 0, this.maxHealth);
			break;
		default:
			this.vel.x = 0;
			this.vel.y = 0;
		}

		this.shape.setCenterX(this.pos.x);
		this.shape.setCenterY(this.pos.y);
		this.pos = this.pos.add(this.vel);
		this.pos.x = MathU.clamp(this.pos.x, 0, Globals.ARENA_WIDTH);
		this.pos.y = MathU.clamp(this.pos.y, 0, Globals.ARENA_HEIGHT);
	}

	public void render(StateBasedGame game, Graphics g) {
		if (this.image == null) {
			this.image = ResourceManager.getImage("player_1");
			this.image2 = ResourceManager.getImage("player_2");
		}

		// draw ship
		this.shape.setCenterX(this.pos.x);
		this.shape.setCenterY(this.pos.y);
		this.image.setRotation((float) this.vel.getTheta());
		this.image.drawCentered(this.pos.x, this.pos.y);

		// draw turret
		Vector2f mousepos = Camera.worldToScreen(new Vector2f(this.input
				.getAbsoluteMouseX(), this.input.getAbsoluteMouseY()));
		float turretAngle = (float) Math.toDegrees(Math.atan2(mousepos.y
				- this.pos.y, mousepos.x - this.pos.x));
		this.image2.setRotation(turretAngle);
		this.image2.drawCentered(this.pos.x, this.pos.y);

		// draw crosshair
		g.setColor(this.health < (this.maxHealth/10f) ? Color.red : Color.white);
		float ch_size = this.vel.length();
		g.drawLine(mousepos.x, mousepos.y - ch_size, mousepos.x, mousepos.y);
		g.drawLine(mousepos.x, mousepos.y + ch_size, mousepos.x, mousepos.y);
		g.drawLine(mousepos.x - ch_size, mousepos.y, mousepos.x, mousepos.y);
		g.drawLine(mousepos.x + ch_size, mousepos.y, mousepos.x, mousepos.y);
		g.setColor(Color.white);
	}

	public void collideWith(Entity b) {
		if (b instanceof Monster) {
			this.health -= ((Monster) b).collideDoDamage;
		}
		if (b instanceof MonsterBullet) {
			this.health -= ((MonsterBullet) b).getDamage();
		}
	}

	@Override
	public EntityState getState() {
		return null; // uses playerstate instead
	}

	@Override
	public void setState(EntityState es) {
		// uses playerstate instead
	}

	@Override
	public Shape getHitBox() {
		return this.shape;
	}

	@Override
	public void playerCollide(Player a) {
		// ignore, can't collide with self

	}

	@Override
	public Vector2f getPos() {
		return this.pos;
	}

	private boolean prevQ = false;
	private boolean prevE = false;

	private void handleMovementInput(float delta) {
		boolean right = (this.input.isKeyDown(Input.KEY_RIGHT) || this.input
				.isKeyDown(Input.KEY_D));
		boolean left = (this.input.isKeyDown(Input.KEY_LEFT) || this.input
				.isKeyDown(Input.KEY_A));
		boolean up = (this.input.isKeyDown(Input.KEY_UP) || this.input
				.isKeyDown(Input.KEY_W));
		boolean down = (this.input.isKeyDown(Input.KEY_DOWN) || this.input
				.isKeyDown(Input.KEY_S));

		if (left && !right)
			this.vel.x += -this.dv * delta;
		else if (right && !left)
			this.vel.x += this.dv * delta;

		if (up && !down)
			this.vel.y += -this.dv * delta;
		else if (down && !up)
			this.vel.y += this.dv * delta;

		float velocity = this.vel.length();
		if (velocity > this.mv)
			this.vel.normalise().scale(this.mv);

		if (this.input.isKeyDown(Input.KEY_SPACE)) {
			this.vel.scale(0.95f);
		}

		if (!this.prevE && this.input.isKeyDown(Input.KEY_E)) {
			this.weps.current_weapon = (int) MathU.loop(
					this.weps.current_weapon + 1, 0,
					this.weps.weapons.length - 1);
		}
		if (!this.prevQ && this.input.isKeyDown(Input.KEY_Q)) {
			this.weps.current_weapon = (int) MathU.loop(
					this.weps.current_weapon - 1, 0,
					this.weps.weapons.length - 1);
		}

		this.prevQ = this.input.isKeyDown(Input.KEY_Q);
		this.prevE = this.input.isKeyDown(Input.KEY_E);
	}

	@Override
	public void close(GameStatus gs) {

	}
}
