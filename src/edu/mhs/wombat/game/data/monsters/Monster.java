package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityState;

public abstract class Monster implements Entity {
	protected float maxHealth = 10;
	protected float health = this.maxHealth;

	protected void takeDamage(float damage) {
		this.health -= damage;
		if (this.health < this.maxHealth / 4f) {
			this.setState(EntityState.DYING);
		}
		if (this.health <= 0) {
			this.setState(EntityState.DEAD);
		}
	}

	public float collideDoDamage = 1;
	public float collideTakeDamage = 1;
}
