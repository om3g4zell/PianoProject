package application;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Time;
import org.jsfml.window.event.Event;

import gui.GuiPool;
import lib.FontManager;
import piano.Piano;
import piece.Piece;
import synthetizer.SynthetizerManager;


public class Game {

	private RenderWindow window;
	private boolean isRunning;
	private FontManager fonts;
	private GuiPool guis;
	private SynthetizerManager syntheManager;
	private Piano piano;
	
	public Game(RenderWindow window) {
		this.fonts = new FontManager();
		this.guis = new GuiPool(window);
		this.syntheManager = new SynthetizerManager();

		this.window = window;
		this.piano = new Piano(window, syntheManager);
		this.isRunning = true;
	}
	
	public void init() {
		
		fonts.load();
		syntheManager.init();
		//syntheManager.changeInstrument(0, 24);
		piano.setPosition(0, (800 - 150));
		piano.init();
		
		guis.init();	 
		Piece test = new Piece();
		//test.loadPieceFromMidi("res/musics/naruto.mid");
		//System.out.println(test);
	}
	
	public void update(Time dt) {		
		this.guis.update(dt);
		piano.update(dt);
	}
	
	public void render() {
		window.clear(new Color(50, 50, 50));
		
		//Render here
		window.draw(guis);
		window.draw(piano);
		//
		window.display();
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public void stop() {
		syntheManager.close();
	}

	public void handleEvent(Event event) {
		this.guis.handleEvent(event);
		piano.handleEvent(event);
	}
}
