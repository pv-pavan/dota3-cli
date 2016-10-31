package com.pv.dota3.cli.game.factory;

import com.pv.dota3.cli.game.ExplorableGame;
import com.pv.dota3.cli.game.enums.Hero;
import com.pv.dota3.cli.game.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by pv on 15/10/16.
 */
public class GameGenerator extends AbstractGameGenerator{

    public ExplorableGame generate(){
        String gameName = getGameName();
        Hero hero = chooseHero();
        ExplorableGame g = new ExplorableGame(gameName, new Player(hero));

        System.out.println("Generating map...\n");
        g.setGameMap(GameMapGenerator.generate(hero));
        return g;
    }


}
