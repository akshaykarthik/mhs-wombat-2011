package edu.mhs.wombat.game.core;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonster;

public class EntityMapper {
	private static Entity _RandomWalkerMonster;
	static {
		_RandomWalkerMonster = new RandomWalkerMonster();
	}
	
	public static Entity getEntity(EntityInstance ei){
		return getEntity(ei.getEntity());
	}
	
	public static Entity getEntity(String type){
		switch (type) {
		case "RandomWalkerMonster":
			return _RandomWalkerMonster;
		default:
			return null;
		}
	}
}
