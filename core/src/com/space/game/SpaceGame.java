package com.space.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.space.game.Entities.EntityManager;
import com.space.game.Entities.PlayerShip;

import java.util.ArrayList;

public class SpaceGame extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;

	protected Box2DDebugRenderer physicsDebugRenderer;
	public static World world;
	
	PlayerShip playerShip;
	
	public final int ZOOM = 35;
	
	@Override
	public void create ()
	{
		createCamera();
		
		// Set up
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		spawnActors();
	}
	
	private void spawnActors()
	{
		EntityManager.spawnBaseShip(new Vector2(100, 300));
		
		playerShip = EntityManager.spawnPlayerShip(new Vector2(0, 0));
	}
	
	private void createCamera()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(30, 30 * (h / w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.zoom = ZOOM;
	}

	@Override
	public void render ()
	{
		camera.update();
		
		// Update entities
		ArrayList<Entity> tempList = new ArrayList<Entity>();
		tempList.addAll(EntityManager.entities);
		for(Entity entity : tempList)
		{
			entity.preUpdate();
			entity.update(Gdx.graphics.getDeltaTime());
		}

		for(Entity entity : tempList)
		{
			for(Entity otherEntity : tempList)
			{
				if(entity == otherEntity)
					continue;
				
				if(entity.body.overlaps(otherEntity.body))
				{
					entity.collides(otherEntity);
				}
			}
		}

		// Draw background and start batch draw
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// Draw all entities
		for(Entity entity : EntityManager.entities)
		{
			entity.render(batch);
		}

		// End batch draw
		batch.end();
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = 30f;
		camera.viewportHeight = 30f * height/width;
		camera.update();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resume() {
	}
}
