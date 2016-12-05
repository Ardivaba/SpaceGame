package com.space.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.space.game.SpaceGame;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class FollowingEnemy extends SimpleMovingEnemy
{
    public void update(float deltaTime)
    {
        position.y -= this.shipSpeed * deltaTime;
        
        position.x = MathUtils.lerp(position.x, SpaceGame.playerShip.position.x, deltaTime * 1.25f);
    }

    public void start()
    {
        this.shipSpeed *= 2;
    }

    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("Enemies/enemyBlue5.png"));
    }
}
