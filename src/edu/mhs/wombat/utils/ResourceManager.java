package edu.mhs.wombat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
	private static final Map<String, Music> music = new HashMap<String, Music>();
	private static final Map<String, Sound> sounds = new HashMap<String, Sound>();
	private static final Map<String, Image> images = new HashMap<String, Image>();
	private static final Map<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();
	private static final Map<String, Animation> animations = new HashMap<String, Animation>();
	private static final Map<String, Font> fonts = new HashMap<String, Font>();
	private static final Map<String, String> parameters = new HashMap<String, String>();

	public static XMLResourceLoader loadResources(String ref)
			throws IOException {
		return loadResources(ResourceLoader.getResourceAsStream(ref));
	}

	/**
	 * @see it.marteEngine.XMLResourceLoader
	 */
	public static XMLResourceLoader loadResources(InputStream in)
			throws IOException {
		XMLResourceLoader resourceLoader = new XMLResourceLoader();
		resourceLoader.load(in);
		return resourceLoader;
	}

	public static void addImage(String key, Image image) {
		if (hasImage(key)) {
			throw new IllegalArgumentException("Image for key " + key
					+ " already exist!");
		}
		images.put(key, image);
	}

	public static void addSpriteSheet(String key, SpriteSheet sheet) {
		if (hasSpriteSheet(key)) {
			throw new IllegalArgumentException("SpriteSheet for key " + key
					+ " already exist!");
		}
		sheets.put(key, sheet);
	}

	public static void addAnimation(String key, Animation anim) {
		if (hasAnimation(key)) {
			throw new IllegalArgumentException("Animation for key " + key
					+ " already exist!");
		}
		animations.put(key, anim);
	}

	public static void addFont(String key, Font font) {
		if (hasFont(key)) {
			throw new IllegalArgumentException("Font for key " + key
					+ " already exist!");
		}
		fonts.put(key, font);
	}

	public static void addMusic(String key, Music song) {
		if (hasMusic(key)) {
			throw new IllegalArgumentException("Music for key " + key
					+ " already exist!");
		}
		music.put(key, song);
	}

	public static void addSound(String key, Sound sound) {
		if (hasSound(key)) {
			throw new IllegalArgumentException("Sound for key " + key
					+ " already exist!");
		}
		sounds.put(key, sound);
	}

	public static void setParameter(String key, String value) {
		parameters.put(key, value);
	}

	public static boolean hasImage(String key) {
		return images.containsKey(key);
	}

	public static boolean hasSpriteSheet(String key) {
		return sheets.containsKey(key);
	}

	public static boolean hasAnimation(String key) {
		return animations.containsKey(key);
	}

	public static boolean hasFont(String key) {
		return fonts.containsKey(key);
	}

	public static boolean hasMusic(String key) {
		return music.containsKey(key);
	}

	public static boolean hasSound(String key) {
		return sounds.containsKey(key);
	}

	public static boolean hasParameter(String key) {
		return parameters.containsKey(key);
	}

	public static Image getImage(String key) {
		Image image = images.get(key);
		if (image == null)
			throw new IllegalArgumentException("No image for key " + key + " "
					+ images.keySet());
		return image;
	}

	public static SpriteSheet getSpriteSheet(String key) {
		SpriteSheet spriteSheet = sheets.get(key);
		if (spriteSheet == null)
			throw new IllegalArgumentException("No spriteSheet for key " + key
					+ " " + sheets.keySet());
		return spriteSheet;
	}

	public static Animation getAnimation(String key) {
		Animation anim = animations.get(key);
		if (anim == null)
			throw new IllegalArgumentException("No Animation for key " + key
					+ " " + animations.keySet());
		return anim;
	}

	public static Font getFont(String key) {
		Font font = fonts.get(key);
		if (font == null)
			throw new IllegalArgumentException("No font for key " + key + " "
					+ fonts.keySet());
		return font;
	}

	public static Music getMusic(String key) {
		Music song = music.get(key);
		if (music == null)
			throw new IllegalArgumentException("No music for key " + key + " "
					+ music.keySet());
		return song;
	}

	public static Sound getSound(String key) {
		Sound sound = sounds.get(key);
		if (sound == null)
			throw new IllegalArgumentException("No sound for key " + key + " "
					+ sounds.keySet());
		return sound;
	}

	public static int getInt(String key) {
		return Integer.parseInt(getParameter(key));
	}

	public static double getDouble(String key) {
		return Double.parseDouble(getParameter(key));
	}

	public static float getFloat(String key) {
		return Float.parseFloat(getParameter(key));
	}

	public static String getParameter(String key) {
		String val = parameters.get(key);
		if (val == null)
			throw new IllegalArgumentException("No parameter for key " + key
					+ " " + parameters.keySet());
		return val;
	}

}
