package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import jdk.javadoc.internal.tool.Start;
import com.badlogic.gdx.math.Rectangle;

public class MainMenu implements Screen
{
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    private Sprite startButton = new Sprite(new Texture(Gdx.files.internal("startbutton2.png")));
    private int startButtonWidth = (int) startButton.getWidth();
    private int  startButtonHeight = (int) startButton.getHeight();
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private int startX = 0;
    private int startY = 0;
//    private int startY = (int) (height -startButton.getHeight());
    private int count = 0;
    private Vector3 touchPoint = new Vector3();

//    private Drawable drawable = new TextureRegionDrawable(new TextureRegion(playTexture);
//    private ImageButton playButton = new ImageButton(drawable);

    public MainMenu(final OraclesOdyssey gam) {
        game = gam;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
//            if (Gdx.input.getX() >= startX && Gdx.input.getX() <= startX + startButton.getWidth() && Gdx.input.getY() >= startY && Gdx.input.getY() <= startY + startButton.getHeight())
//            {
//
//                System.out.println("asdasdasd" + count);
//                count++;
//            }
            if (startButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y))
            {
                System.out.println("asdasdasd" + count);
                System.out.println("touchpointx: " + touchPoint.x);
                System.out.println("touchpointy: " + touchPoint.y);
                count++;
            }
            camera.project(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        }

//        }

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
