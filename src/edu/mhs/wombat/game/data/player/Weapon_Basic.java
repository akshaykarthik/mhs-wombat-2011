package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.common.LinearBullet;

public class Weapon_Basic extends Weapon {
	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire)
			gs.addEntityInstance(new LinearBullet(gs.player.pos,
					getMousePos(), 7.5f));
	}

	@Override
	public float getAttackCD() {
		return 100;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "basic weapon";
	}

}