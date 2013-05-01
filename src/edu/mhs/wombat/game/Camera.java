package edu.mhs.wombat.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import edu.mhs.wombat.utils.Globals;

public class Camera {

	private static float x = 0;
	private static float y = 0;

	public static Vector2f screenToWorld(Vector2f screenV) {
		return new Vector2f(screenV.x + Camera.x, screenV.y + Camera.y);
	}

	public static Vector2f worldToScreen(Vector2f worldV) {
		return new Vector2f(worldV.x - Camera.x, worldV.y - Camera.y);
	}

	public static void preDraw(Graphics g, GameStatus gs) {
		x = -gs.player.pos.x + Globals.WIDTH / 2; // player is now center
		y = -gs.player.pos.y + Globals.HEIGHT / 2;
		g.translate(x, y);

	}

	public static void postDraw(Graphics g, GameStatus gs) {
		g.translate(-x, -y);
	}
	
	

}
