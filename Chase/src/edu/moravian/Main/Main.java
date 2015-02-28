package edu.moravian.Main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.moravian.Game.Game;

public class Main {

	private final static int screenWidth = 800;
	private final static int screenHeight = 600;
	
	public static void main(String[] args) {
		try
        {
            AppGameContainer agc = new AppGameContainer(new Game(screenWidth, screenHeight), screenWidth, screenHeight, false);
            
            agc.start();
        }
        catch (SlickException ex)
        {
            System.out.println("Error starting game");
        }
	}

}
