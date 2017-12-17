package lib;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.Font;

public class FontManager {

	private final String fontPath = "res/fonts/"; 
	
	public enum FontID {
		OPTMIMUS,
		HACK
	}
	
	private HashMap<FontID, Font> fonts;
	
	public FontManager() {
		fonts = new HashMap<>();
	}
	
	public void load() {
		
		Font font = new Font();
		try {
			font.loadFromFile(Paths.get(fontPath + "optimus.ttf"));
			fonts.put(FontID.OPTMIMUS, font);
			font = new Font();
			font.loadFromFile(Paths.get(fontPath + "hack.ttf"));
			fonts.put(FontID.HACK, font);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Font get(FontID id) {
		return fonts.get(id);
	}
}
