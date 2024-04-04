package com.mygdx.game;

public class EnemyTankPlane extends EnemyPlane
{
    public EnemyTankPlane()
    {
        super(150, 12, 15);
        setPlaneSprite("ship_0014");
        setBulletSprite("enemyBulletSmall");
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
