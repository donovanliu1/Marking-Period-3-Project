package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PlayerPlane extends Plane
{
    private TextureAtlas enemyHitExplosion;
    private int ammo;
    private int maxAmmo;

    public PlayerPlane(int maxAmmo, int maxHP, int minDamage, int maxDamage)
    {
        super(maxHP, minDamage, maxDamage);
        enemyHitExplosion = new TextureAtlas("atlas/enemyContactExplosion.atlas");
        ammo = maxAmmo;
        this.maxAmmo = maxAmmo;
        setPlaneSprite("ship_0000"); // this will be removed once we make subclasses of this class - each subclass has its own sprite
//        super.setBulletSprite("bullet1"); // commented out until i make bulletSprites texture atlas
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
