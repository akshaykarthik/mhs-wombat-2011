package edu.mhs.wombat.utils;

import org.newdawn.slick.geom.Vector2f;


public class Globals {
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	public static final Vector2f Size = new Vector2f(WIDTH, HEIGHT);
	
	
	public static final int TARGET_FPS = 60;

	// These factors control the speed of transitions
	public static final float TRANSITION_FACTOR = 2.0f;
	public static final float TRANSITION_FACTOR2 = 1.5f;
	public static final float TRANSITION_FACTOR3 = 500f;

	public static final boolean DEBUG = true;
	public static boolean GAME_DEBUG = true;
	
	public static boolean isInField(Vector2f pos){
		return (pos.x > 0 && pos.x < WIDTH) && (pos.y > 0 && pos.y < HEIGHT);
	}

}
