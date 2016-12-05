package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entity;
import com.space.game.SpaceGame;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class PlayerShip extends BaseShip 
{
    protected float shipSpeed = 600f;
    protected Vector2 gunOffset = new Vector2(50, 75 / 2);
    protected boolean shootingDirection = true;
    
    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("playerShip1_blue.png"));
    }

    @Override
    public void shoot()
    {
        float bulletPosX = this.position.x + this.texture.getWidth() / 2;
        float bulletPosY = this.position.y + this.texture.getHeight() / 2 + 50;
        Bullet bullet = EntityManager.spawnBullet(new Vector2(bulletPosX, bulletPosY), true);
    }
    
    @Override
    public void update(float deltaTime)
    {
        handlePlayerInput(deltaTime);
        System.out.println(position.x + " " + position.y);
    }
    
    private void handlePlayerInput(float deltaTime)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            position.y += shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            position.y -= shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            position.x -= shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            position.x += shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            this.shoot();
        }
        
        validatePosition();
    }
    
    private void validatePosition()
    {
        if(position.x > Gdx.graphics.getWidth() + this.body.getWidth())
            position.x = Gdx.graphics.getWidth() + this.body.getWidth();
    }
}
