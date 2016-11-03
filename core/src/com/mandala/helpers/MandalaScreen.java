package com.mandala.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mandala.helpers.MandalaRenderer;
import com.mandala.helpers.InputHandler;
public class MandalaScreen implements Screen {
	private MandalaRenderer render;
	private float runtime = 0;
	public MandalaScreen() {
		render = new MandalaRenderer();
		Gdx.input.setInputProcessor(new InputHandler(render));
	}
	@Override
	public void render(float delta) {
		runtime += delta;
		render.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("Resize", width + " , " + height);
	}
	@Override
	public void show() {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
}
