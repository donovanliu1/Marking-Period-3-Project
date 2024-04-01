package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    public static int width = Gdx.graphics.getWidth(); // const since it doesnt change
    public static int height = Gdx.graphics.getHeight(); // const since it doesnt change
    private Sprite testingsprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
    private Sprite gameBackground = new Sprite(new Texture(Gdx.files.internal("startbackground.jpg"))); // Change to game background
    public GameScreen(final OraclesOdyssey gam) // The create class
    {
        this.game = gam;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, width, height);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None); // We dont want the cursor to show in the game
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.begin();
        game.batch.draw(gameBackground, 0, 0, width, height);
        game.plane.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
