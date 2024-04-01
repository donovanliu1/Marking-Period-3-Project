package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// PROJECTILES WILL NOT HAVE DAMAGE. DAMAGE WILL BE BASED ON SHIP TYPE
public class Projectile
{
    private Sprite bulletSprite;

    public Projectile(Sprite bulletSprite)
    {
        this.bulletSprite = bulletSprite;
    }

    public void create(int x, int y, SpriteBatch batch)
    {
        bulletSprite.draw(batch);
        bulletSprite.setPosition(x, y);
    }

    public void render(boolean isEnemy, boolean isTracking)
    {
        if (isEnemy)
    }
}
