package edu.mhs.wombat.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class HUDSystem {

	private static final int _timerWidth = 30;
	private static final int _timerHeight = 4;

	public void update(StateBasedGame game, GameStatus gs, int delta) {

	}

	public void camRender(StateBasedGame game, GameStatus gs, Graphics g) {
		drawAttackTimer(gs, g);
		drawHealthBarPlayer(gs, g);
		drawEnergyBarPlayer(gs, g);
	}

	private void drawEnergyBarPlayer(GameStatus gs, Graphics g) {

	}

	private void drawHealthBarPlayer(GameStatus gs, Graphics g) {

	}

	private void drawAttackTimer(GameStatus gs, Graphics g) {
		Shape shape = gs.player.shape;
		Vector2f pos = gs.player.pos;
		float timer = (gs.player.weps.getAttackTimer() / gs.player.weps
				.getAttackCD());
		float timerX = pos.x - shape.getWidth() / 2;
		float timerY = pos.y - shape.getHeight();
		g.fillRect(timerX, timerY, MathU.clamp(timer, 0, 1) * _timerWidth,
				_timerHeight);
		g.drawRect(timerX, timerY, _timerWidth, _timerHeight);
	}

	public void render(StateBasedGame game, GameStatus gs, Graphics g) {
		g.setFont(ResourceManager.getFont("font20"));
		g.drawString("score : " + gs.scores.getScore(), 10, 70);
		g.drawString("weapon : " + gs.player.weps.getName(), 10, 90);

		if (Globals.GAME_DEBUG) {
			g.drawString("EntityCount : " + gs.entities.size(), 10, 50);
		}
	}

	public void close() {

	}

}
