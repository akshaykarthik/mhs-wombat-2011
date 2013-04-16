package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;

public abstract class Monster implements Entity{
	protected float maxHealth = 10;
	protected float health = maxHealth;
	
	
	protected void takeDamage(float damage){
		health -= damage;
		if(health < 10){
			setState(EntityState.DEAD);
		}
	}
}
