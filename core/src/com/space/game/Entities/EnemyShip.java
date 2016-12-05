package com.space.game.Entities;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class EnemyShip extends BaseShip {
    protected float shipSpeed = 300f;
    
    @Override
    public void update(float deltaTime)
    {
        position.y -= this.shipSpeed * deltaTime;
    }
}