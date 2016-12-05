package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.Projectiles.Projectile;
import com.space.game.Entity;
import com.space.game.SpaceGame;

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
        Projectile bullet = EntityManager.spawnProjectile(position.add(this.gunOffset), this.shootingDirection);
    }
    
    public void collision(Entity otherEntity)
    {
        try
        {
            Projectile bullet = (Projectile) otherEntity;
            if(bullet != null)
            {
                // Make it so that payers can't get damaged with their own bullets
                // Note: Need to refactor this
                if(!(bullet.direction && SpaceGame.playerShip == this))
                    this.damage(1f);
            }
        }
        catch (Exception e)
        {
            // Not bullet...
            // Note: Should just use .class to instead of this try catch but out of time
        }
    }
}
