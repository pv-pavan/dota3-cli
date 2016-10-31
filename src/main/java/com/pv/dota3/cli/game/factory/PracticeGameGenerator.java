package com.pv.dota3.cli.game.factory;

import com.pv.dota3.cli.game.Game;
import com.pv.dota3.cli.game.PracticeHeroGame;
import com.pv.dota3.cli.game.GameUtil;
import com.pv.dota3.cli.game.Player;
import com.pv.dota3.cli.game.enums.Hero;

import java.util.Random;
import java.util.Set;

/**
 * Created by pv on 23/10/16.
 */
public class PracticeGameGenerator extends AbstractGameGenerator {

    @Override
    public Game generate() {
        String name = getGameName();
        Hero hero = chooseHero();
        Set<Hero> availHeros = GameUtil.getAvailableHeros(hero);
        Random rand = new Random();
        int randIndex = rand.nextInt(availHeros.size());
        Hero enemy = (Hero) availHeros.toArray()[randIndex];
        return new PracticeHeroGame(name, new Player(hero), new Player(enemy));
    }
}
