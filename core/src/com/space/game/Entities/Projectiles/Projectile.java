package com.space.game.Entities.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.space.game.Entity;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class Projectile extends Entity
{
    public float velocity = 700f;
    public float damage = 1f;
    public boolean direction;
    
    protected Sound sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/laser.wav"));
    
    public Projectile()
    {
        super();
        
        sound.play();
    }
    
    public void setDirection(boolean upwardVelocity)
    {
        direction = upwardVelocity;
        
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
    
    @Override
    public void collision(Entity otherEntity)
    {
        if(otherEntity.getClass() == this.getClass())
            return;
        
        this.destroy();
    }
}
