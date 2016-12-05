package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.space.game.Entity;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class Bullet extends Entity
{
    public float velocity = 500f;
    public float damage = 0.01f;
    
    public void setDirection(boolean upwardVelocity)
    {
        if(!upwardVelocity)
        {
            velocity *= -1;
        }
    }
    
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("Lasers/laserGreen13.png"));
    }
    
    @Override
    public void update(float deltaTime)
    {
        position.y += velocity * deltaTime;
    }
}
