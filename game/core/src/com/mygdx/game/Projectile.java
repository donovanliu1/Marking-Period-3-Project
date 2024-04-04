package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// PROJECTILES WILL NOT HAVE DAMAGE. DAMAGE WILL BE BASED ON SHIP TYPE
public class Projectile
{
    private Sprite bulletSprite;
    private int x;
    private int y;
    private int speed = 1000;
    private boolean remove = false;
    private Vector2 position;
    private Vector2 velocity;
    public Projectile(Sprite bulletSprite, int x, int y, boolean isEnemyBullet)
    {
        this.bulletSprite = bulletSprite;
        this.bulletSprite.setScale(0.7f);
        this.x = x;
        this.y = y;
        if (isEnemyBullet) {
            speed *= -1;
        }
        this.bulletSprite.setPosition(x, y);
    }
    public Projectile(Sprite bulletSprite, int x, int y, boolean isEnemyBullet, int angle)
    {
        this.bulletSprite = bulletSprite;
        this.bulletSprite.setScale(0.7f);
        this.x = x;
        this.y = y;
        if (isEnemyBullet) {
            position = new Vector2(x, y);
            velocity = new Vector2(speed * MathUtils.cos(angle), speed * MathUtils.sin(angle));
            bulletSprite.setRotation(angle * MathUtils.radiansToDegrees + 90);

        }
        this.bulletSprite.setPosition(x, y);
    }
    // deltaTime used to limit amount of bullets on screen
    // might have to make this a separate class for just player projectile
    public void update(double deltaTime) { // this method will be used to update the bullets in an arraylist
        if (y > Gdx.graphics.getHeight() || y < -50) { // ?
            remove = true;
        }
        if (velocity == null) {
            y += (int) (speed * deltaTime);
        }
        else {
            position.add((float) (velocity.x * deltaTime), (float) (velocity.y * deltaTime));
        }
    }

    public boolean hitPlane(Rectangle rect)
    {
        if (bulletSprite.getBoundingRectangle().overlaps(rect)) return true;
        return false;
    }

    public void render(SpriteBatch batch)
    {
        bulletSprite.draw(batch);
        if (velocity == null) {
            bulletSprite.setPosition(x, y);
        }
        else {
            bulletSprite.setPosition(position.x, position.y);
        }
        bulletSprite.draw(batch);
        // it used to be batch.draw(bulletSprite)
    }

    public boolean isRemove()
    {
        return remove;
    }

    public Sprite getBulletSprite() {
        return bulletSprite;
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
