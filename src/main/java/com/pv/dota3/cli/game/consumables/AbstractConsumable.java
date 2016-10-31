package com.pv.dota3.cli.game.consumables;

import com.pv.dota3.cli.game.GameObject;

/**
 * Created by pv on 23/10/16.
 */
public abstract class AbstractConsumable extends GameObject  implements Consumable{
    protected int attrValue;
    protected ConsumableStatus status = ConsumableStatus.NEW;

    public int getAttrValue() {
        return attrValue;
    }

    protected void setAttrValue(int attrValue) {
        this.attrValue = attrValue;
    }

    public ConsumableStatus getStatus() {
        return status;
    }

    protected void setStatus(ConsumableStatus status) {
        this.status = status;
    }

    protected void apply(){
        this.status = ConsumableStatus.USED;
    }
}
