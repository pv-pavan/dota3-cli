package com.pv.dota3.cli.game.consumables;

import com.pv.dota3.cli.game.GameObject;
import com.pv.dota3.cli.game.Player;

/**
 * Class indicates a consumable when used provides additional armour to player
 *
 * Created by pv on 23/10/16.
 */
public class ArmourPack extends AbstractConsumable {

    public ArmourPack(int attrVal){
        setAttrValue(attrVal);
    }

    @Override
    public void apply(Player player) {
        player.setArmour(player.getArmour() + getAttrValue());
        super.apply();
    }
}
