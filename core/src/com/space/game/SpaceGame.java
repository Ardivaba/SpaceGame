package com.space.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.game.Entities.EnemyShip;
import com.space.game.Entities.EntityManager;
import com.space.game.Entities.GameLogic;
import com.space.game.Entities.PlayerShip;

import java.util.ArrayList;

public class SpaceGame extends ApplicationAdapter
{
	SpriteBatch batch;
	
	Texture playerHealthTexture;
	SpriteBatch overlayBatch;
	Texture img;
	OrthographicCamera camera;

	protected Box2DDebugRenderer physicsDebugRenderer;
	public static World world;

	CharSequence str;
	BitmapFont font;
	public static boolean gameStarted = false;
	
	public static PlayerShip playerShip;
	GameLogic game;
	
	public static final int GAME_WIDTH = 800;
	
	public final int ZOOM = 60;
	
	boolean firstUpdate = true;
	
	@Override
	public void create ()
	{
		createCamera();
		createGame();
		
		// Set up
		playerHealthTexture = new Texture(Gdx.files.internal("UI/playerLife1_blue.png"));
		batch = new SpriteBatch();
		overlayBatch = new SpriteBatch();
		
		// Menu
		str = "Press [Enter] to start game";
		font = new BitmapFont();

		spawnActors();
	}
	
	private void spawnActors()
	{
		EnemyShip ship = new EnemyShip();
		ship.init(new Vector2(100, 300));
		
		playerShip = EntityManager.spawnPlayerShip(new Vector2(0, -Gdx.graphics.getHeight()));
		// Worst hack of the century i guess
		if(!gameStarted)
			EntityManager.entities.remove(playerShip);
	}
	
	private void createGame()
	{
		game = new GameLogic();
		game.addEnemies(); // Don't even know why i have to do it this way...probably being a derp
	}
	
	private void createCamera()
	{
		camera = new OrthographicCamera();
		camera.zoom = ZOOM;
		camera.update();
		
	}
	@Override
	public void render ()
	{		
		batch.begin();
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		game.update(deltaTime);
		camera.update();
		
		// Update entities
		// Copying list to avoid ConcurrentModificationException
		ArrayList<Entity> tempList = new ArrayList<Entity>();
		tempList.addAll(EntityManager.entities);
		for(Entity entity : tempList)
		{
			entity.preUpdate();
			entity.update(deltaTime);
		}

		checkCollisions(tempList); 

		// Draw background and start batch draw
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		


		// Draw all entities
		for(Entity entity : EntityManager.entities)
		{
			entity.render(batch);
		}

		// End batch draw
		batch.end();
		batch.setProjectionMatrix(camera.combined);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		camera.position.set(camera.position.x - 1, Gdx.graphics.getHeight() / 2, 0);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			camera.position.set(camera.position.x + 1, Gdx.graphics.getHeight() / 2, 0);
		
		camera.position.x = MathUtils.lerp(camera.position.x, playerShip.position.x, deltaTime * 2.5f);

		
		overlayBatch.begin();

		if(!gameStarted)
		{
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
			{
				EntityManager.destroyAllEntities();
				gameStarted = true;
				spawnActors();
				createGame();
			}

			font.draw(overlayBatch, str, (Gdx.graphics.getWidth() - str.length() * 5) / 2, Gdx.graphics.getHeight() / 2);
		}

		if(playerShip.health <= 0.0f)
		{
			gameStarted = false;
		}

		for(float i = 0; i < playerShip.health; i++)
		{
			// More hacks...
			if(gameStarted)
				overlayBatch.draw(playerHealthTexture, i * (playerHealthTexture.getWidth() + 10), 0);
		}
		
		font.draw(overlayBatch, (CharSequence)("Score: " + String.valueOf((int)game.score)), 5, 50 );

		overlayBatch.end();
	}
	
	private void checkCollisions(ArrayList<Entity> entities)
	{
		// Have to use copied list
		for(Entity entity : entities)
		{
			for(Entity otherEntity : entities)
			{
				if(entity == otherEntity)
					continue;

				if(entity.body.overlaps(otherEntity.body))
				{
					entity.collision(otherEntity);
				}
			}
		}
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
