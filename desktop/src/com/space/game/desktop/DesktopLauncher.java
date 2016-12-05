package com.space.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.space.game.SpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new SpaceGame(), config);
	}
}
