package com.mygdx.game;

public class PlayerTankPlane extends PlayerPlane
{

    public PlayerTankPlane()
    {
        super(70, 175, 16, 22);
        setPlaneSprite("ship_0002");
        setBulletSprite("playerBulletSmall");
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
