package gui;

import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Time;
import org.jsfml.window.event.Event;

public abstract class AbstractGui extends BasicTransformable implements Drawable{
	
	protected RenderWindow window;
	
	public AbstractGui(RenderWindow window) {
		this.window = window;
	}
	
	abstract public void init();
	
	abstract public void handleEvent(Event e);
	
	abstract public void update(Time dt);
	
	
}
