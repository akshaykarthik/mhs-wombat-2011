package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.common.MineBullet;

public class Weapon_Mine extends Weapon {
	public Weapon_Mine() {
		super();
	}

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire && MineBullet.CurrentMines <= MineBullet.MaxMines)
			gs.addEntity(new MineBullet(gs.player.pos));
	}

	@Override
	public float getAttackCD() {
		return 1000;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "mine";
	}

}
