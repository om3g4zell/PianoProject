package gui;

import java.util.ArrayList;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Time;
import org.jsfml.window.event.Event;

public class GuiPool extends AbstractGui{

	private ArrayList<AbstractGui> guis;
	
	public GuiPool(RenderWindow window) {
		super(window);
		guis = new ArrayList<>();
	}

	public void addElement(AbstractGui gui) {
		guis.add(gui);
	}
	
	@Override
	public void init() {
		for(AbstractGui gui : guis) {
			gui.init();
		}
	}
	
	@Override
	public void handleEvent(Event e) {
		for(AbstractGui gui : guis) {
			gui.handleEvent(e);
		}
	}

	@Override
	public void update(Time dt) {
		for(AbstractGui gui : guis) {
			gui.update(dt);;
		}
	}
	
	@Override
	public void draw(RenderTarget window, RenderStates arg1) {
		for(AbstractGui gui : guis) {
			window.draw(gui);
		}
	}
}
