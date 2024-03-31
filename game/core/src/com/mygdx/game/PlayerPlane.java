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
        enemyHitAnimation = new Animation<TextureRegion>(0.5f, enemyHitExplosion.findRegions("explosion"));
        ammo = maxAmmo;
        this.maxAmmo = maxAmmo;
        setPlaneSprite("ship_0000"); // this will be removed once we make subclasses of this class - each subclass has its own sprite
//        super.setBulletSprite("bullet1"); // commented out until i make bulletSprites texture atlas
        stateTime = 0f;
    }

    @Override
    public void shoot() // we are using this
    {
        if (ammo > 0)
        {
            ammo--;
            System.out.println("shoot");
        }
        else System.out.println("out of ammo!"); // all of this is temp code to provide an outline
    }

    @Override
    public void render(SpriteBatch batch)
    {
        super.render(batch);
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = enemyHitAnimation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, 0, 0);  // testing
    }

    @Override
    public void setPlaneSprite(String name)
    {
        super.setPlaneSprite(name);
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
}
