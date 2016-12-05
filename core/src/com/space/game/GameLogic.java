package com.space.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.Enemies.EnemyShip;
import com.space.game.Entities.Enemies.FollowingEnemy;
import com.space.game.Entities.Enemies.SimpleMovingEnemy;
import com.space.game.Entities.EntityManager;
import com.space.game.SpaceGame;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class GameLogic 
{
    private Random random;
    public float spawnTimer = 5f;
    public float maxSpawnTimer = 5.0f;
    
    public float score = 0.0f;
    
    public boolean running = true;
    
    private ArrayList<Class> enemyList = new ArrayList<Class>();
    
    private static Sound backgroundSound;
    
    public void GameLogic()
    {
        running = true;
        random = new Random();
        score = 0.0f;
    }
    
    public void addEnemies()
    {
        enemyList.add(EnemyShip.class);
        enemyList.add(SimpleMovingEnemy.class);
        enemyList.add(FollowingEnemy.class);
    }
    
    public void update(float deltaTime)
    {
        ArrayList<EnemyShip> enemyShips = EntityManager.getEnemyShips();
        
        if(spawnTimer < 0.0f)
        {
            spawnRandomEnemyShip();
            spawnTimer = maxSpawnTimer;
        }
        
        spawnTimer -= deltaTime;
        if(enemyShips.size() < 1)
            spawnTimer -= deltaTime * 2.0f;
        
        if(SpaceGame.gameStarted)
            score += deltaTime;
        
        if(maxSpawnTimer > 0.5f)
            maxSpawnTimer -= deltaTime / 10.0f;
    }
    
    private void spawnRandomEnemyShip()
    {
        Random random = new Random();

        try
        {
            EnemyShip ship = (EnemyShip) enemyList.get(random.nextInt(enemyList.size())).newInstance();
            
            float posX = -Gdx.graphics.getWidth() + (random.nextFloat() * Gdx.graphics.getWidth());
            posX = -800 + random.nextInt(800 * 2);
            float posY = Gdx.graphics.getHeight() * 2;
            ship.init(new Vector2(posX, posY));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
