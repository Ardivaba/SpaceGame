package com.space.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.space.game.Entities.Enemies.EnemyShip;
import com.space.game.Entities.Projectiles.Projectile;
import com.space.game.Entity;

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
        bulletTypes.add(Projectile.class);
    }

    public static BaseShip spawnBaseShip(Vector2 position)
    {
        BaseShip ship = new BaseShip();
        ship.init(position);
        
        return ship;
    }
    
    public static PlayerShip spawnPlayerShip(Vector2 position)
    {
        PlayerShip ship = new PlayerShip();
        ship.init(position);
        
        return ship;
    }

    public static Projectile spawnBullet(Vector2 position, boolean upwardDirection)
    {
        Projectile bullet = new Projectile();
        bullet.init(position);
        bullet.setDirection(upwardDirection);
        
        return bullet;
    }
    
    public static void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }
    
    public static ArrayList<EnemyShip> getEnemyShips()
    {
        ArrayList<EnemyShip> enemyShips = new ArrayList<EnemyShip>();
        for(Entity entity : entities)
        {
            if(entity.getClass().isAssignableFrom(EnemyShip.class))
            {
                enemyShips.add((EnemyShip) entity);
            }
        }
        
        return enemyShips;
    }

    public static void destroyAllEntities()
    {
        entities.clear();
    }
}
