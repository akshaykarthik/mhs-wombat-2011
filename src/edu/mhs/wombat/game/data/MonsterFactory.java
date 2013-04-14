package edu.mhs.wombat.game.data;

import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonsterInstance;

public class MonsterFactory {
	public static EntityInstance newRandomWalkerMonster(){
		return new RandomWalkerMonsterInstance(150, 150);
	}
}
