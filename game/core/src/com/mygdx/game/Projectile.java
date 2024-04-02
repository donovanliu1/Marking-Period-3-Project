package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// PROJECTILES WILL NOT HAVE DAMAGE. DAMAGE WILL BE BASED ON SHIP TYPE
public class Projectile
{
    private Sprite bulletSprite;
    private int x;
    private int y;
    private int speed = 600;
    private boolean remove = false;
    public Projectile(Sprite bulletSprite, int x, int y, boolean isEnemyBullet)
    {
        this.bulletSprite = bulletSprite;
        this.bulletSprite.setScale(bulletSprite.getWidth()/2, bulletSprite.getHeight()/2);
        this.x = x;
        this.y = y;
        if (isEnemyBullet) speed *= -1;
    }
    // deltaTime used to limit amount of bullets on screen
    // might have to make this a separate class for just player projectile
    public void update(double deltaTime) { // this method will be used to update the bullets in an arraylist
        y += (int) (speed * deltaTime);
        if (y > Gdx.graphics.getHeight()) { // ?
            remove = true;
        }
    }
    public void render(SpriteBatch batch)
    {
        batch.draw(bulletSprite, x, y);
    }

    public boolean isRemove() {
        return remove;
    }
}
