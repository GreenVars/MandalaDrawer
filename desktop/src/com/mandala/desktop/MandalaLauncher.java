package com.mandala.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mandala.Mandala;

public class MandalaLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 550;
		config.width = 550;
		new LwjglApplication(new Mandala(), config);
	}
}
