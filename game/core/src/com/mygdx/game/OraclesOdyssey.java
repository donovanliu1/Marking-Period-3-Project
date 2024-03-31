package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class OraclesOdyssey extends Game {
	SpriteBatch batch;
	BitmapFont font;
	Plane plane;

	public void create()
	{
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		plane = new PlayerPlane(100, 100, 100, 100);
		this.setScreen(new MainMenuScreen(this));
	}

	public void render()
	{
		super.render(); // important!
	}

	public void dispose()
	{
		batch.dispose();
		font.dispose();
	}
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 480);
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
