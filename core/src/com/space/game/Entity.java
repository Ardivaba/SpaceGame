package com.space.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.space.game.Entities.EntityManager;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class Entity {
    private float width;
    private float height;

    protected Texture texture;
    public Vector2 position;
    
    public void init(Vector2 position) 
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        this.position = position;
        setTexture();
    }

    public void update(float deltaTime) 
    {
    }
    
    public void collides(Entity otherEntity)
    {
        
    }

    public void render(Batch batch) 
    {
        batch.draw(this.texture, position.x, position.y);
    }

    public void setTexture(){
        this.texture = null;
    }
}
