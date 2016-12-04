package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.space.game.Entity;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class BaseShip extends Entity {
    @Override
    public void setTexture()
    {
        this.texture = new Texture(Gdx.files.internal("playerShip1_blue.png"));
    }
}
