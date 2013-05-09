package edu.mhs.wombat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SpriteSheetFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLResourceLoader {
	private String baseDir;
	private Element rootElement;

	private ArrayList<Element> sound;
	private ArrayList<Element> music;
	private ArrayList<Element> image;
	private ArrayList<Element> sheet;
	private ArrayList<Element> anim;
	private ArrayList<Element> angelcodefont;
	private ArrayList<Element> unicodefont;
	private ArrayList<Element> spritesheetfont;
	private ArrayList<Element> param;

	public void load(InputStream in) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(in);
			this.rootElement = document.getDocumentElement();
			this.validateResourceFile();
			ArrayList<Element> elements = this.getElementsByTagName("basedir");
			this.setBaseDirectory(elements.get(0).getAttribute("path"));

			this.sound = this.getElementsByTagName("sound");
			this.music = this.getElementsByTagName("music");
			this.image = this.getElementsByTagName("image");
			this.sheet = this.getElementsByTagName("sheet");
			this.anim = this.getElementsByTagName("anim");

			this.angelcodefont = this.getElementsByTagName("angelcodefont");

			this.unicodefont = this.getElementsByTagName("unicodefont");

			this.spritesheetfont = this.getElementsByTagName("spritesheetfont");

			this.param = this.getElementsByTagName("param");

		} catch (Exception e) {
			Log.error(e);
			throw new IOException("Unable to load resource configuration file",
					e);
		}
	}

	public int remainingElements() {
		return this.sound.size() + this.music.size() + this.image.size()
				+ this.sheet.size() + this.anim.size()
				+ this.angelcodefont.size() + this.unicodefont.size()
				+ this.spritesheetfont.size() + this.param.size();
	}

	public String loadNext() throws SlickException {
		String name = "";
		if (this.sound.size() > 0) {
			Element e = this.sound.remove(0);
			this.loadSound(e);
			name = e.getAttribute("key");
		} else if (this.music.size() > 0) {
			Element e = this.music.remove(0);
			this.loadMusic(e);
			name = e.getAttribute("key");
		} else if (this.image.size() > 0) {
			Element e = this.image.remove(0);
			this.loadImage(e);
			name = e.getAttribute("key");
		} else if (this.unicodefont.size() > 0) {
			Element e = this.unicodefont.remove(0);
			this.loadUnicodeFont(e);
			name = e.getAttribute("key");
		} else if (this.sheet.size() > 0) {
			Element e = this.sheet.remove(0);
			this.loadSpriteSheet(e);
			name = e.getAttribute("key");
		} else if (this.anim.size() > 0) {
			Element e = this.anim.remove(0);
			this.loadAnimation(e);
			name = e.getAttribute("key");
		} else if (this.angelcodefont.size() > 0) {
			Element e = this.angelcodefont.remove(0);
			this.loadAngelCodeFont(e);
			name = e.getAttribute("key");
		} else if (this.spritesheetfont.size() > 0) {
			Element e = this.spritesheetfont.remove(0);
			this.loadSpriteSheetFont(e);
			name = e.getAttribute("key");
		} else if (this.param.size() > 0) {
			Element e = this.param.remove(0);
			this.loadParameter(e);
			name = e.getAttribute("key");
		} else {
			return "finalizing";
		}
		return name;
	}

	private void validateResourceFile() throws IOException {
		if (!this.rootElement.getNodeName().equals("resources")) {
			throw new IOException("Not a resource configuration file");
		}
	}

	private ArrayList<Element> getElementsByTagName(String elementName) {
		NodeList nodes = this.rootElement.getElementsByTagName(elementName);
		int nodeCount = nodes.getLength();

		if (nodeCount != 0) {
			ArrayList<Element> elements = new ArrayList<Element>();

			for (int i = 0; i < nodeCount; i++) {
				elements.add((Element) nodes.item(i));
			}
			return elements;
		} else {
			return new ArrayList<Element>();
		}
	}

	private void setBaseDirectory(String baseDirectory) throws SlickException {
		Log.debug("Setting base directory to " + baseDirectory);
		if (baseDirectory == null || baseDirectory.isEmpty()) {
			throw new SlickException("Could not find required BaseDir element.");
		}
		this.baseDir = baseDirectory;
		if (!this.baseDir.endsWith("/")) {
			this.baseDir += "/";
		}
	}

	private void loadSound(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "file");
		String key = element.getAttribute("key");
		String file = element.getAttribute("file");
		Log.debug(this.formatLoadMsg("sound", key, file));
		ResourceManager.addSound(key, new Sound(this.baseDir + file));
	}

	private void loadMusic(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "file");
		String key = element.getAttribute("key");
		String file = element.getAttribute("file");
		Log.debug(this.formatLoadMsg("music", key, file));
		ResourceManager.addMusic(key, new Music(this.baseDir + file));
	}

	private void loadImage(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "file");
		String key = element.getAttribute("key");
		String file = element.getAttribute("file");
		Color transparentColor = null;

		if (element.hasAttribute("transparentColor")) {
			transparentColor = this.decodeColor(element);
		}

		Image image;
		try {
			image = new Image(this.baseDir + file, transparentColor);
		} catch (SlickException ex) {
			// To support large textures on older graphic cards
			Log.info("Using BigImage.");
			image = new BigImage(this.baseDir + file);
		}
		Log.debug(this.formatLoadMsg("image", key, file));
		ResourceManager.addImage(key, image);
	}

	private void loadSpriteSheet(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "file", "width", "height");
		String key = element.getAttribute("key");
		String file = element.getAttribute("file");
		int width = this.parseIntAttribute(element, "width");
		int height = this.parseIntAttribute(element, "height");
		Color transparentColor = null;

		if (element.hasAttribute("transparentColor")) {
			transparentColor = this.decodeColor(element);
		}

		Log.debug(String.format(
				"Loading spritesheet key=%s file=%s width=%s height=%s", key,
				file, width, height));
		ResourceManager.addSpriteSheet(key, new SpriteSheet(
				this.baseDir + file, width, height, transparentColor));
	}

	private Color decodeColor(Element element) {
		String transparentColorAsText = element
				.getAttribute("transparentColor");
		if (transparentColorAsText != null && !transparentColorAsText.isEmpty()) {
			return Color.decode(transparentColorAsText);
		} else {
			return null;
		}
	}

	private void loadAnimation(Element element) {
		this.checkRequiredAttributes(element, "key", "imgName", "frameDuration");
		String key = element.getAttribute("key");
		String imgName = element.getAttribute("imgName");
		int frameDuration = this.parseIntAttribute(element, "frameDuration");
		boolean flipHorizontal = false, flipVertical = false;
		int[] frames;
		int row = 0;
		boolean loop = true;

		if (!ResourceManager.hasSpriteSheet(imgName)) {
			throw new IllegalArgumentException("Animation " + key
					+ " needs spritesheet " + imgName
					+ " but it has not been loaded.");
		}
		SpriteSheet sheet = ResourceManager.getSpriteSheet(imgName);

		if (element.hasAttribute("frames")) {
			String framesAsText = element.getAttribute("frames");
			frames = this.readFrameIndexes(framesAsText);
		} else {
			frames = new int[sheet.getHorizontalCount()];
			for (int i = 0; i < sheet.getHorizontalCount(); i++) {
				frames[i] = i;
			}
		}
		if (element.hasAttribute("flipHorizontal")) {
			flipHorizontal = this.parseBooleanAttribute(element,
					"flipHorizontal");
		}
		if (element.hasAttribute("flipVertical")) {
			flipVertical = this.parseBooleanAttribute(element, "flipVertical");
		}
		if (element.hasAttribute("row")) {
			row = this.parseIntAttribute(element, "row");
		}
		if (element.hasAttribute("loop")) {
			loop = this.parseBooleanAttribute(element, "loop");
		}

		Animation anim = this.buildAnimationFromFrames(sheet, row, frames,
				frameDuration, flipHorizontal, flipVertical);
		anim.setLooping(loop);
		Log.debug(this.formatLoadMsg("animation", key, "spritesheet", imgName));
		ResourceManager.addAnimation(key, anim);
	}

	private int[] readFrameIndexes(String framesAsText) {
		StringTokenizer tokenizer = new StringTokenizer(framesAsText, ",");

		int frames[] = new int[tokenizer.countTokens()];
		for (int i = 0; tokenizer.hasMoreTokens(); i++) {
			frames[i] = Integer.parseInt(tokenizer.nextToken().trim());
		}
		return frames;
	}

	/**
	 * Create an animation from a spritesheet row.
	 * 
	 * @param sheet
	 *            The spritesheet that contains the individual images.
	 * @param row
	 *            The vertical index in the spritesheet to read the animation
	 *            from, zero based.
	 * @param frames
	 *            The horizontal indexes of the frames that need to be included
	 *            in the animation, zero based.
	 * @param frameDuration
	 *            The duration of 1 frame in the resulting animation.
	 * @param flipHorizontal
	 *            If true flips each frame in the animation on the x axis
	 * @param flipVertical
	 *            If true flips each frame in the animation on the y axis
	 * @return An animation parsed from the spritesheet
	 */
	private Animation buildAnimationFromFrames(SpriteSheet sheet, int row,
			int[] frames, int frameDuration, boolean flipHorizontal,
			boolean flipVertical) {
		Animation animation = new Animation(false);
		for (int frameIndex : frames) {
			Image img = sheet.getSubImage(frameIndex, row);

			if (flipHorizontal || flipVertical) {
				img = img.getFlippedCopy(flipHorizontal, flipVertical);
			}
			animation.addFrame(img, frameDuration);
		}
		return animation;
	}

	private void loadAngelCodeFont(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "fontFile", "imageFile");
		String key = element.getAttribute("key");
		String fontFile = element.getAttribute("fontFile");
		String imageFile = element.getAttribute("imageFile");

		Log.debug(String.format(
				"Loading Angelcode font key=%s imagefile=%s fontfile=%s", key,
				imageFile, fontFile));
		AngelCodeFont font = new AngelCodeFont(this.baseDir + fontFile,
				this.baseDir + imageFile, true);
		ResourceManager.addFont(key, font);
	}

	@SuppressWarnings("unchecked")
	private void loadUnicodeFont(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "file");
		String key = element.getAttribute("key");
		String ttfFile = element.getAttribute("file");
		int fontSize = 12;

		if (element.hasAttribute("fontSize")) {
			fontSize = this.parseIntAttribute(element, "fontSize");
		}

		Log.debug(this.formatLoadMsg("Unicode font", key, ttfFile));
		UnicodeFont unicodeFont = new UnicodeFont(this.baseDir + ttfFile,
				fontSize, false, false);
		unicodeFont.getEffects().add(new ColorEffect());
		unicodeFont.addAsciiGlyphs();
		unicodeFont.loadGlyphs();
		unicodeFont.setDisplayListCaching(true);
		ResourceManager.addFont(key, unicodeFont);
	}

	private void loadSpriteSheetFont(Element element) {
		this.checkRequiredAttributes(element, "key", "file", "firstchar");
		String key = element.getAttribute("key");
		String imgName = element.getAttribute("file");
		char startingChar = element.getAttribute("firstchar").charAt(0);

		if (!ResourceManager.hasSpriteSheet(imgName)) {
			throw new IllegalArgumentException("Spritesheetfont " + key
					+ " needs spritesheet " + imgName
					+ " but it has not been loaded.");
		}
		SpriteSheet sheet = ResourceManager.getSpriteSheet(imgName);

		Log.debug(this.formatLoadMsg("SpriteSheet font", key, imgName));
		SpriteSheetFont font = new SpriteSheetFont(sheet, startingChar);
		ResourceManager.addFont(key, font);
	}

	private void loadParameter(Element element) throws SlickException {
		this.checkRequiredAttributes(element, "key", "value");
		String key = element.getAttribute("key");
		String value = element.getAttribute("value");

		Log.debug(this.formatLoadMsg("Parameter", key, "value", value));
		ResourceManager.setParameter(key, value);
	}

	private void checkRequiredAttributes(Element element, String... attributes) {
		boolean requiredAttributeMissing = false;
		for (String attribute : attributes) {
			if (!element.hasAttribute(attribute)) {
				requiredAttributeMissing = true;
			}
		}

		if (requiredAttributeMissing) {
			StringBuffer missingAttributesMessage = new StringBuffer(
					"Required attribute(s) missing: ");
			for (String attribute : attributes) {
				if (!element.hasAttribute(attribute)) {
					missingAttributesMessage.append(attribute);
					missingAttributesMessage.append(", ");
				}
			}
			// Remove trailing ", "
			int length = missingAttributesMessage.length();
			missingAttributesMessage.delete(length - 2, length);
			missingAttributesMessage.append(" from element ").append(
					element.getNodeName());
			throw new IllegalArgumentException(
					missingAttributesMessage.toString());
		}
	}

	private int parseIntAttribute(Element element, String attributeName) {
		return Integer.parseInt(element.getAttribute(attributeName));
	}

	private boolean parseBooleanAttribute(Element element, String attributeName) {
		return Boolean.parseBoolean(element.getAttribute(attributeName));
	}

	private String formatLoadMsg(String type, String key, String value) {
		return this.formatLoadMsg(type, key, "file", value);
	}

	private String formatLoadMsg(String type, String key, String valueName,
			String value) {
		StringBuffer buf = new StringBuffer("Loading ");
		buf.append(type).append(" key=").append(key).append(" ")
				.append(valueName).append("=").append(value);
		return buf.toString();
	}
}