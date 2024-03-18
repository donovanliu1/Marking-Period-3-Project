package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen
{
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    private Sprite startButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png")));
    private Sprite startBackgroundSprite = new Sprite(new Texture(Gdx.files.internal("startbackground.jpg")));
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private int startButtonX = centerWidth(startButtonSprite);
    private int startButtonY = centerHeight(startButtonSprite) + height/4;
    // CHANGE THE BELOW VARIABLES WHEN WE FIND THE SPRITE WE USE
    private Sprite menuButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png")));
    private Sprite creditButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png"))); // used to cite sources? idk maybe change to like tutorial

    private int menuButtonX = centerWidth(menuButtonSprite) - width/4;
    private int menuButtonY = centerHeight(menuButtonSprite) - height/4;
    private int creditButtonX = centerWidth(creditButtonSprite) + width/4;
    private int creditButtonY = centerHeight(creditButtonSprite) - height/4;
    private int count = 0;
    private Vector3 touchPoint = new Vector3();

//    private Drawable drawable = new TextureRegionDrawable(new TextureRegion(playTexture);
//    private ImageButton playButton = new ImageButton(drawable);

    public MainMenuScreen(final OraclesOdyssey gam) {
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
        renderBackground(); // METHOD CREATED TO RENDER BACKGROUND
        renderButtons(); // CHECK THE METHOD TO SEE THE RENDERS
        game.batch.end(); // ENDS
//        Rectangle rect = new Rectangle(0,0,1,1);

//        System.out.println(width);
//        System.out.println(height);

        if(Gdx.input.isTouched())
        {
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
//            if (Gdx.input.getX() >= startButtonX && Gdx.input.getX() <= startButtonX + startButtonSprite.getWidth() && Gdx.input.getY() >= startButtonY && Gdx.input.getY() <= startButtonY + startButtonSprite.getHeight())
//            {
//
//                System.out.println("asdasdasd" + count);
//                count++;
//            }
            startButtonSprite.getBoundingRectangle();
            if (startButtonSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y))
            {
                System.out.println("asdasdasd" + count);
                System.out.println("touchpointx: " + touchPoint.x);
                System.out.println("touchpointy: " + touchPoint.y);
                count++;
                dispose();
            }
            camera.project(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
    }

    public void renderBackground() {
        game.batch.draw(startBackgroundSprite, 0, 0, width, height);
    }
    public void renderButtons() {
        game.batch.draw(startButtonSprite, startButtonX, startButtonY);
        game.batch.draw(menuButtonSprite, menuButtonX, menuButtonY);
        game.batch.draw(creditButtonSprite, creditButtonX, creditButtonY);
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
    public int centerWidth(Sprite sprite) {
        return (int) ((width - sprite.getWidth())/2);
    }
    public int centerHeight(Sprite sprite) {
        return (int) ((height - sprite.getHeight())/2);
    }
}
