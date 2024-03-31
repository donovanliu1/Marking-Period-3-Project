package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class EnemyPlane extends Plane
{
    private TextureAtlas playerHitExplosion;

    public EnemyPlane(int maxHP, int minDamage, int maxDamage)
    {
        super(maxHP, minDamage, maxDamage);
//        playerHitExplosion = new TextureAtlas("atlas/playerContactExplosion.atlas"); // commented out until i make playerContactExplosion texture atlas
        setPlaneSprite("ship_0012"); // this will be removed once we make subclasses of this class - each subclass has its own sprite
//        setBulletSprite("bullet3"); // commented out until i make bulletSprites texture atlas
    }
}
