package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;
import edu.mhs.wombat.game.core.Hitbox;
import edu.mhs.wombat.game.data.player.Player;

public class SlowChaserMonster extends Monster{

	public Vector2f pos;
	public Vector2f vel;
	public EntityState state;

	public SlowChaserMonster(float ix, float iy){
		state = EntityState.ALIVE;
		pos = new Vector2f(ix, iy);
		vel = new Vector2f(0, 0);
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

	}
	
	@Override
	public Hitbox getHitBox() {
		return null;
	}

	@Override
	public void render(StateBasedGame game, Graphics g) {

	}

	@Override
	public void collideWith(Entity b) {
		this.setState(EntityState.DEAD);
	}

	@Override
	public Vector2f getPos() {
		return pos;
	}


	@Override
	public void playerCollide(Player a) {
		
	}

}
