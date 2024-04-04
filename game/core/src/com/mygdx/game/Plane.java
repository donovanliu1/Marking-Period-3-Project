package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Plane
{
    private int hp;
    private int maxHP;
    private int minDamage; // if we run out of time we could make this just const
    private int maxDamage;
    private TextureAtlas planeSprites = new TextureAtlas("atlas/planeSprites.atlas");
    private TextureAtlas bulletSprites = new TextureAtlas("atlas/bulletSprites.atlas");
    private Sprite planeSprite;
    private Sprite bulletSprite;

    public Plane(int maxHP, int minDamage, int maxDamage) // constructor for enemy plane
    {
        hp = maxHP;
        this.maxHP = maxHP;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public void shoot()
    {
        System.out.println("shoot");
    }

    public void setMaxHP(int maxHP)
    {
        this.maxHP = maxHP;
    }

    public int getHp()
    {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Sprite getPlaneSprite() {
        return planeSprite;
    }

    public TextureAtlas getPlaneSprites()
    {
        return planeSprites;
    }

    public TextureAtlas getBulletSprites()
    {
        return bulletSprites;
    }

    public void setPlaneSprite(String name)
    {
        planeSprite = new Sprite(planeSprites.findRegion(name));
    }

    public void setBulletSprite(String name)
    {
        bulletSprite = new Sprite(bulletSprites.findRegion(name));
    }

    public Sprite getBulletSprite()
    {
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
