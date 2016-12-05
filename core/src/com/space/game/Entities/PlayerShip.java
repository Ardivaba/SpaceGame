package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.Projectiles.Projectile;
import com.space.game.SpaceGame;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class PlayerShip extends BaseShip 
{
    protected boolean shootingDirection = true;
    // Was player damaged this frame?
    public int indicateDamaged = 0;
    
    private Texture playerDamagedTexture;
    private Texture originalTexture;
    
    Sound hitSound;
    
    public void start()
    {
        this.health = 3.0f;
        this.shipSpeed = 900f;
        
        playerDamagedTexture = new Texture(Gdx.files.internal("playerShip1_orange.png"));
        originalTexture = texture;
        
        hitSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/hit.ogg"));
    }
    
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
        Projectile bullet = EntityManager.spawnBullet(new Vector2(bulletPosX, bulletPosY), true);
    }
    
    @Override
    public void update(float deltaTime)
    {
        handlePlayerInput(deltaTime);
    }
    
    private void handlePlayerInput(float deltaTime)
    {
        // Restore original texture after being damaged
        if(indicateDamaged <= 0)
        {
            this.texture = originalTexture;
        }
        else
        {
            this.indicateDamaged--;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            position.y += shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            position.y -= shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            position.x -= shipSpeed * deltaTime;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
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
        if(position.x < -800)
            position.x = -800;
        
        if(position.x > 800)
            position.x = 800;
        
        if(position.y > Gdx.graphics.getHeight() + this.body.getHeight() - 10)
            position.y = Gdx.graphics.getHeight() + this.body.getHeight() - 10;

        if(position.y < -Gdx.graphics.getHeight() - this.body.getHeight())
            position.y = -Gdx.graphics.getHeight() - this.body.getHeight();
    }
    
    public void damage(float value)
    {
        super.damage(value);
        // Indicate damaged for 10 frames
        this.indicateDamaged = 5;
        this.texture = playerDamagedTexture;
        hitSound.play();
    }
}
