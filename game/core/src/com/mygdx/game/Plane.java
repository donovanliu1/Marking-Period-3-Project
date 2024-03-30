package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Plane
{
    private int ammo;
    private int maxAmmo;
    private int hp;
    private int maxHP;
    private Sprite planeSprite = new Sprite(new Texture(Gdx.files.internal("kenney_pixelshmup/Ships/ship_0000.png")));

    public Plane(int maxAmmo, int maxHP)
    {
        this.maxAmmo = maxAmmo;
        this.maxHP = maxHP;
    }

    public void update(SpriteBatch batch)
    {
        planeSprite.draw(batch);
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
