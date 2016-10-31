package com.pv.dota3.cli.game.consumables;

import com.pv.dota3.cli.game.Player;

/**
 * Class indicates a consumable when applied to player gives bonus life.
 *
 * Created by pv on 23/10/16.
 */
public class HealthPack extends AbstractConsumable {

    public HealthPack(int attrVal){
        setAttrValue(attrVal);
    }

    @Override
    public void apply(Player player) {
        player.setLife(player.getLife() + getAttrValue());
        super.apply();
    }
}
