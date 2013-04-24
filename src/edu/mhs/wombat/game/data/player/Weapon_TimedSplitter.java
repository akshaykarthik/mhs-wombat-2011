package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.common.TimedSplitterBullet;

public class Weapon_TimedSplitter extends Weapon {

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire)
			gs.addEntityInstance(new TimedSplitterBullet(gs.player.pos,
					getMousePos(), 10f));
	}

	@Override
	public float getAttackCD() {
		return 100;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "wtf mode";
	}

}
