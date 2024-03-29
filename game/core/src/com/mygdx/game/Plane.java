package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Plane
{
    private int ammo;
    private int maxAmmo;
    private int hp;
    private int maxHP;
    private Texture planeTexture = new Texture();

    public Plane(int maxAmmo, int maxHP)
    {
        this.maxAmmo = maxAmmo;
        this.maxHP = maxHP;
    }

    public void render()
    {
        
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
