package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.MissileBullet;

public class Weapon_Missile extends Weapon {
	
	public final float energyCost = 5;

	
	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (this.canFire && gs.player.energy >= energyCost){
			gs.addEntity(new MissileBullet(gs.player.pos, this.getMousePos(),
					-10, 1.5f));
			gs.player.energy -= energyCost;
		}
	}

	@Override
	public float getAttackCD() {
		return 500;
	}

	@Override
	public String getName() {
		return "Missile";
	}

}
