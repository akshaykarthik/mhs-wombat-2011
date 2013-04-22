package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;

public abstract class Monster implements Entity{
	protected float maxHealth = 10;
	protected float health = maxHealth;
	
	
	protected void takeDamage(float damage){
		health -= damage;
		if(health < maxHealth/4f){
			setState(EntityState.DYING);
		}
		if(health <= 0){
			setState(EntityState.DEAD);
		}
	}
}
