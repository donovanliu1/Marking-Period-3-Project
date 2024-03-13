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
    private Texture startButton = new Texture(Gdx.files.internal("startbutton2.png"));
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
        game.batch.draw(startButton, startX, startY);
//        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end(); // ENDS
//        Rectangle rect = new Rectangle(0,0,1,1);

//        System.out.println(width);
//        System.out.println(height);

        if(Gdx.input.isTouched())
        {
            System.out.println("\nstartx: " + startX);
            System.out.println("starty: " + startY);
            System.out.println("input x: " + Gdx.input.getX());
            System.out.println("input y: " + Gdx.input.getY());
            System.out.println("startWidth: " + startButtonHeight);
            System.out.println("startHeight: " + startButtonHeight);

            if (Gdx.input.getX() >= startX && Gdx.input.getX() <= startX + startButtonWidth && Gdx.input.getY() >= startY && Gdx.input.getY() <= startY + startButtonHeight)
                System.out.println("asdasdasd");
            dispose();
        }
        if (Gdx.input.isTouched()) {
//            rect.setPosition(Gdx.input.getX(), Gdx.input.getY());

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
