package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.common.AccelBullet;

public class Weapon_Missile extends Weapon {
	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire)
			gs.addEntityInstance(new AccelBullet(gs.player.pos, getMousePos(),
					-10, 1.5f));
	}

	@Override
	public float getAttackCD() {
		return 500;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "missile";
	}

}