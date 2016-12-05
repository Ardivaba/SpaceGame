package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class PlayerShip extends BaseShip 
{
    protected float shipSpeed = 100f;
    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("playerShip1_blue.png"));
    }
    
    @Override
    public void update(float deltaTime)
    {
        handlePlayerInput(deltaTime);
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
    }
}
