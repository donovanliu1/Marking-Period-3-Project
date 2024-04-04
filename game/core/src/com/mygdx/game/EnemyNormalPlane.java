package com.mygdx.game;

public class EnemyNormalPlane extends EnemyPlane
{
    public EnemyNormalPlane()
    {
        super(90, 19, 23);
        setPlaneSprite("ship_0012");
        setBulletSprite("enemyBulletNormal");
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
