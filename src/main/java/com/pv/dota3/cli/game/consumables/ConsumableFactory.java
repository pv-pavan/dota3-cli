package com.pv.dota3.cli.game.consumables;

import java.util.Random;

/**
 * Factory class that generates consumables given type
 *
 * Created by pv on 31/10/16.
 */
public class ConsumableFactory {
    private static ConsumableFactory ourInstance = new ConsumableFactory();

    public static ConsumableFactory getInstance() {
        return ourInstance;
    }

    private ConsumableFactory() {
    }


    /**
     * Generate a consumable given consumabletype
     * @param type
     * @return
     */
    public Consumable getConsumable(ConsumableType type){
        Random rand = new Random();
        ArmourPack armourPack =  new ArmourPack(rand.nextInt(2)+1);
        HealthPack healthPack = new HealthPack(rand.nextInt(20)+10);


        switch (type){
            case ARMOUR:
                System.out.println("You collected a armour pack..When consumed grants addl. armour: "+armourPack.getAttrValue());
                return armourPack;
            case LIFE:
            default:
                System.out.println("You collected a health pack..When consumed you gain life: "+healthPack.getAttrValue());
                return healthPack;
        }
    }
}
