package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.consumables.Consumable;
import com.pv.dota3.cli.game.consumables.ConsumableFactory;
import com.pv.dota3.cli.game.consumables.ConsumableType;
import com.pv.dota3.cli.game.enums.Hero;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by pv on 23/10/16.
 */
public class GameUtil {

    private static Set<Hero> allHeroes;
    private static Set<Hero> availableHeroes;

    static {
        allHeroes = new HashSet<>();
        for(Hero a:Hero.values()){
            allHeroes.add(a);
        }
    }
    public static Set<Hero> getAvailableHeros(Hero forHero){
        availableHeroes = allHeroes;
        availableHeroes.remove(forHero);
        return availableHeroes;
    }

    public static void assignRandomConsumable(Player player){
        Random rand = new Random();
        int noOfConsumables = rand.nextInt(3);
        ConsumableType[] ca = ConsumableType.values();

        for(int i = 0; i<noOfConsumables; i++){
            int indexOfType = rand.nextInt(ca.length);
            player.addConsumable(ConsumableFactory.getInstance().getConsumable(ca[indexOfType]));
        }
    }
}
