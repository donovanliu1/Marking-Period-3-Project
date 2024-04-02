package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class PlayerPlane extends Plane
{
    private final TextureAtlas enemyHitExplosion;
    public Animation<TextureRegion> enemyHitAnimation;
    private int ammo;
    private int maxAmmo;
    private float stateTime;

    public PlayerPlane(int maxAmmo, int maxHP, int minDamage, int maxDamage)
    {
        super(maxHP, minDamage, maxDamage);
        enemyHitExplosion = new TextureAtlas("atlas/enemyContactExplosion.atlas");
        enemyHitAnimation = new Animation<TextureRegion>(0.09f, enemyHitExplosion.findRegions("explosion"));
        ammo = maxAmmo;
        this.maxAmmo = maxAmmo;
        setPlaneSprite("ship_0000"); // this will be removed once we make subclasses of this class - each subclass has its own sprite
//        super.setBulletSprite("bullet1"); // commented out until i make bulletSprites texture atlas
        stateTime = 0f;
    }

    public boolean playerShoot() // we are using this
    {
        if (ammo > 0)
        {
            ammo--;
            System.out.println("shoot");
            return true;
        }
        System.out.println("out of ammo!"); // all of this is temp code to provide an outline
        return false;
    }

    public void render(SpriteBatch batch)
    {
        getPlaneSprite().draw(batch);
        getPlaneSprite().setScale(4.0F);
        getPlaneSprite().setX((float) Gdx.input.getX());
        getPlaneSprite().setY((float) (GameScreen.height - Gdx.input.getY()));
        stateTime += Gdx.graphics.getDeltaTime();
        enemyHit(10, 10, batch);

    }

    public void enemyHit(int x, int y, SpriteBatch batch)
    {
//        TextureRegion currentFrame = enemyHitAnimation.getKeyFrame(stateTime, false);
        Sprite currentSprite = new Sprite(enemyHitAnimation.getKeyFrame(stateTime, false));
        if (!enemyHitAnimation.isAnimationFinished(stateTime))
        {
            currentSprite.draw(batch);
            currentSprite.setPosition(x, y);
            currentSprite.setScale(4.0f);
        }
    }

    @Override
    public void setPlaneSprite(String name)
    {
        super.setPlaneSprite(name);
    }

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

    public void setMaxAmmo(int max)
    {
        maxAmmo = max;
    }

    public int getAmmo()
    {
        return ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    @Override
    public int getHp()
    {
        return super.getHp();
    }

    @Override
    public int getMaxHP()
    {
        return super.getMaxHP();
    }
}
