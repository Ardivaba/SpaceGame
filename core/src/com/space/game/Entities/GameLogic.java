package com.space.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.space.game.Entity;
import com.space.game.SpaceGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class GameLogic 
{
    private Random random;
    
    public void GameLogic()
    {
        random = new Random();
    }
    
    public void update(float deltaTime)
    {
        ArrayList<EnemyShip> enemyShips = EntityManager.getEnemyShips();
        
        if(enemyShips.size() < 1)
        {
            spawnRandomEnemyShip();
        }
    }
    
    private void spawnRandomEnemyShip()
    {
        Random random = new Random();

        EnemyShip ship = new EnemyShip();
        ship.init(new Vector2(-Gdx.graphics.getWidth() + (random.nextFloat() * Gdx.graphics.getWidth()), Gdx.graphics.getHeight() * 2 ));
        
        System.out.println("Spawning enemy ship...");
    }
}
