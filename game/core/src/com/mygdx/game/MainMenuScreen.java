package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

public class MainMenuScreen implements Screen
{
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("Music/mainMenuMusic.mp3"));
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    // CHANGE THE BELOW VARIABLES WHEN WE FIND THE SPRITE WE USE
    private Sprite startButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png")));
    private Sprite startBackgroundSprite = new Sprite(new Texture(Gdx.files.internal("startbackground.jpg")));
    private Sprite titleSprite = new Sprite(new Texture(Gdx.files.internal("wip.png")));
//    private Sprite menuButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png")));
//    private Sprite creditButtonSprite = new Sprite(new Texture(Gdx.files.internal("startbutton.png"))); // used to cite sources? idk maybe change to like tutorial
    private Sprite birdSprite = new Sprite(new Texture(Gdx.files.internal("birdflying/BirdFlying_0.png")));
    private Sprite birdSprite2 = new Sprite(new Texture(Gdx.files.internal("birdflying/BirdFlying_0.png")));
    private Sprite birdSprite3 = new Sprite(new Texture(Gdx.files.internal("birdflying/BirdFlying_0.png")));
    private int titleY = height - 600;
    private int titleX = centerWidth(titleSprite);
    private int startButtonX = centerWidth(startButtonSprite);
    private int startButtonY = centerHeight(startButtonSprite) - 200;

    private TextureAtlas birdFlying = new TextureAtlas("atlas/birdAtlas.atlas");
    private TextureAtlas rBirdFlying = new TextureAtlas("atlas/rBirdAtlas.atlas");

    private Animation<TextureRegion> birdAnimation = new Animation<TextureRegion>(0.09f, birdFlying.findRegion("BirdFlying"));
    private Animation<TextureRegion> rBirdAnimation = new Animation<TextureRegion>(0.09f, rBirdFlying.findRegion("rBirdFlying"));
    private float stateTime;

    private int[][] birdCoords = new int[][]{{centerWidth(birdSprite) + width/2 + 200, centerHeight(birdSprite) - height/4},
            {(int) birdSprite2.getWidth() * -1 - 600, centerHeight(birdSprite2) - height/8},
            {(int) birdSprite3.getWidth() * -1 - 550, centerHeight(birdSprite2) - height/4 + 600}};

    private Random random = new Random();
    private Vector3 touchPoint = new Vector3();

    public MainMenuScreen(final OraclesOdyssey gam) // The create class
    {
        game = gam;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, width, height);
        stateTime = 0f;
        music.setLooping(true);
        music.setVolume(0.35f);
        music.play();
    }
    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin(); // STARTS
        stateTime += delta;
        renderBackground(); // METHOD CREATED TO RENDER BACKGROUND
//        renderBirds(); // too much hassle for something that people are only going to see for 2 seconds

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
        game.batch.draw(titleSprite, titleX, titleY);
    }
    public void renderButtons()
    {
        game.batch.draw(startButtonSprite, startButtonX - startButtonSprite.getWidth(), startButtonY - startButtonSprite.getHeight(), startButtonSprite.getWidth() * 3, startButtonSprite.getHeight() * 3);
//        game.batch.draw(menuButtonSprite, menuButtonX - startButtonSprite.getWidth(), menuButtonY - menuButtonSprite.getHeight(), menuButtonSprite.getWidth() * 3, menuButtonSprite.getHeight() * 3);
//        game.batch.draw(creditButtonSprite, creditButtonX - creditButtonSprite.getWidth(), creditButtonY - creditButtonSprite.getHeight(), creditButtonSprite.getWidth() * 3, creditButtonSprite.getHeight() * 3);
    }
    public void renderBirds()
    {
        Sprite currentBirdSprite = new Sprite(birdAnimation.getKeyFrame(stateTime, true));
        Sprite currentBirdSprite2 = new Sprite(rBirdAnimation.getKeyFrame(stateTime, true));
        Sprite currentBirdSprite3 = new Sprite(rBirdAnimation.getKeyFrame(stateTime, true));
        game.batch.draw(currentBirdSprite, birdCoords[0][0], birdCoords[0][1], birdSprite.getWidth() * 8, birdSprite.getHeight() * 8);
        game.batch.draw(currentBirdSprite2, birdCoords[1][0], birdCoords[1][1], birdSprite2.getWidth() * 8, birdSprite2.getHeight() * 8);
        game.batch.draw(currentBirdSprite3, birdCoords[2][0], birdCoords[2][1], birdSprite3.getWidth() * 8, birdSprite3.getHeight() * 8);
        updateBirds();
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
//        menuButtonSprite.getTexture().dispose();
        startBackgroundSprite.getTexture().dispose();
//        creditButtonSprite.getTexture().dispose();
        birdSprite.getTexture().dispose();
        birdSprite2.getTexture().dispose();
        birdSprite3.getTexture().dispose();
    }
    public int centerWidth(Sprite sprite, int dilation) {
        return (int) ((width - sprite.getWidth())/2) * dilation;
    }
    public int centerHeight(Sprite sprite, int dilation) {
        return (int) ((height - sprite.getHeight())/2) * dilation;
    }
    public int centerWidth(Sprite sprite)
    {
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
            music.stop();
            music.dispose();
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
    public void updateBirds()
    {
        birdCoords[0][0] -= 10;
        birdCoords[1][0] += 8;
        birdCoords[2][0] += 12;
    }
}
