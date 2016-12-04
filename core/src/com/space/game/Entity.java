package com.space.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class Entity {
    public Body body;

    private float width;
    private float height;

    protected Texture texture;

    public float getX()
    {
        return this.body.getPosition().x;
    }

    public float getY()
    {
        return this.body.getPosition().y;
    }

    public Entity() {
        setTexture();
    }

    public void update(float deltaTime) {

    }

    public void render(Batch batch) {
        batch.draw(this.texture, this.getX(), this.getY());
    }

    public void createBody(Vector2 position) {
        Entity entity = new Entity();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        Body body = SpaceGame.world.createBody(bodyDef);

        this.body = body;
    }

    public void setSize(){
        this.width = 50;
        this.height = 50;
    }

    public void setTexture(){
        this.texture = null;
    }

    public void setPosition(float x, float y){
        this.body.setTransform(x, y, this.body.getAngle());
    }
}
