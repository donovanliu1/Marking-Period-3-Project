package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LoseScreen implements Screen {

    final OraclesOdyssey game;
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private Sprite loseSprite = new Sprite(new Texture(Gdx.files.internal("GameOver.jpg")));
    OrthographicCamera camera;

    public LoseScreen(final OraclesOdyssey game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(loseSprite, 0, 0, width, height);
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

