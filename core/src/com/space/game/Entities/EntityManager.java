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

    public static BaseShip SpawnBaseShip(Vector2 position)
    {
        BaseShip ship = new BaseShip();
        ship.init(position);
        
        entities.add(ship);

        return ship;
    }
}
