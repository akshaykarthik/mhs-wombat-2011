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
	private int TILESIZE = 512;
	private float x, y;
	private int width, height;
	private float[] depths;
	private float dx, dy;
	private boolean pause = false;

	public Starfield(int w, int h) {
		loadImage();
		dx = w;
		dy = h;
		width = Globals.WIDTH;
		height = Globals.HEIGHT;
		this.depths = new float[] { 12f, 8f, 5f, 3.5f, 1f };
	}

	public Image loadImage() {
		if (img == null) {
			img = ResourceManager.getImage("starfield1");
		}
		return img;
	}

	public void render(Graphics g) {
		g.setDrawMode(Graphics.MODE_ADD);
		for (int d = 0; d < depths.length; d++) {
			if (!pause) {
				x += dx;
				y += dy;
			}
			float scale = depths[d];
			float cornerX = x / scale - width / 2 / scale;
			float cornerY = y / scale - height / 2 / scale;
			float startX = (float) (Math.floor(cornerX / TILESIZE) * TILESIZE);
			float startY = (float) (Math.floor(cornerY / TILESIZE) * TILESIZE);
			float offsetX = -cornerX;
			float offsetY = -cornerY;

			for (int row = (int) startY; row < startY + 2 * height; row += TILESIZE) {
				for (int col = (int) startX; col < startX + 2 * width; col += TILESIZE) {
					g.drawImage(img, col + offsetX, row + offsetY);
				}

			}
		}
		g.setDrawMode(Graphics.MODE_NORMAL);
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		pause = false;
	}

}