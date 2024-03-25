package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

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
    private Sprite birdSprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
    private Sprite birdSprite2 = new Sprite(new Texture(Gdx.files.internal("startbutton2.png")));
    private Sprite birdSprite3 = new Sprite(new Texture(Gdx.files.internal("startButton.png")));
    private int menuButtonX = centerWidth(menuButtonSprite) - width/4;
    private int menuButtonY = centerHeight(menuButtonSprite) - height/4;
    private int creditButtonX = centerWidth(creditButtonSprite) + width/4;
    private int creditButtonY = centerHeight(creditButtonSprite) - height/4;
    private int[][] birdCoords = new int[][]{{centerWidth(birdSprite) + width/2 + 200, centerHeight(birdSprite) - height/4},
            {(int) birdSprite2.getWidth() * -1 - 600, centerHeight(birdSprite2) - height/8},
            {(int) birdSprite3.getWidth() * -1 - 550, centerHeight(birdSprite2) - height/4 + 600}};
    

    private int count = 0;
    private Random random = new Random();
    private Vector3 touchPoint = new Vector3();

//    private Drawable drawable = new TextureRegionDrawable(new TextureRegion(playTexture);
//    private ImageButton playButton = new ImageButton(drawable);

    public MainMenuScreen(final OraclesOdyssey gam)
    {
        game = gam;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, width, height);
    }
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin(); // STARTS
        renderBackground(); // METHOD CREATED TO RENDER BACKGROUND
        renderBirds();
        renderButtons(); // CHECK THE METHOD TO SEE THE RENDERS
        game.batch.end(); // ENDS







        if(Gdx.input.isTouched())
        {
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            manageMouseInputs();
            //camera.project(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
    }

    public void renderBackground()
    {
        game.batch.draw(startBackgroundSprite, 0, 0, width, height);
    }
    public void renderButtons()
    {
        game.batch.draw(startButtonSprite, startButtonX - startButtonSprite.getWidth()/3, startButtonY - startButtonSprite.getHeight()/3, startButtonSprite.getWidth() * 3, startButtonSprite.getHeight() * 3);
        game.batch.draw(menuButtonSprite, menuButtonX - startButtonSprite.getWidth()/3, menuButtonY - menuButtonSprite.getHeight()/3, menuButtonSprite.getWidth() * 3, menuButtonSprite.getHeight() * 3);
        game.batch.draw(creditButtonSprite, creditButtonX - creditButtonSprite.getWidth()/3, creditButtonY - creditButtonSprite.getHeight()/3, creditButtonSprite.getWidth() * 3, creditButtonSprite.getHeight() * 3);
    }
    public void renderBirds()
    {
        game.batch.draw(birdSprite, birdCoords[0][0], birdCoords[0][1]);
        game.batch.draw(birdSprite2, birdCoords[1][0], birdCoords[1][1]);
        game.batch.draw(birdSprite3, birdCoords[2][0], birdCoords[2][1]);
        updateBirds();

//        Rectangle birdRect = birdSprite.getBoundingRectangle().setPosition(birdSprite.getX(), birdSprite.getY());
//        Rectangle birdRect2 = birdSprite2.getBoundingRectangle().setPosition(birdSprite2.getX(), birdSprite2.getY());;
//        Rectangle birdRect3 = birdSprite3.getBoundingRectangle().setPosition(birdSprite.getX(), birdSprite.getY());;
//        System.out.println(birdRect);
//        System.out.println(birdRect2);
//        System.out.println(birdRect3);
//        boolean oneInTwo = birdRect.overlaps(birdRect2);
//        boolean oneInThree = birdRect.overlaps(birdRect3);
//        boolean twoInThree = birdRect2.overlaps(birdRect3);
        if(birdCoords[0][0] < birdSprite.getWidth() * -1 - 100)
        {
            birdCoords[0][0] = centerWidth(birdSprite) + width/2 + 200;
            birdCoords[0][1] = random.nextInt((int) (0 + birdSprite.getHeight()), (int) (height - birdSprite.getHeight()));
        }
        if (birdCoords[1][0] > centerWidth(birdSprite2) + width/2 + 200)
        {
            birdCoords[1][0] = (int) birdSprite2.getWidth() * -1 - 100;
            birdCoords[1][1] = random.nextInt((int) (0 + birdSprite2.getHeight()), (int) (height - birdSprite2.getHeight()));
        }
        if (birdCoords[2][0] > centerWidth(birdSprite3) + width/2 + 200)
        {
            birdCoords[2][0] = (int) birdSprite3.getWidth() * -1 - 100;
            birdCoords[2][1] = random.nextInt((int) (0 + birdSprite3.getHeight()), (int) (height - birdSprite3.getHeight()));
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
        startButtonSprite.getTexture().dispose();
        menuButtonSprite.getTexture().dispose();
        startBackgroundSprite.getTexture().dispose();
        creditButtonSprite.getTexture().dispose();
    }
    public int centerWidth(Sprite sprite, int dilation) {
        return (int) ((width - sprite.getWidth())/2) * dilation;
    }
    public int centerHeight(Sprite sprite, int dilation) {
        return (int) ((height - sprite.getHeight())/2) * dilation;
    }public int centerWidth(Sprite sprite) {
    return (int) ((width - sprite.getWidth())/2);
}
    public int centerHeight(Sprite sprite) {
        return (int) ((height - sprite.getHeight())/2);
    }

    public boolean inputDetected(Sprite sprite, int spritePosX, int spritePosY)
    {
        boolean xBound = touchPoint.x > spritePosX && touchPoint.x < spritePosX + sprite.getWidth();
        boolean yBound = touchPoint.y > spritePosY && touchPoint.y < spritePosY + sprite.getHeight();
        return xBound && yBound;
    }
    public void manageMouseInputs()
    {
        if(inputDetected(startButtonSprite, startButtonX, startButtonY)) {
            dispose();
            game.setScreen(new MainMenuScreen(game));
        }
        if (inputDetected(menuButtonSprite, menuButtonX, menuButtonY)) {
            System.out.println("menu");
        }
        if (inputDetected(creditButtonSprite, creditButtonX, creditButtonY)) {
            System.out.println("credit");
        }
    }
    public void updateBirds()
    {
        birdCoords[0][0] -= 10;
        birdCoords[1][0] += 8;
        birdCoords[2][0] += 12;
    }
}
