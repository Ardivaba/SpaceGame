package com.space.game.Entities.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.BaseShip;
import com.space.game.Entities.Projectiles.Projectile;
import com.space.game.Entities.EntityManager;
import com.space.game.Entities.PlayerShip;
import com.space.game.Entity;

import java.util.Random;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class EnemyShip extends BaseShip {
    protected float shipSpeed = 300f;
    protected Random random = new Random();
    private int timesShot = 1;
    
    @Override
    public void update(float deltaTime)
    {
        position.y -= this.shipSpeed * deltaTime;
        
        if(random.nextInt(100 * this.timesShot) == 5)
        {
            this.shoot();
        }
    }

    @Override
    public void shoot()
    {
        this.timesShot++;
        float bulletPosX = this.position.x + this.texture.getWidth() / 2;
        float bulletPosY = this.position.y + this.texture.getHeight() / 2 - 100;
        Projectile bullet = EntityManager.spawnProjectile(new Vector2(bulletPosX, bulletPosY), false);
    }

    public void collision(Entity otherEntity)
    {
        super.collision(otherEntity);
        if(otherEntity.getClass() == PlayerShip.class)
        {
            otherEntity.damage(1f);
            this.destroy();
        }
    }
}