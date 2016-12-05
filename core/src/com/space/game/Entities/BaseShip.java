package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entity;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class BaseShip extends Entity 
{
    protected float shipSpeed = 100f;
    protected Vector2 gunOffset = new Vector2(0, 0);
    protected boolean shootingDirection = false;
    
    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("Enemies/enemyBlack1.png"));
    }
    
    public void shoot()
    {
        Bullet bullet = EntityManager.spawnBullet(position.add(this.gunOffset), this.shootingDirection);
    }
    
    public void collides(Entity otherEntity)
    {
        try
        {
            Bullet bullet = (Bullet) otherEntity;
            if(bullet != null)
            {
                this.damage(bullet.damage);
            }
        }
        catch (Exception e)
        {
            // Not bullet...w
        }
    }
}
