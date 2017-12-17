package gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import lib.FontManager;
import lib.FontManager.FontID;

public class Button extends AbstractGui{

	
	private RectangleShape rectangle;
	private Text text;
	private String textString;
	private FontManager fonts;
	private boolean pressed;
	
	public Button(RenderWindow window, FontManager fonts, String textString) {
		super(window);
		this.fonts = fonts;
		this.textString = textString;
		
		text = new Text();
		
		pressed = false;
	}

	public void init() {
		text.setFont(fonts.get(FontID.HACK));
		text.setCharacterSize(30);
		text.setColor(Color.WHITE);
		
		text.setString(textString);
		text.setPosition(10, 5);
		
		rectangle = new RectangleShape();
		rectangle.setSize(new Vector2f(text.getGlobalBounds().width + text.getPosition().x + 20, text.getGlobalBounds().height + text.getPosition().y + 20));
		rectangle.setFillColor(new Color(100, 50, 50, 100));
	}
	
	public void handleEvent(Event e) {
		pressed = false;
		if(e.type == Event.Type.MOUSE_BUTTON_PRESSED && e.asMouseButtonEvent().button == Mouse.Button.LEFT) {
			FloatRect localHitbox = rectangle.getGlobalBounds();
			FloatRect hitbox = getTransform().transformRect(localHitbox);
			
			if(hitbox.contains(window.mapPixelToCoords(Mouse.getPosition(window)))) {
				pressed = true;
			}
		}
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public void update(Time dt) {
		
	}
	
	
	@Override
	public void draw(RenderTarget window, RenderStates states) {
		RenderStates newStates = new RenderStates(Transform.combine(states.transform, this.getTransform()));
		
		window.draw(rectangle, newStates);
		window.draw(text, newStates);

	}
	
}
