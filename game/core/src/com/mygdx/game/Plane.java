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
    private Sprite planeSprite;
    private Sprite bulletSprite;

    public Plane(int maxHP, int minDamage, int maxDamage) // constructor for enemy plane
    {
        hp = maxHP;
        this.maxHP = maxHP;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    // RENDER BULLETS BASED ON SPRITE AND NOT MOUSE POS
    public void render(SpriteBatch batch)
    {
        planeSprite.draw(batch);
        planeSprite.setX((float) Gdx.input.getX());
        planeSprite.setY((float) (GameScreen.height - Gdx.input.getY())); // plane is at mouse position
        planeSprite.setScale(4.0F);
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

    public TextureAtlas getPlaneSprites()
    {
        return planeSprites;
    }

    public void setPlaneSprite(String name)
    {
        planeSprite = new Sprite(planeSprites.findRegion(name));
    }

    public void setBulletSprite(String name)
    {
//        bulletSprite = new Sprite(bulletSprites.findRegion(name)); // commented out for now until i make the bulletSprites texture atlas
    }
}
