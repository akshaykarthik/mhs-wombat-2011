package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.common.SplitterBullet;

public class Weapon_Splitter extends Weapon {

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire)
			gs.addEntityInstance(new SplitterBullet(gs.player.pos,
					getMousePos(), 10f));
	}

	@Override
	public float getAttackCD() {
		return 1000;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "splitter";
	}

}
