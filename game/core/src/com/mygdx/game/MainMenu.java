package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.javadoc.internal.tool.Start;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu implements Screen
{
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    private Texture startButton = new Texture(Gdx.files.internal("startbutton.png"));
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();

    public MainMenu(final OraclesOdyssey gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin(); // STARTS
        game.batch.draw(startButton, width/2 - 181, height/3);
//        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end(); // ENDS
        Rectangle rect = new Rectangle(0,0,1,1);
        if (Gdx.input.isTouched()) {
            rect.setPosition(Gdx.input.getX(), Gdx.input.getY());
            if (rect.overlaps(startButton.getB))
//            game.setScreen(new GameScreen(game));
//            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
