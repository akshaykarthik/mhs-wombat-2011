package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.SplitterBullet;

public class Weapon_Splitter extends Weapon {

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (this.canFire)
			gs.addEntity(new SplitterBullet(gs.player.pos, this.getMousePos(),
					10f));
	}

	@Override
	public float getAttackCD() {
		return 350;
	}

	@Override
	public String getName() {
		return "splitter";
	}

}
