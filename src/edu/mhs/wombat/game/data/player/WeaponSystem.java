package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;

public class WeaponSystem {
	public int current_weapon = 3;

	public Weapon[] weapons = new Weapon[] { new Weapon_Missile(), // linear
																	// bullet
			new Weapon_Mine(), // mine
			new Weapon_Splitter(), // splitter
			new Weapon_TimedSplitter(), // timed god mine
			new Weapon_Basic() // missile
	};

	public void update(int delta) {
		weapons[current_weapon].update(delta, 1);
	}

	public float getAttackTimer() {
		return weapons[current_weapon].attackTimer;
	}

	public float getAttackCD() {
		return weapons[current_weapon].getAttackCD();
	}

	public void fire(GameStatus gs) {
		weapons[current_weapon].fire(gs);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return weapons[current_weapon].getName();
	}
}
