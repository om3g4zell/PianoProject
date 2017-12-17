package main;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import application.Game;

public class Main {
	
	public static void main(String args[] ) {
		final float TickRate = 1/60.f;
		final Time TickRateTime = Time.getSeconds(TickRate);
	   int ticks = 0;
	   int frames = 0;

	   final String TITLE  = "Free synthesia";

	   RenderWindow window = new RenderWindow(new VideoMode(1280, 800), TITLE);

	   Game game = new Game(window);
	   game.init();

	   Clock clientClock = new Clock();
	   Time elapsed = Time.ZERO;
	   Time dt = Time.ZERO;
	   Time fpsTimer = Time.ZERO;

	    // Start the game loop
	    while (window.isOpen())
	    {
	        if(!game.isRunning()) {
	            window.close();
	            game.stop();
	        }

	        // Process events
	        for(Event event : window.pollEvents()) {
	            if(event.type == Event.Type.CLOSED) {
	                //The user pressed the close button
	                window.close();
	                game.stop();
	            }
	            game.handleEvent(event);
	        }
	        dt = clientClock.restart();
	        elapsed = Time.add(elapsed, dt);

	        if(elapsed.asSeconds() > TickRate)
	        {
	            elapsed = Time.sub(elapsed, TickRateTime);
	            game.update(TickRateTime);
	            ticks++;
	        }

	        game.render();
	        frames++;

	        fpsTimer = Time.add(fpsTimer, dt);

	        if(fpsTimer.asSeconds() > 1)
	        {
	            window.setTitle(TITLE + " ticks : " + ticks + " , FPS : " + frames);
	            ticks = 0;
	            frames = 0;
	            fpsTimer = Time.ZERO;
	        }
	    }
	}
	
}
