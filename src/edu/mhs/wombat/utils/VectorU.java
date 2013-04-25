package edu.mhs.wombat.utils;

import org.newdawn.slick.geom.Vector2f;

public class VectorU {
	public static final Vector2f Zero = new Vector2f(0, 0);
	public static final Vector2f Unit = new Vector2f(1, 1);

	
	public static boolean inBounds(Vector2f pos, Vector2f tl, Vector2f br){
		return (pos.x >= tl.x && pos.x <= br.x) && (pos.y >= tl.y && pos.y <= br.y);
	}
}
