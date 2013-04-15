package edu.mhs.wombat.game.data.monsters;

import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.core.EntityState;

public class SlowChaserMonsterInstance implements EntityInstance{
	public float x;
	public float dx;
	public float y;
	public float dy;
	public EntityState state;
	
	public SlowChaserMonsterInstance(){
		
	}
	
	public SlowChaserMonsterInstance(float ix, float iy){
		x = ix;
		y = iy;
		state = EntityState.ALIVE;
	}
	
	@Override
	public String getEntity() {
		return "SlowChaserMonster";
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
