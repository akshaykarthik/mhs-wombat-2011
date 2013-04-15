package edu.mhs.wombat.game.core;

public interface EntityInstance {
	String getEntity();
	EntityState getState();
	void setState(EntityState es);
}
