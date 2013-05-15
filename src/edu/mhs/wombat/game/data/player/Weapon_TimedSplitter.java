package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.TimeBombBullet;

public class Weapon_TimedSplitter extends Weapon {
	
	public final float energyCost = 15;
	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (this.canFire && gs.player.energy >= energyCost){
			gs.addEntity(new TimeBombBullet(gs.player.pos, this.getMousePos(),
					10f));
			gs.player.energy -= energyCost;
		}
	}

	@Override
	public float getAttackCD() {
		return 500;
	}

	@Override
	public String getName() {
		return "wtf mode";
	}

}
