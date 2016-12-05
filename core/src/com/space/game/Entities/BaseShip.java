package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.Projectiles.Projectile;
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
        Projectile bullet = EntityManager.spawnBullet(position.add(this.gunOffset), this.shootingDirection);
    }
    
    public void collision(Entity otherEntity)
    {
        try
        {
            Projectile bullet = (Projectile) otherEntity;
            if(bullet != null)
            {
                this.damage(1f);
            }
        }
        catch (Exception e)
        {
            // Not bullet...w
        }
    }
}
