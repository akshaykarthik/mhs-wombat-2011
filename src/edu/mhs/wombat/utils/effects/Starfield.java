package edu.mhs.wombat.utils.effects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import edu.mhs.wombat.utils.Globals;
import edu.mhs.wombat.utils.ResourceManager;

/**
 * This creates a starfield effect where different layers of stars scroll by at
 * different speeds as the player moves.
 * 
 * @author twood
 * 
 */
public class Starfield {

	private Image img;
	private final int TILESIZE = 512;
	private float x, y;
	private final int width, height;
	private final float[] depths;
	private final float dx, dy;
	private boolean pause = false;

	public Starfield(int w, int h) {
		this.loadImage();
		this.dx = w;
		this.dy = h;
		this.width = Globals.WIDTH;
		this.height = Globals.HEIGHT;
		this.depths = new float[] { 12f, 8f, 5f, 3.5f, 1f };
	}

	public Image loadImage() {
		if (this.img == null) {
			this.img = ResourceManager.getImage("starfield1");
		}
		return this.img;
	}

	public void render(Graphics g) {
		g.setDrawMode(Graphics.MODE_ADD);
		for (float scale : this.depths) {
			if (!this.pause) {
				this.x += this.dx;
				this.y += this.dy;
			}
			float cornerX = this.x / scale - this.width / 2 / scale;
			float cornerY = this.y / scale - this.height / 2 / scale;
			float startX = (float) (Math.floor(cornerX / this.TILESIZE) * this.TILESIZE);
			float startY = (float) (Math.floor(cornerY / this.TILESIZE) * this.TILESIZE);
			float offsetX = -cornerX;
			float offsetY = -cornerY;

			for (int row = (int) startY; row < startY + 2 * this.height; row += this.TILESIZE) {
				for (int col = (int) startX; col < startX + 2 * this.width; col += this.TILESIZE) {
					g.drawImage(this.img, col + offsetX, row + offsetY);
				}

			}
		}
		g.setDrawMode(Graphics.MODE_NORMAL);
	}

	public void pause() {
		this.pause = true;
	}

	public void resume() {
		this.pause = false;
	}

}