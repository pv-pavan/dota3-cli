package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.consumables.Consumable;
import com.pv.dota3.cli.game.enums.FightResultCode;

import java.io.Serializable;

/**
 * Created by pv on 22/10/16.
 */
public abstract class Game implements Serializable {

    private String name;
    private Player player;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void explore();
    public abstract FightResultCode fight();
    public abstract boolean isFinalCheckPoint();


    public void consume(Consumable object){

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game that = (Game) o;

        if (!name.equals(that.name)) return false;
        return player != null ? player.equals(that.player) : that.player == null;

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
