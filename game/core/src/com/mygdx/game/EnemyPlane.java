package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class EnemyPlane extends Plane
{
    private TextureAtlas playerHitExplosion;
    public Animation<TextureRegion> playerHitAnimation;
    private float stateTime;

    public EnemyPlane(int maxHP, int minDamage, int maxDamage)
    {
        super(maxHP, minDamage, maxDamage);
        playerHitExplosion = new TextureAtlas("atlas/playerHitExplosion.atlas");
        playerHitAnimation = new Animation<TextureRegion>(0.09f, playerHitExplosion.findRegions("explosion"));
        stateTime = 0f;
    }

    public void render(SpriteBatch batch)
    {
        getPlaneSprite().draw(batch);
        getPlaneSprite().setScale(4.0F);
        getPlaneSprite().setX((float) Gdx.input.getX());
        getPlaneSprite().setY((float) (GameScreen.height - Gdx.input.getY()));
        stateTime += Gdx.graphics.getDeltaTime();
        playerHit(10, 10, batch);
    }

    public void enemyShoot() // we are using this
    {

    }

    public void playerHit(int x, int y, SpriteBatch batch)
    {
//        TextureRegion currentFrame = enemyHitAnimation.getKeyFrame(stateTime, false);
        Sprite currentSprite = new Sprite(playerHitAnimation.getKeyFrame(stateTime, false));
        if (!playerHitAnimation.isAnimationFinished(stateTime))
        {
            currentSprite.draw(batch);
            currentSprite.setPosition(x, y);
            currentSprite.setScale(4.0f);
        }
    }
}
