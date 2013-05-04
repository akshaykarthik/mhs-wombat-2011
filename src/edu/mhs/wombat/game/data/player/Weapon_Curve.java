package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.CurveBullet;

public class Weapon_Curve extends Weapon {
	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire)
			gs.addEntity(new CurveBullet(gs.player.pos, getMousePos(), 7.5f));
	}

	@Override
	public float getAttackCD() {
		return 50;
	}

	@Override
	public String getName() {
		return "curve";
	}

}
