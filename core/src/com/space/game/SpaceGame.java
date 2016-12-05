package com.space.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.space.game.Entities.Enemies.EnemyShip;
import com.space.game.Entities.EntityManager;
import com.space.game.Entities.PlayerShip;

import java.util.ArrayList;

public class SpaceGame extends ApplicationAdapter
{
	// Rendering variables
	SpriteBatch batch;
	SpriteBatch overlayBatch;
	SpriteBatch backgroundBatch;
	Texture backgroundTexture;
	
	// Overlay textures
	Texture playerHealthTexture;
	Texture img;

	// Menu overlay font variables
	CharSequence str;
	BitmapFont font;

	// Camera related variables
	OrthographicCamera camera;
	public final int ZOOM = 60;
	
	// Gameplay variables
	GameLogic game;
	public static PlayerShip playerShip;
	public static boolean gameStarted = false;
	boolean firstUpdate = true;
	
	@Override
	public void create ()
	{
		createCamera();
		createGame();
		
		// Set up background variables
		backgroundTexture = new Texture(Gdx.files.internal("bg.png"));
		backgroundBatch = new SpriteBatch();
		
		// Set overlay variables
		playerHealthTexture = new Texture(Gdx.files.internal("UI/playerLife1_blue.png"));
		batch = new SpriteBatch();
		overlayBatch = new SpriteBatch();
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
		// Draw background and start batch draw
		Gdx.gl.glClearColor(0f, 0.f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		backgroundBatch.begin();
		//backgroundBatch.draw(backgroundTexture, -400, 0);
		backgroundBatch.end();
		
		batch.begin();
		batch.draw(backgroundTexture, (-backgroundTexture.getWidth() * 4) / 2, (-backgroundTexture.getHeight() * 4) / 2, backgroundTexture.getWidth() * 4, backgroundTexture.getHeight() * 4);
		
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

		// Draw all entities
		for(Entity entity : EntityManager.entities)
		{
			entity.render(batch);
		}

		// End batch draw
		batch.end();
		batch.setProjectionMatrix(camera.combined);
		
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
