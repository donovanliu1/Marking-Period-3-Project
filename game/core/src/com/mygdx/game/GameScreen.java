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
    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private TiledMap gamea;
    private Sprite gameBackground = new Sprite(new Texture(Gdx.files.internal("Desert_Map.png"))); // Change to game background
    private PlayerPlane plane = new PlayerPlane(100, 100, 100, 100);

    public static final double SHOOT_WAIT_TIME = 0.4; // If I'm not lazy enough ill change all the finals so they are capital
    private double shootTimer;
    ArrayList<Projectile> playerProjectiles = new ArrayList<>();

    public GameScreen(final OraclesOdyssey gam) // The create class
    {
        this.game = gam;
        shootTimer = 0;
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
        playerProjectileSprite.setSize(playerProjectileSprite.getWidth()/5, playerProjectileSprite.getHeight()/5);

        //Shooting
        shootTimer += delta;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && shootTimer > SHOOT_WAIT_TIME){
            System.out.println("shooting"); // this is test
            if (plane.shootPlayer()) {
                shootTimer = 0;
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 10, (int) plane.getPlaneSprite().getY() - 12));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 56, (int) plane.getPlaneSprite().getY() + 4));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 100, (int) plane.getPlaneSprite().getY() - 12));
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
        game.batch.draw(gameBackground, 0, 0, width, height);
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
