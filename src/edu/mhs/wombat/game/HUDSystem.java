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
	private static final ShapeFill Hgradient = new GradientFill(healthX,
			healthY, Color.green, healthX + healthW, healthY + healthH,
			Color.red);

	private static final float energyW = 10;
	private static final float energyH = 250;
	private static final float energyX = Globals.WIDTH - energyW - 30;
	private static final float energyY = Globals.HEIGHT - energyH - 10;
	private static final Shape energyBorder = new Rectangle(energyX, energyY,
			energyW, energyH);
	private static final ShapeFill Egradient = new GradientFill(energyX,
			energyY, Color.blue, energyX + energyW, energyY + energyH,
			Color.cyan);

	// #end constants
	public void update(StateBasedGame game, GameStatus gs, int delta) {

	}

	private void drawEnergyBarPlayer(GameStatus gs, Graphics g) {
		float percent = (gs.player.energy / gs.player.maxEnergy);
		Rectangle energyFill = new Rectangle(energyX, 1 + energyY
				+ (1 - percent) * energyH, energyW - 1, energyH * percent - 1);
		g.draw(energyBorder);
		g.fill(energyFill, Egradient);
	}

	private void drawHealthBarPlayer(GameStatus gs, Graphics g) {
		float percent = (gs.player.health / gs.player.maxHealth);
		Shape healthFill = new Rectangle(healthX, 1 + healthY + (1 - percent)
				* healthH, healthW - 1, healthH * percent - 1);
		g.draw(healthBorder);
		g.fill(healthFill, Hgradient);
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

	public void debugRender(StateBasedGame game, GameStatus gs, Graphics g) {
		g.drawString("EntityCount : " + gs.entities.size(), 10, 50);
	}

	public void debugCamRender(StateBasedGame game, GameStatus gs, Graphics g) {
		// draw debug grid and arena border
		g.drawImage(ResourceManager.getImage("debug_grid"), 0, 0);
		g.drawRect(0, 0, Globals.ARENA_WIDTH, Globals.ARENA_HEIGHT);

		// draw player velocity
		Vector2f pos = gs.player.pos;
		Vector2f vel = gs.player.vel;
		g.setColor(Color.red);
		g.drawLine(pos.x, pos.y, pos.x + vel.x * 10, pos.y + vel.y * 10);
		g.setColor(Color.white);

		// draw horiz/vert lines\
	}

	public void render(StateBasedGame game, GameStatus gs, Graphics g) {
		g.setFont(ResourceManager.getFont("font20"));
		g.drawString("level : " + (int)gs.levelManager.difficulty, 10, 70);
		g.drawString("score : " + gs.scores.getScore(), 10, 90);
		g.drawString("multiplier : " + gs.scores.getMultiplier(), 10, 110);
		g.drawString("weapon : " + gs.player.weps.getName(), 10, 130);
		this.drawHealthBarPlayer(gs, g);
		this.drawEnergyBarPlayer(gs, g);

	}

	public void camRender(StateBasedGame game, GameStatus gs, Graphics g) {
		this.drawAttackTimer(gs, g);

	}

	public void close() {

	}

}
