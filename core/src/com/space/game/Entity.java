package com.space.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.space.game.Entities.EntityManager;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class Entity extends Actor{
    protected Texture texture;
    public Vector2 position;
    public Rectangle body;
    
    public float health = 1.0f;
    
    public Entity()
    {
        // Bad code...but helps me save time
        EntityManager.entities.add(this);
    }
    
    public void init(Vector2 position) 
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        this.position = position;
        setTexture();
        body = new Rectangle();
        body.set(position.x, position.y, texture.getWidth(), texture.getHeight());
    }
    
    public void preUpdate()
    {
        body.setPosition(position.x, position.y);
        
        if(position.y < -Gdx.graphics.getHeight())
            this.destroy();
    }

    public void update(float deltaTime) 
    {
    }
    
    public void collision(Entity otherEntity)
    {
        
    }
    
    public void damage(float value)
    {
        this.health -= value;
        if(this.health <= 0f)
        {
            this.destroy();
        }
    }
    
    public void destroy()
    {
        EntityManager.removeEntity(this);
    }

    public void render(Batch batch) 
    {
        batch.draw(this.texture, position.x, position.y);
    }
    
    @Override
    public void draw (Batch batch, float parentAlpha) 
    {
    }

    public void setTexture(){
        this.texture = null;
    }
}
