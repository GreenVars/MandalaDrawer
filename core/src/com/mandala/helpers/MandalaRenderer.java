package com.mandala.helpers;
// http://www.mandaladrawerer.com/
import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MandalaRenderer {
	public boolean THICK_BRUSH = true;
	public boolean DRAW_CIRCLES = false;
	public boolean drawLines = false;
	public int BRUSH_SIZE = 5;
	public int BRUSH_INTERVAL = 1;
	public int sides = 10;
	private int count = 0;
	private int width;
	private int height;
	private float shade = .2f;
	public boolean isTouchedDown = false;
	private Point[] lastPoints;
	private OrthographicCamera cam;
	private ShapeRenderer shapeR;
	private SpriteBatch batcher;
	private Pixmap pixmap;
	private Texture pixmapTexture;
	//public Pixmap undo;
	public MandalaRenderer() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		shapeR = new ShapeRenderer();
		shapeR.setProjectionMatrix(cam.combined);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		pixmap = new Pixmap(width, height, Format.RGBA8888);
		//pixmap.setColor(1, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		pixmapTexture = new Texture(pixmap, Format.RGB888, false);
	}
	public void render(float delta) {
		Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		batcher.draw(pixmapTexture, 0, 0);
		batcher.end();
		pixmapTexture.dispose();
		pixmapTexture = new Texture(pixmap, Format.RGB888, false);
	}
	public void draw(int x, int y) {
		pixmap.setColor(1, 1, 1, shade); // XXX SET COLOR
		//undo = pixmap;
		//pixmap.drawPixel(x, y);
		count += 1;
		if(count % 40 == 0) {
			//BRUSH_SIZE += 1;
			//sides += 2;
			//isTouchedDown = false;
			//drawLines = !drawLines;
		}
		Point[] points = mandalaPoints(x,y);
		for(int i = 0; i < sides; i++) {
			Point m = points[i];
			pixmap.drawPixel(m.x, m.y);
			if(isTouchedDown && drawLines) {
				Point lastPoint = lastPoints[i];
				pixmap.drawLine(m.x, m.y, lastPoint.x, lastPoint.y);
				if(THICK_BRUSH) {
					for(int offset = 1; offset < BRUSH_SIZE; offset++) {
						pixmap.drawLine(m.x+offset, m.y+offset, 
								lastPoint.x+offset, lastPoint.y+offset);
					}
				}
				if(DRAW_CIRCLES) {
					pixmap.fillCircle(m.x, m.y, BRUSH_SIZE);
				}
				lastPoints[i] = m;
			}
			
		}
		lastPoints = points;
		isTouchedDown = true;
	}
	private Point[] mandalaPoints(int x, int y) {
	    Point[] points = new Point[sides];
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		Point center = new Point(width/2, height/2);
		double radius = distance(center, new Point(x,y));
		double thetaOffset = findTheta(x, y);
		for(int i = 0; i < sides; i++) {
			double theta = (i * (2*Math.PI)) / sides;
			int newX = (int) ((Math.cos(theta + thetaOffset) * radius) + center.x);
			int newY = (int) ((Math.sin(theta + thetaOffset) * radius) + center.y);
			points[i] = new Point(newX, newY);
		}
		return points;
	}
	public double findTheta(int screenX, int screenY) {
		double theta = 0;
		double midX = Gdx.graphics.getWidth()/2;
		double midY = Gdx.graphics.getHeight()/2;
		if(Math.abs(screenX - midX) > 0) { // Not vertical slope
			double slope = (screenY - midY) / (screenX - midX);
			if(screenX > midX && slope >= 0) { // Quadrant 1
				theta = Math.atan(slope);
			} else if (screenX < midX) { // Quadrant 2 or 3
				theta = Math.PI + Math.atan(slope);
			} else if (screenX > midX && slope < 0) { // Quadrant 4
				theta = 2*Math.PI + Math.atan(slope);
			}
		} else {
			if(screenY > midY) {
				theta = Math.PI / 2.0; // 90 Degrees
			} else if (screenY < midY) {
				theta = 3*Math.PI / 2.0; // 270 Degrees
			} else {
				Gdx.app.log("Error", "Clicked on Origin Perfectly");
				theta = 0;
			}
		}
		return theta;
	}
	public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y,2));
	}
	public void setSides(int n) {
		this.sides = n;
	}
}
