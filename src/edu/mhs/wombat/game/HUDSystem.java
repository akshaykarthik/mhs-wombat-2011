package edu.mhs.wombat.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.utils.MathUtils;

public class HUDSystem {

	public void update(StateBasedGame game, GameStatus gs, int delta) {

	}

	public void camRender(StateBasedGame game, GameStatus gs, Graphics g) {
		Shape shape = gs.player.shape;
		Vector2f pos = gs.player.pos;
		float timer = (gs.player.weps.getAttackTimer() / gs.player.weps
				.getAttackCD());
		float timerX = pos.x - shape.getWidth() / 2;
		float timerY = pos.y - shape.getHeight();
		g.fillRect(timerX, timerY, MathUtils.clamp(timer, 0, 1) * 30, 4);
		g.drawRect(timerX, timerY, 30, 4);
	}

	public void render(StateBasedGame game, GameStatus gs, Graphics g) {

	}

	public void close() {

	}

}
