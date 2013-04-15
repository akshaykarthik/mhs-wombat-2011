package edu.mhs.wombat.game.core;

import edu.mhs.wombat.game.data.monsters.EmptyMonster;
import edu.mhs.wombat.game.data.monsters.RandomWalkerMonster;
import edu.mhs.wombat.game.data.monsters.SlowChaserMonster;

public class EntityMapper {
	private static Entity _EmptyMonster;
	private static Entity _RandomWalkerMonster;
	private static Entity _SlowChaserMonster;

	public static void load() {
		_RandomWalkerMonster = new RandomWalkerMonster();
		_EmptyMonster = new EmptyMonster();
		_SlowChaserMonster = new SlowChaserMonster();
	}

	public static Entity getEntity(EntityInstance ei) {
		return getEntity(ei.getEntity());
	}

	public static Entity getEntity(String type) {
		switch (type) {
		case "EmptyMonster":
			return _EmptyMonster;
		case "RandomWalkerMonster":
			return _RandomWalkerMonster;
		case "SlowChaserMonster":
			return _SlowChaserMonster;
		default:
			return null;
		}
	}
}
