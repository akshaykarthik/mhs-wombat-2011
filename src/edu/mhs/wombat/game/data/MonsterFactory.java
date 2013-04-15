package edu.mhs.wombat.game.data;

import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.data.monsters.EmptyMonsterInstance;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonsterInstance;
import edu.mhs.wombat.game.data.monsters.SlowChaserMonsterInstance;

public class MonsterFactory {
	public static EntityInstance newRandomWalkerMonster(float x, float y){
		return new RandomWalkerMonsterInstance(x, y);
	}
	
	public static EntityInstance newEmptyMonster(float x, float y){
		return new EmptyMonsterInstance(x, y);
	}
	
	public static EntityInstance newSlowChaserMonster(float x, float y){
		return new SlowChaserMonsterInstance(x, y);
	}
}
