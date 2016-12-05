package com.space.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.space.game.Entity;
import com.space.game.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasutaja on 05.12.2016.
 */
public class EntityManager
{
    public static List<Entity> entities;

    static
    {
        entities = new ArrayList<Entity>();
    }

    public static BaseShip spawnBaseShip(Vector2 position)
    {
        BaseShip ship = new BaseShip();
        ship.init(position);

        EntityManager.entities.add(ship);
        return ship;
    }
    
    public static PlayerShip spawnPlayerShip(Vector2 position)
    {
        PlayerShip ship = new PlayerShip();
        ship.init(position);

        EntityManager.entities.add(ship);
        return ship;
    }
    
    public static Bullet spawnBullet(Vector2 position, boolean upwardDirection)
    {
        Bullet bullet = new Bullet();
        bullet.init(position);
        bullet.setDirection(upwardDirection);

        EntityManager.entities.add(bullet);
        return bullet;
    }
}
