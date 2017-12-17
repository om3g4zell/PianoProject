package piano;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public class PianoKey implements Drawable{
	
	private int key;
	private int octave;
	private boolean activeFlag;
	
	protected static final int WHITE_SIZE = 24;
	protected static final int BLACK_SIZE = 12;
	protected static final int PIANO_KEY_SIZE = 150;
	
	public enum TYPE {
		BLACK,
		WHITE
	}
	
	private TYPE type;
	
	private RectangleShape keyShape;

	public PianoKey(int x, int y, int key, int octave, TYPE type) {
		keyShape = new RectangleShape();
		activeFlag = false;
		if(type == TYPE.WHITE) {
			keyShape.setFillColor(Color.WHITE);
			keyShape.setSize(new Vector2f(WHITE_SIZE, PIANO_KEY_SIZE));
		}
		else {
			keyShape.setFillColor(Color.BLACK);
			keyShape.setSize(new Vector2f(BLACK_SIZE,PIANO_KEY_SIZE / 2));
		}
		keyShape.setPosition(new Vector2f(x, y));
		keyShape.setOutlineColor(Color.BLACK);
		keyShape.setOutlineThickness(1);
		
		this.type = type;
		this.key = key;
		this.octave = octave;
	}
	
	public void init() {
		
	}
	
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public boolean isActive() {
		return activeFlag;
	}
	
	@Override
	public void draw(RenderTarget window, RenderStates states) {
		window.draw(keyShape);
	}
	
	public int getOctave() {
		return octave;
	}
	
	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}


	public TYPE getType() {
		return type;
	}


	public void setType(TYPE type) {
		this.type = type;
	}


	public RectangleShape getKeyShape() {
		return keyShape;
	}


	public void setKeyShape(RectangleShape keyShape) {
		this.keyShape = keyShape;
	}

}
