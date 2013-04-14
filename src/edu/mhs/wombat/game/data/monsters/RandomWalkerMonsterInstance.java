package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.EntityInstance;

public class RandomWalkerMonsterInstance implements EntityInstance {

	public float x;
	public float dx;
	public float y;
	public float dy;
	
	public RandomWalkerMonsterInstance(){
		
	}
	
	public RandomWalkerMonsterInstance(float ix, float iy){
		x = ix;
		y = iy;
	}
	
	@Override
	public String getEntity() {
		return "RandomWalkerMonster";
	}
}
