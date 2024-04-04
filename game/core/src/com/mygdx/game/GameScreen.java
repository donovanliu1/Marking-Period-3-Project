package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final OraclesOdyssey game;
    private OrthographicCamera camera;
    public static int width = Gdx.graphics.getWidth(); // const since it doesnt change
    public static int height = Gdx.graphics.getHeight(); // const since it doesnt change
    private Random r = new Random();

    private Music music = Gdx.audio.newMusic(Gdx.files.internal("Music/gameMusic.mp3"));
    private Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("Music/shootSound.mp3"));
    private Sound reloadSound = Gdx.audio.newSound(Gdx.files.internal("Music/reloadSound.mp3"));
//    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private TiledMap gamea;
    private Sprite gameBackground = new Sprite(new Texture(Gdx.files.internal("Desert_Map.png"))); // Change to game background
    private PlayerPlane plane = new PlayerNormalPlane();

    public static final double SHOOT_WAIT_TIME = 0.5; // If I'm not lazy enough ill change all the finals so they are capital
    public static final double RELOAD_WAIT_TIME = 3.0;

    private boolean reloading = false;
    private double shootTimer;
    private double reloadTimer;
    private float backgroundOffset = 0;
    private float gameBackgroundHeight = gameBackground.getHeight() * (1920/gameBackground.getWidth());
    private float gameBackgroundWidth = gameBackground.getWidth() * (1920/gameBackground.getWidth());
    private ArrayList<Projectile> playerProjectiles = new ArrayList<>();
    private ArrayList<Projectile> enemyProjectiles = new ArrayList<>();
    private double stateTime = 0;
    public final static double TIME_BETWEEN_SPAWNS = 10.0;
    BitmapFont scoreFont;
    public static int wave = 1;

    // this is used for the levels - levels are premade
    // adds enemy planes to an arraylist of enemy planes that draw out the enemy planes onto the screen
    // removes enemy planes once they're dead
    private EnemyPlane[][] enemyPlanes1 = {
            {new EnemyNormalPlane(), new EnemyNormalPlane(), new EnemyNormalPlane(), new EnemyNormalPlane()},
            {new EnemyNormalPlane(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyGlassCannon()},
            {new EnemyGlassCannon(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyGlassCannon(), new EnemyNormalPlane()},
            {new EnemyNormalPlane(), new EnemyNormalPlane(), new EnemyNormalPlane(), new EnemyTankPlane()},
            {new EnemyTankPlane(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyNormalPlane()},
            {new EnemyTankPlane(), new EnemyTankPlane(), new EnemyTankPlane(), new EnemyTankPlane()},
            {new EnemyGlassCannon(), new EnemyGlassCannon(), new EnemyGlassCannon()}};

    private ArrayList<EnemyPlane> currentEnemies = new ArrayList<>(); // stores current enemies on screen

    public GameScreen(final OraclesOdyssey gam) // The create class
    {
        this.game = gam;
        shootTimer = 0;
        reloadTimer = 0;
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false, width, height);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None); // We dont want the cursor to show in the game
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        gameBackground = new Sprite(new Texture(Gdx.files.internal("Desert_Map.png")));

        music.setLooping(true);
        music.setVolume(0.35f);
        music.play();


//        gameBackground = new TmxMapLoader().load("maps/Desert_Map.tmx");
//        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameBackground);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        Sprite playerProjectileSprite = new Sprite(plane.getBulletSprites().findRegion("playerBulletNormal"));
        //Shooting
        shootTimer += delta;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && shootTimer > SHOOT_WAIT_TIME && plane.getAmmo() > 0) {
            System.out.println("shooting"); // this is test
            if (plane.playerShoot()) {
                shootTimer = 0;
                shootSound.play(0.07f);
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 7, (int) plane.getPlaneSprite().getY() - 12, false));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 48, (int) plane.getPlaneSprite().getY() + 4, false));
                playerProjectiles.add(new Projectile(playerProjectileSprite, (int) plane.getPlaneSprite().getX() - 89, (int) plane.getPlaneSprite().getY() - 12, false));
            }
        }

        if (plane.getAmmo() <= 0)
        {
            if (reloadTimer == 0) reloadSound.play(0.4f);
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

        projectilesToRemove.clear();
        float planeX = plane.getPlaneSprite().getX();
        float planeY = plane.getPlaneSprite().getY();
        if (stateTime > 0 && stateTime < 30) {
            for (EnemyPlane enemyPlane: enemyPlanes1[0]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 5 && stateTime < 35) {
            for (EnemyPlane enemyPlane: enemyPlanes1[1]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 10 && stateTime < 40) {
            for (EnemyPlane enemyPlane: enemyPlanes1[2]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 15 && stateTime < 45) {
            for (EnemyPlane enemyPlane: enemyPlanes1[3]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 20 && stateTime < 50) {
            for (EnemyPlane enemyPlane: enemyPlanes1[4]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 25 && stateTime < 55) {
            for (EnemyPlane enemyPlane: enemyPlanes1[5]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 30 && stateTime < 60) {
            for (EnemyPlane enemyPlane: enemyPlanes1[6]) { // move this higher. used to render enemy projectiles
                enemyPlane.update(delta);
                if (enemyPlane.enemyShoot(delta) && !enemyPlane.getHit()) {
                    float distanceX = enemyPlane.getPlaneSprite().getX() - planeX;
                    float distanceY = enemyPlane.getPlaneSprite().getY() - planeY;
                    float angle = 3.14f + MathUtils.atan2(distanceY, distanceX);
                    enemyProjectiles.add(new Projectile(enemyPlane.getBulletSprite(), enemyPlane.getX() - 40, enemyPlane.getY() - 10, true, (int) angle));
                }
            }
        }
        if (stateTime > 50) {
            for (EnemyPlane[] enemyPlanes: enemyPlanes1) {
                for (EnemyPlane enemyPlane : enemyPlanes) {
                    enemyPlane.setY(r.nextInt(1200, 1600));
                    enemyPlane.setX(r.nextInt(0, 1920));
                }
            }
        }
        if (stateTime > 51) {
            wave++;
            stateTime = 0;
        }
        for (Projectile projectile: enemyProjectiles) {
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

        GlyphLayout waveLayout = new GlyphLayout(scoreFont,"Wave: " + wave);
        GlyphLayout ammoLayout = new GlyphLayout(scoreFont, "Ammo: " + plane.getAmmo() + "/" + plane.getMaxAmmo());
        GlyphLayout reloadingLayout = new GlyphLayout(scoreFont, "Reloading...");
        scoreFont.draw(game.batch, waveLayout, width/ 2 - waveLayout.width / 2, height - waveLayout.height - 10);
        if(plane.getAmmo() > 0) {
            scoreFont.draw(game.batch, ammoLayout, 100, 100);
        }
        else {
            scoreFont.draw(game.batch, reloadingLayout, 100, 100);
        }


        for (Projectile projectile: playerProjectiles) {
            for (EnemyPlane[] enemyPlanes: enemyPlanes1) {
                for (EnemyPlane enemyPlane: enemyPlanes) {
//                    Sprite projectileSprite = projectile.getBulletSprite();
                    Sprite enemySprite = enemyPlane.getPlaneSprite();
                    if (projectile.hitPlane(enemySprite.getBoundingRectangle())) {
                        enemyPlane.setHit(true);

                        plane.enemyHit(enemyPlane.getX(), enemyPlane.getY(), game.batch);
                        enemyPlane.setY(-1000);
//                        enemyPlane.getPlaneSprite().getTexture().dispose();



                        projectilesToRemove.add(projectile);
                    }
                }
            }
        }
        for (Projectile projectile: enemyProjectiles) {
            Sprite projectileSprite = projectile.getBulletSprite();
            Sprite planeSprite = plane.getPlaneSprite();
            if (new Rectangle(projectileSprite.getX(), projectileSprite.getY(), projectileSprite.getWidth(), projectileSprite.getHeight()).overlaps(new Rectangle(planeSprite.getX(), planeSprite.getY(), planeSprite.getWidth(), planeSprite.getHeight()))) {
                music.stop();
                music.dispose();
                game.setScreen(new LoseScreen(game));
            }
        }
        playerProjectiles.removeAll(projectilesToRemove);
        for (Projectile projectile: playerProjectiles) {
            projectile.render(game.batch);
        }
        for (Projectile projectile: enemyProjectiles) {
            projectile.render(game.batch);
        }
        if (stateTime > 0 && stateTime < 30) {
            for (EnemyPlane enemyPlane: enemyPlanes1[0]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 5 && stateTime < 35) {
            for (EnemyPlane enemyPlane: enemyPlanes1[1]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 10 && stateTime < 40) {
            for (EnemyPlane enemyPlane: enemyPlanes1[2]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 15 && stateTime < 45) {
            for (EnemyPlane enemyPlane: enemyPlanes1[3]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 20 && stateTime < 50) {
            for (EnemyPlane enemyPlane: enemyPlanes1[4]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 25 && stateTime < 55) {
            for (EnemyPlane enemyPlane: enemyPlanes1[5]) {
                enemyPlane.render(game.batch);
            }
        }
        if (stateTime > 30 && stateTime < 60) {
            for (EnemyPlane enemyPlane: enemyPlanes1[6]) {
                enemyPlane.render(game.batch);
            }
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
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
