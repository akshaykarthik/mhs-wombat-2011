package edu.mhs.wombat.game.data;

import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.data.monsters.EmptyMonster;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonster;
import edu.mhs.wombat.game.data.monsters.SlowChaserMonster;


public class MonsterFactory {
	public static Entity newRandomWalkerMonster(float x, float y){
		return new RandomWalkerMonster(x, y);
	}
	
	public static Entity newEmptyMonster(float x, float y){
		return new EmptyMonster(x, y);
	}
	
	public static Entity newSlowChaserMonster(float x, float y){
		return new SlowChaserMonster(x, y);
	}
}
