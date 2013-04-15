package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.core.EntityState;

public class RandomWalkerMonsterInstance implements EntityInstance {

	public float x;
	public float dx;
	public float y;
	public float dy;
	public EntityState state;
	
	public RandomWalkerMonsterInstance(){
		
	}
	
	public RandomWalkerMonsterInstance(float ix, float iy){
		x = ix;
		y = iy;
		state = state.ALIVE;
	}
	
	@Override
	public String getEntity() {
		return "RandomWalkerMonster";
	}

	@Override
	public EntityState getState() {
		return this.state;
	}

	@Override
	public void setState(EntityState es) {
		this.state = es;
	}
	
}
