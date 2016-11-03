package com.mandala.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
// HANDLES INPUT
public class InputHandler implements InputProcessor  {
	static final boolean DEV_MODE = true;
	private MandalaRenderer render;
	public InputHandler(MandalaRenderer r) {
		render = r;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("Touch Down", screenX + " , " + screenY + " , " + button);
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.log("Touch Drag", "" + screenX + " " + screenY + " " + pointer);
		render.draw(screenX, screenY);

		return true;
	}
	@Override
	public boolean keyDown(int keycode) {
		if(!DEV_MODE) {
			return false;
		}
		Gdx.app.log("Key Down", "" + keycode);
		switch (keycode) {
		case 19: // Up Arrow
			render.BRUSH_SIZE += 1;
			Gdx.app.log("Brush Size", "" + render.BRUSH_SIZE);
			break;
		case 20: // Down Arrow
			render.BRUSH_SIZE -= 1;
			Gdx.app.log("Brush Size", "" + render.BRUSH_SIZE);
			break;
		case 21: // Left Arrow
			render.BRUSH_INTERVAL -= 1;
			Gdx.app.log("Brush Interval", "" + render.BRUSH_INTERVAL);
			break;
		case 22: // Right Arrow
			render.BRUSH_INTERVAL += 1;
			Gdx.app.log("Brush Interval", "" + render.BRUSH_INTERVAL);
			break;
		case 31: // C
			render.DRAW_CIRCLES = !render.DRAW_CIRCLES;
			Gdx.app.log("Circles Toggled", "" + render.DRAW_CIRCLES);
			break;
		case 40: // L
			render.drawLines = !render.drawLines;
			Gdx.app.log("Lines Toggled", "" + render.drawLines);
			break;
		case 48: // T
			render.THICK_BRUSH = !render.THICK_BRUSH;
			Gdx.app.log("Thick Brush Toggled", "" + render.THICK_BRUSH);
			break;
		/*case 54: // Z
			render.setPixmap(render.undo);
			Gdx.app.log("Undo", "Complete");
			break;*/
		case 69: // Minus Sign -
			render.sides -= 1;
			Gdx.app.log("Sides", "" + render.sides);
			break;
		case 70: // Plus Sign +
			render.sides += 1;
			Gdx.app.log("Sides", "" + render.sides);
		}
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		render.isTouchedDown = false;
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
