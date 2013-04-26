package edu.mhs.wombat.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathU;
import edu.mhs.wombat.utils.ResourceManager;

public class HUDSystem {

	private static final int _timerWidth = 30;
	private static final int _timerHeight = 4;

	private static final float healthW = 10;
	private static final float healthH = 300;
	private static final float healthX = Globals.WIDTH - healthW - 10;
	private static final float healthY = Globals.HEIGHT - healthH - 10;
	private static final Shape healthBorder = new Rectangle(healthX, healthY,
			healthW, healthH);
	private static final ShapeFill gradient = new GradientFill(healthX, healthY, Color.green,
			healthX + healthW, healthY + healthH, Color.red);

	public void update(StateBasedGame game, GameStatus gs, int delta) {

	}

	public void camRender(StateBasedGame game, GameStatus gs, Graphics g) {
		drawAttackTimer(gs, g);
	}

	private void drawEnergyBarPlayer(GameStatus gs, Graphics g) {

	}

	private void drawHealthBarPlayer(GameStatus gs, Graphics g) {
		float percent = (gs.player.health / gs.player.maxHealth);
		Shape healthFill = new Rectangle(healthX, healthY + (1 - percent)
				* healthH, healthW - 1, healthH * percent);
		g.draw(healthBorder);
		g.fill(healthFill, gradient);
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
		drawHealthBarPlayer(gs, g);
		drawEnergyBarPlayer(gs, g);
		if (Globals.GAME_DEBUG) {
			g.drawString("EntityCount : " + gs.entities.size(), 10, 50);
		}
	}

	public void close() {

	}

}
