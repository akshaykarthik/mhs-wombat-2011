package edu.mhs.wombat.game.data.monsters;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;
import edu.mhs.wombat.game.core.EntityInstance;
import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.MathUtils;

public class RandomWalkerMonster implements Entity {
	private static Circle c = new Circle(0, 0, 5);

	@Override
	public void init(GameStatus gs) {

	}

	@Override
	public void update(StateBasedGame game, GameStatus gs, EntityInstance ei,
			int delta) {
		switch (ei.getState()) {
		case ALIVE:
			break;
		case DEAD:
			break;
		case DYING:
			break;
		case SPAWNING:
			break;
		case STUNNED:
			break;
		default:
			break;
		}

		RandomWalkerMonsterInstance ti = (RandomWalkerMonsterInstance) ei;
		ti.dx = ((float) (ti.dx + (Math.random() < 0.5 ? -1.0 : 1.0)));
		ti.dy = ((float) (ti.dy + (Math.random() < 0.5 ? -1.0 : 1.0)));
		ti.x += ti.dx;
		ti.y += ti.dy;
		ti.x = (float) MathUtils.loop(ti.x, 0, Globals.WIDTH);
		ti.y = (float) MathUtils.loop(ti.y, 0, Globals.HEIGHT);

		float dv = (float) Math.hypot(ti.dx, ti.dy);
		float ndv = (float) MathUtils.clamp(dv, -50, 50);
		if (dv != ndv) {
			ti.dx *= (dv / ndv);
			ti.dy *= (dv / ndv);
		}
	}

	@Override
	public void render(StateBasedGame game, EntityInstance ei, Graphics g) {
		RandomWalkerMonsterInstance ti = (RandomWalkerMonsterInstance) ei;
		c.setCenterX(ti.x);
		c.setCenterY(ti.y);
		g.draw(c);
	}

	@Override
	public void collideWith(EntityInstance a, Entity ba, EntityInstance bb) {

	}

}
