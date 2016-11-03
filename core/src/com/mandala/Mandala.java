package com.mandala;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mandala.helpers.MandalaScreen;


public class Mandala extends Game {
	@Override
	public void create() {
		Gdx.app.log("Event", "Mandala Created");
		setScreen(new MandalaScreen());
	}
	@Override
	public void dispose() {
		super.dispose();
	}
	
	
}
