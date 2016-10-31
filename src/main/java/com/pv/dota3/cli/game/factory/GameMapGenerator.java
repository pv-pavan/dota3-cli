package com.pv.dota3.cli.game.factory;

import com.pv.dota3.cli.game.GameUtil;
import com.pv.dota3.cli.game.beans.GameCheckPoint;
import com.pv.dota3.cli.game.GameMap;
import com.pv.dota3.cli.game.enums.Hero;

import java.util.*;

/**
 * Created by pv on 22/10/16.
 */
public class GameMapGenerator {

    /**
     * Generates game map for a given hero(player)
     * @param forHero
     * @return
     */
    public static GameMap generate(Hero forHero){
        List<GameCheckPoint> checkPoints = new LinkedList<>();
        Random random = new Random();
        for(Hero h: GameUtil.getAvailableHeros(forHero)){
            checkPoints.add(new GameCheckPoint(h, random.nextInt(3)+8));
        }
        return new GameMap(checkPoints);
    }
}
