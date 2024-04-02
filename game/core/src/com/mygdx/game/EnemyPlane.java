package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.Random;

public class EnemyPlane extends Plane
{
    private TextureAtlas playerHitExplosion;
    public Animation<TextureRegion> playerHitAnimation;
    private float stateTime;
    private int x;
    private int y;
    private final Random r = new Random();
    private double shootTimer = 0;
    private boolean remove = false;
    private boolean isHit = false;
    private final float SHOOT_WAIT_TIME = 0.8f;

    public EnemyPlane(int maxHP, int minDamage, int maxDamage)
    {
        super(maxHP, minDamage, maxDamage);
        playerHitExplosion = new TextureAtlas("atlas/playerHitExplosion.atlas");
        playerHitAnimation = new Animation<TextureRegion>(0.09f, playerHitExplosion.findRegions("explosion"));
        stateTime = 0f;
        x = r.nextInt(0, 1920);
        y = r.nextInt(1200, 1600);
    }

    public void render(SpriteBatch batch)
    {
        getPlaneSprite().draw(batch);
        getPlaneSprite().setScale(4.0F);
        getPlaneSprite().setX((float) x);
        getPlaneSprite().setY((float) y);
        stateTime += Gdx.graphics.getDeltaTime();
        playerHit(10, 10, batch);
    }
    public void update(float deltaTime) {
        y -= (int) (150 * deltaTime);
        if (y < -100) {
            remove = true;
        }
    }

    public boolean enemyShoot(float deltaTime) // we are using this
    {
        boolean canShoot = 1080 > y && y > 0;
        if (canShoot) {
            shootTimer += deltaTime;
        }
        if (shootTimer > SHOOT_WAIT_TIME) {
            shootTimer = 0;
            return (canShoot);
        }
        return false;
    }

    public void playerHit(int x, int y, SpriteBatch batch)
    {
//        TextureRegion currentFrame = enemyHitAnimation.getKeyFrame(stateTime, false);
        Sprite currentSprite = new Sprite(playerHitAnimation.getKeyFrame(stateTime, false));
        if (!playerHitAnimation.isAnimationFinished(stateTime))
        {
            currentSprite.draw(batch);
            currentSprite.setPosition(x, y);
            currentSprite.setScale(4.0f);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
    public boolean getHit() {
        return isHit;
    }
}
