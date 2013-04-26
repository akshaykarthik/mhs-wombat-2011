package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.data.bullets.MineBullet;
import edu.mhs.wombat.game.data.explosions.SmallExplosion;

public class Weapon_Mine extends Weapon {
	public Weapon_Mine() {
		super();
	}

	@Override
	public void fire(GameStatus gs) {
		super.fire(gs);
		if (canFire && MineBullet.CurrentMines <= MineBullet.MaxMines)
			gs.addEntity(new SmallExplosion(gs.player.pos));
	}

	@Override
	public float getAttackCD() {
		return 10;
	}

	@Override
	public String getName() {
		return "mine";
	}

}
