package edu.mhs.wombat.game.data.player;

import edu.mhs.wombat.game.GameStatus;

public class WeaponSystem {
	private int current_weapon = 1;
	
	private Weapon[] weapons = new Weapon[]{
		new Weapon_Basic(),
		new Weapon_Mine()
	};
	
	public void update(int delta){
		weapons[current_weapon].update(delta, 1);
	}
	
	public void fire(GameStatus gs){
		weapons[current_weapon].fire(gs);
	}
}
