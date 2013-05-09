package edu.mhs.wombat.utils;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;

public class QuadGrid {

	private GameStatus gs;
	private Rectangle bounds;

	private QuadGrid topL;
	private QuadGrid topR;
	private QuadGrid botL;
	private QuadGrid botR;

	private ArrayList<Entity> entities;

	public QuadGrid(Vector2f size, GameStatus gs) {
		this(new Vector2f(0, 0), size, gs);
	}

	public QuadGrid(Vector2f topL, Vector2f size, GameStatus gs) {
		bounds = new Rectangle(0, 0, size.x, size.y);

	}

	public void addEntities(ArrayList<Entity> entities) {
		for (Entity a : entities) {
			if (a.getHitBox().intersects(bounds)) {
				entities.add(a);
			}
		}
	}

	public void removeEntities() {
		entities.clear();

	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<QuadGrid> getSubdivisions() {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		grids.add(topL);
		grids.add(topR);
		grids.add(botL);
		grids.add(botR);
		return grids;
	}

	public ArrayList<QuadGrid> getPlayerGrid() {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		for (QuadGrid a : getSubdivisions()) {
			if (a.bounds.intersects(gs.player.getHitBox())) {
				grids.add(a);
			}
		}
		return grids;
	}

	public void subdivide() {
		Vector2f halfSize = new Vector2f(
				(bounds.getMaxX() - bounds.getMinX()) / 2,
				(bounds.getMaxY() - bounds.getMinY()) / 2);
		Vector2f minXY = new Vector2f(bounds.getMinX(), bounds.getMinY());
		topL = new QuadGrid(minXY, halfSize, gs);
		topR = new QuadGrid(new Vector2f(minXY.x + halfSize.x, minXY.y),
				halfSize, gs);
		botL = new QuadGrid(new Vector2f(minXY.x, minXY.y + halfSize.y),
				halfSize, gs);
		botR = new QuadGrid(minXY.add(halfSize), halfSize, gs);

	}

	public ArrayList<QuadGrid> subdivideN(int n) {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		Vector2f halfSize = new Vector2f(
				(bounds.getMaxX() - bounds.getMinX()) / 2,
				(bounds.getMaxY() - bounds.getMinY()) / 2);
		Vector2f minXY = new Vector2f(bounds.getMinX(), bounds.getMinY());

		topL = new QuadGrid(minXY, halfSize, gs);
		topR = new QuadGrid(new Vector2f(minXY.x + halfSize.x, minXY.y),
				halfSize, gs);
		botL = new QuadGrid(new Vector2f(minXY.x, minXY.y + halfSize.y),
				halfSize, gs);
		botR = new QuadGrid(minXY.add(halfSize), halfSize, gs);

		if (n > 1) {
			topL.subdivideN(n - 1);
			topR.subdivideN(n - 1);
			botL.subdivideN(n - 1);
			botR.subdivideN(n - 1);
		} else if (n == 1) {
			this.subdivide();
			grids.addAll(getSubdivisions());
			return grids;
		}
		return grids;

	}

}
