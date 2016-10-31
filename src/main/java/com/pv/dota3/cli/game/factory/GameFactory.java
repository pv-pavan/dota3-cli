package com.pv.dota3.cli.game.factory;

import com.pv.dota3.cli.game.Game;
import com.pv.dota3.cli.game.enums.GameType;

/**
 * Created by pv on 23/10/16.
 */
public class GameFactory {
    public static Game generateGame(GameType type){
        IGameGenerator g = null;
        switch (type){
            case MAP_EXPORING:
                g = new GameGenerator();
                break;
            case HERO_PRACTICE:
            default:
                g = new PracticeGameGenerator();
        }

        return g.generate();
    }
}
