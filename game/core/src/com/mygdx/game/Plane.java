package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Plane
{
    private int ammo;
    private int maxAmmo;
    private int hp;
    private int maxHP;
    private int minDamage;
    private int maxDamage;
    private TextureAtlas enemyHitExplosion;
    private TextureAtlas planeSprites = new TextureAtlas("atlas/planeSprites.atlas");
    private Sprite planeSprite = new Sprite(planeSprites.findRegion("ship_0000"));

    public Plane(int maxHP, int minDamage, int maxDamage)
    {
        ammo = Integer.MAX_VALUE;
        maxAmmo = Integer.MAX_VALUE;
        hp = maxHP;
        this.maxHP = maxHP;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public Plane(int maxAmmo, int maxHP, int minDamage, int maxDamage)
    {
        enemyHitExplosion = new TextureAtlas("atlas/enemyContactExplosion.atlas");
        this.maxAmmo = maxAmmo;
        hp = maxHP;
        this.maxHP = maxHP;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public void update(SpriteBatch batch)
    {
        planeSprite.draw(batch);
        planeSprite.setCenterX(Gdx.graphics.getWidth());
        planeSprite.setCenterY(Gdx.graphics.getHeight());
    }

    public void setMaxAmmo(int max)
    {
        maxAmmo = max;
    }

    public void setMaxHP(int maxHP)
    {
        this.maxHP = maxHP;
    }

    public int getAmmo()
    {
        return ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public int getHp()
    {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
