package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.TimeBombBullet;

public class Weapon_TimedSplitter extends Weapon {

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (this.canFire)
			gs.addEntity(new TimeBombBullet(gs.player.pos, this.getMousePos(),
					10f));
	}

	@Override
	public float getAttackCD() {
		return 100;
	}

	@Override
	public String getName() {
		return "wtf mode";
	}

}
