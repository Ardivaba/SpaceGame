package com.space.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.game.Entities.EntityManager;

public class SpaceGame extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture img;

	protected Box2DDebugRenderer physicsDebugRenderer;
	public static World world;
	
	@Override
	public void create ()
	{
		// Set up box2d
		world = new World(new Vector2(0, 0), false);
		physicsDebugRenderer = new Box2DDebugRenderer();

		// Set up
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		EntityManager.SpawnBaseShip(new Vector2(20, 20));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);

		for(Entity entity : EntityManager.entities)
		{
			entity.render(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		img.dispose();
	}
}
