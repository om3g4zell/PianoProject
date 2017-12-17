package piano;

import java.util.ArrayList;

import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Time;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import piano.PianoKey.TYPE;
import synthetizer.SynthetizerManager;

public class Piano extends BasicTransformable implements Drawable{

	private ArrayList<PianoKey> keys;
	private RenderWindow window;
	private SynthetizerManager sm;
	private PianoKey lastNote;
	
	public Piano(RenderWindow window, SynthetizerManager sm) {
		lastNote = null;
		this.keys = new ArrayList<>();
		this.sm = sm;
		this.window = window;
	}
	
	public void init() {
		int keyNumber = 0;
		int octaveNumber = 0;
		int value = 0;
		for(int i = 0; i <= 1280 / PianoKey.WHITE_SIZE;) {
			int x = 0;
			if(keyNumber == 1 || keyNumber == 3 || keyNumber == 6 || keyNumber == 8 ||keyNumber == 10) {
				switch(keyNumber) {
				case 1 : x = (( 1 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE) - (PianoKey.BLACK_SIZE / 2);
					break;
				case 3: x = ((2 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE) - (PianoKey.BLACK_SIZE / 2);
					break;
				case 6 : x = ((4 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE) - (PianoKey.BLACK_SIZE / 2);
					break;
				case 8 :  x = ((5 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE) - (PianoKey.BLACK_SIZE / 2);
					break;
				case 10 : x = ((6 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE) - (PianoKey.BLACK_SIZE / 2);
					break;
				}
				keys.add(new PianoKey(x, (int)this.getPosition().y, keyNumber, octaveNumber, value, PianoKey.TYPE.BLACK));
			}
			else {
				if(keyNumber == 0) x = (octaveNumber * 7) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 2) x = (1 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 4)  x = (2 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 5)  x = (3 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 7)  x = (4 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 9)  x = (5 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				else if(keyNumber == 11)  x = (6 + (octaveNumber * 7)) * PianoKey.WHITE_SIZE;
				keys.add(new PianoKey(x, (int)this.getPosition().y, keyNumber, octaveNumber, value, PianoKey.TYPE.WHITE));
				i++;
			}
			keyNumber++;
			if(keyNumber == 12) {
				keyNumber = 0;
				octaveNumber++;
			}
			value++;
		}
	}
	
	public void update(Time dt) {
		
	}
	
	public void handleEvent(Event e) {
		if(e.type == Event.Type.MOUSE_BUTTON_PRESSED && e.asMouseButtonEvent().button == Mouse.Button.LEFT) {
			for(PianoKey pk : keys) {
				if(pk.getType() == TYPE.WHITE) continue;
				FloatRect localHitbox = pk.getKeyShape().getGlobalBounds();
				
				if(localHitbox.contains(window.mapPixelToCoords(Mouse.getPosition(window)))) {
					pk.setActiveFlag(true);
					lastNote = pk;
					sm.playNote(pk.getValue(), 0);
					return;
				}
			}
			for(PianoKey pk : keys) {
				if(pk.getType() == TYPE.BLACK) continue;
				FloatRect localHitbox = pk.getKeyShape().getGlobalBounds();
				
				if(localHitbox.contains(window.mapPixelToCoords(Mouse.getPosition(window)))) {
					pk.setActiveFlag(true);
					lastNote = pk;
					sm.playNote(pk.getValue(), 0);
					return;
				}
			}
		}
		else if(e.type == Event.Type.MOUSE_BUTTON_RELEASED && e.asMouseButtonEvent().button == Mouse.Button.LEFT) {
			if(lastNote != null) {
				lastNote.setActiveFlag(false);
				sm.stopNote(lastNote.getValue(), 0);
			}
		}
	}

	@Override
	public void draw(RenderTarget window, RenderStates states) {
		RenderStates newStates = new RenderStates(Transform.combine(states.transform, this.getTransform()));
		
		ArrayList<PianoKey> whiteKey = new ArrayList<>();
		ArrayList<PianoKey> blackKey = new ArrayList<>();
		
		for(PianoKey pk : keys) {
			if(pk.getType().equals(PianoKey.TYPE.WHITE)) whiteKey.add(pk);
			else blackKey.add(pk);
		}
		for(PianoKey wpk : whiteKey) {
			window.draw(wpk, newStates);
		}
		for(PianoKey bpk : blackKey) {
			window.draw(bpk, newStates);
		}
		
	}
}
