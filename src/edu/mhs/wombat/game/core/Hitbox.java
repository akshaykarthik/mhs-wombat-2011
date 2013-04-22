package edu.mhs.wombat.game.core;

import org.newdawn.slick.Graphics;

public class Hitbox {
	public float w, h;

	public Hitbox(float iw, float ih) {
		super();
		w = iw;
		h = ih;
	}

	public static boolean collides(Entity a, Entity b) {

		float tw = a.getHitBox().w;
		float th = a.getHitBox().h;

		float rw = b.getHitBox().w;
		float rh = b.getHitBox().h;

		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}

		float tx = a.getPos().x - tw / 2;
		float ty = a.getPos().y - th / 2;
		float rx = b.getPos().x - rw / 2;
		float ry = b.getPos().y - rh / 2;
		rw += rx;
		rh += ry;
		tw += tx;
		th += ty;
		// overflow || intersect
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty)
				&& (tw < tx || tw > rx) && (th < ty || th > ry));
	}


	public static void renderHitbox(Entity a, Graphics g) {
		float tw = a.getHitBox().w;
		float th = a.getHitBox().h;
		float tx = a.getPos().x - tw / 2;
		float ty = a.getPos().y - th / 2;
		g.drawRect(tx, ty, tw, th);
	}

}
