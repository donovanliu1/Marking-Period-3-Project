package com.mygdx.game;

public class PlayerTankPlane extends PlayerPlane
{

    public PlayerTankPlane(int maxAmmo, int maxHP, int minDamage, int maxDamage) // temp constructor
    {
        super(maxAmmo, maxHP, minDamage, maxDamage);
        setPlaneSprite("ship_0000");
        setBulletSprite("playerBulletSmall");
    }
}
