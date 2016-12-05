package com.space.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.space.game.Entities.Enemies.EnemyShip;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class SimpleMovingEnemy extends EnemyShip
{
    protected float targetX = 0.0f;
    @Override
    public void update(float deltaTime)
    {
        position.y -= this.shipSpeed * deltaTime;
        
        if(Math.abs(position.x - targetX) < 200.0f)
        {
                pickNewMovementTarget();
        }

        position.x += this.shipSpeed * deltaTime;
        position.x = MathUtils.lerp(position.x, targetX, deltaTime * 1.25f);
    }
    
    public void start()
    {
        this.shipSpeed /= 2;
        pickNewMovementTarget();
    }
    
    public void pickNewMovementTarget()
    {
        targetX = -Gdx.graphics.getWidth() + (random.nextFloat() * Gdx.graphics.getWidth());
    }

    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("Enemies/enemyGreen4.png"));
    }
}
