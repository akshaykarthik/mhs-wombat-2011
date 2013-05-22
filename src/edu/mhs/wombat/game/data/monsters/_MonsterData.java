package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.geom.Vector2f;

public class _MonsterData {

	public static final int maxID = 5;
	
	public static Monster getMonsterOnId(int id, Vector2f pos){
		Monster m = null;
		/* @formatter:off */
		switch(id){
		case 0: m = new RandomWalkerMonster(pos); break;
		case 1: m = new BumperMonster(pos); break;
		case 2: m = new PullMonster(pos); break;
		case 3: m = new PushMonster(pos); break;
		case 4: m = new SlowChaserMonster(pos); break;
		case 5: m = new ShooterMonster(pos); break;
		}
		/* @formatter:on */
		return m;
	}
	
	public static int getDifficultyOnID(int id){
		/* @formatter:off */
		switch(id){
		case 0: return 1; //m = new RandomWalkerMonster(pos); break;
		case 1: return 2; //m = new BumperMonster(pos); break;
		case 2: return 4; //m = new PullMonster(pos); break;
		case 3: return 4; //m = new PushMonster(pos); break;
		case 4: return 8; //m = new SlowChaserMonster(pos); break;
		case 5: return 4; //m = new ShooterMonster(pos); break;
		default: return 0;
		}
		/* @formatter:on */
	}
	// walkermonster
	public static final int RandomWalker_Points = 5;
	public static final float RandomWalker_Health = 10;
	public static final float RandomWalker_Difficulty = 1;
	
	// bumpermonster
	public static final int Bumper_Points = 10;
	public static final float Bumper_Health = 20;
	public static final float Bumper_Difficulty = 3;
	
	// push/pull

	public static final float PushPull_MinRadius = 50f;
	public static final float PushPull_BaseRadius = 100f;
	
	// pullmonster
	public static final int Pull_Points = 10;
	public static final float Pull_Health = 20;
	
	// pushmonster
	public static final int Push_Points = 10;
	public static final float Push_Health = 20;
	
	// chasermonster
	public static final int SlowChaser_Points = 100;
	public static final float SlowChaser_Health = 50;
		
	// shootermonster
	public static final int Shooter_Points = 45;
	public static final float Shooter_Health = 25;
	public static final float Shooter_Timer = 2500;
	


}
