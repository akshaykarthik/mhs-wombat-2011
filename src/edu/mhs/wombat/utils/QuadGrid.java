package edu.mhs.wombat.utils;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import edu.mhs.wombat.game.GameStatus;
import edu.mhs.wombat.game.core.Entity;

public class QuadGrid {

	private GameStatus gs;
	private final Rectangle bounds;

	private QuadGrid topL;
	private QuadGrid topR;
	private QuadGrid botL;
	private QuadGrid botR;

	private ArrayList<Entity> entities;

	public QuadGrid(Vector2f size, GameStatus gs) {
		this(new Vector2f(0, 0), size, gs);
	}

	public QuadGrid(Vector2f topL, Vector2f size, GameStatus gs) {
		this.bounds = new Rectangle(0, 0, size.x, size.y);

	}

	public void addEntities(ArrayList<Entity> entities) {
		for (Entity a : entities) {
			if (a.getHitBox().intersects(this.bounds)) {
				entities.add(a);
			}
		}
	}

	public void removeEntities() {
		this.entities.clear();

	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	public ArrayList<QuadGrid> getSubdivisions() {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		grids.add(this.topL);
		grids.add(this.topR);
		grids.add(this.botL);
		grids.add(this.botR);
		return grids;
	}

	public ArrayList<QuadGrid> getPlayerGrid() {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		for (QuadGrid a : this.getSubdivisions()) {
			if (a.bounds.intersects(this.gs.player.getHitBox())) {
				grids.add(a);
			}
		}
		return grids;
	}

	public void subdivide() {
		Vector2f halfSize = new Vector2f(
				(this.bounds.getMaxX() - this.bounds.getMinX()) / 2,
				(this.bounds.getMaxY() - this.bounds.getMinY()) / 2);
		Vector2f minXY = new Vector2f(this.bounds.getMinX(),
				this.bounds.getMinY());
		this.topL = new QuadGrid(minXY, halfSize, this.gs);
		this.topR = new QuadGrid(new Vector2f(minXY.x + halfSize.x, minXY.y),
				halfSize, this.gs);
		this.botL = new QuadGrid(new Vector2f(minXY.x, minXY.y + halfSize.y),
				halfSize, this.gs);
		this.botR = new QuadGrid(minXY.add(halfSize), halfSize, this.gs);

	}

	public ArrayList<QuadGrid> subdivideN(int n) {
		ArrayList<QuadGrid> grids = new ArrayList<QuadGrid>();
		Vector2f halfSize = new Vector2f(
				(this.bounds.getMaxX() - this.bounds.getMinX()) / 2,
				(this.bounds.getMaxY() - this.bounds.getMinY()) / 2);
		Vector2f minXY = new Vector2f(this.bounds.getMinX(),
				this.bounds.getMinY());

		this.topL = new QuadGrid(minXY, halfSize, this.gs);
		this.topR = new QuadGrid(new Vector2f(minXY.x + halfSize.x, minXY.y),
				halfSize, this.gs);
		this.botL = new QuadGrid(new Vector2f(minXY.x, minXY.y + halfSize.y),
				halfSize, this.gs);
		this.botR = new QuadGrid(minXY.add(halfSize), halfSize, this.gs);

		if (n > 1) {
			this.topL.subdivideN(n - 1);
			this.topR.subdivideN(n - 1);
			this.botL.subdivideN(n - 1);
			this.botR.subdivideN(n - 1);
		} else if (n == 1) {
			this.subdivide();
			grids.addAll(this.getSubdivisions());
			return grids;
		}
		return grids;

	}

}
