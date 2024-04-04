package com.mygdx.game;

public class PlayerNormalPlane extends PlayerPlane // please find a better name for this
{

    public PlayerNormalPlane() // temp constructor
    {
        super(20, 100, 33, 36);
        setPlaneSprite("ship_0000");
        setBulletSprite("playerBulletNormal");
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
