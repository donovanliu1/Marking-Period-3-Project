package com.mygdx.game;

public class EnemyGlassCannon extends EnemyPlane
{
    public EnemyGlassCannon()
    {
        super(40, 38, 42);
        setPlaneSprite("ship_0019");
        setBulletSprite("enemyBulletStrong");
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
