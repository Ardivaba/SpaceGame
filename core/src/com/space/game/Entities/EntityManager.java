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
    public static List<Class> bulletTypes;

    static
    {
        entities = new ArrayList<Entity>();
        bulletTypes = new ArrayList<Class>();
    }
    
    private static void registerBulletTypes()
    {
        bulletTypes.add(Bullet.class);
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


    public static void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }
}
