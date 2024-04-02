package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    public static int width = Gdx.graphics.getWidth(); // const since it doesnt change
    public static int height = Gdx.graphics.getHeight(); // const since it doesnt change
//    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private TiledMap gamea;
    private Sprite gameBackground = new Sprite(new Texture(Gdx.files.internal("Desert_Map.png"))); // Change to game background
    private PlayerPlane plane = new PlayerNormalPlane();

    public static final double SHOOT_WAIT_TIME = 0.25; // If I'm not lazy enough ill change all the finals so they are capital
    public static final double RELOAD_WAIT_TIME = 3.0;

    private double shootTimer;
    private double reloadTimer;
    private float backgroundOffset = 0;
    private float gameBackgroundHeight = gameBackground.getHeight() * (1920/gameBackground.getWidth());
    private float gameBackgroundWidth = gameBackground.getWidth() * (1920/gameBackground.getWidth());
    private ArrayList<Projectile> playerProjectiles = new ArrayList<>();
    private ArrayList<Projectile> enemyProjectiles = new ArrayList<>();

    public final static double TIME_BETWEEN_SPAWNS = 10.0;

    // this is used for the levels - levels are premade
    // adds enemy planes to an arraylist of enemy planes that draw out the enemy planes onto the screen
    // removes enemy planes once they're dead
    private EnemyPlane[][] enemyPlanes1 = {
            {new EnemyNormalPlane(), new EnemyNormalPlane(), new EnemyNormalPlane()},
            {new EnemyNormalPlane(), new EnemyTankPlane(), new EnemyNormalPlane(), new EnemyTankPlane()},
            {new EnemyGlassCannon(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyGlassCannon()},
            {new EnemyGlassCannon(), new EnemyGlassCannon(), new EnemyGlassCannon()},
            {new EnemyNormalPlane(), new EnemyTankPlane(), new EnemyTankPlane()}};

    private ArrayList<EnemyPlane> currentEnemies = new ArrayList<>(); // stores current enemies on screen

    public GameScreen(final OraclesOdyssey gam) // The create class
    {
        this.game = gam;
        shootTimer = 0;
        reloadTimer = 0;
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false, width, height);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None); // We dont want the cursor to show in the game

        gameBackground = new Sprite(new Texture(Gdx.files.internal("Desert_Map.png")));
//        gameBackground = new TmxMapLoader().load("maps/Desert_Map.tmx");
//        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameBackground);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Sprite playerProjectileSprite = new Sprite(plane.getBulletSprites().findRegion("playerBulletNormal"));

        //Shooting
        shootTimer += delta;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && shootTimer > SHOOT_WAIT_TIME && plane.getAmmo() > 0) {
            System.out.println("shooting"); // this is test
            if (plane.playerShoot()) {
                shootTimer = 0;
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 7, (int) plane.getPlaneSprite().getY() - 12, false));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 48, (int) plane.getPlaneSprite().getY() + 4, false));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 89, (int) plane.getPlaneSprite().getY() - 12, false));

            }
        }

        if (plane.getAmmo() <= 0)
        {
            reloadTimer += delta;
            if (reloadTimer > RELOAD_WAIT_TIME)
            {
                plane.setAmmo(plane.getMaxAmmo());
                reloadTimer = 0;
            }
        }

        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile: playerProjectiles) {
            projectile.update(delta);
            if (projectile.isRemove()) {
                projectilesToRemove.add(projectile);
            }
        }
        playerProjectiles.removeAll(projectilesToRemove);

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.begin();
        backgroundOffset-= 2;
        if(backgroundOffset % (gameBackgroundHeight) == 0) {
            backgroundOffset = 0;
        }
        plane.enemyHit(10, 10, game.batch);
        game.batch.draw(gameBackground, 0, backgroundOffset + gameBackgroundHeight, gameBackgroundWidth, gameBackgroundHeight);
        game.batch.draw(gameBackground, 0, backgroundOffset, gameBackgroundWidth, gameBackgroundHeight);
        for (Projectile projectile: playerProjectiles) {
            projectile.render(game.batch);
        }
        plane.render(game.batch);
        game.batch.end();

//        tiledMapRenderer.render();
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
    public void dispose()
    {

    }
}
