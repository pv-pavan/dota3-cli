package com.pv.dota3.cli.game.beans;

import com.pv.dota3.cli.game.enums.Hero;
import com.pv.dota3.cli.game.Player;

import java.io.Serializable;

/**
 * Class indicates check point in a game.
 *
 * Created by pv on 15/10/16.
 */
public class GameCheckPoint implements Serializable {

    private Player residentHero;
    private int winBonusHealth;

    public GameCheckPoint(Hero residentHero, int winBonusHealth) {
        this.residentHero = new Player(residentHero);
        this.winBonusHealth = winBonusHealth;
    }

    public Player getResidentHero() {
        return residentHero;
    }

    public void setResidentHero(Player residentHero) {
        this.residentHero = residentHero;
    }

    public int getWinBonusHealth() {
        return winBonusHealth;
    }

    public void setWinBonusHealth(int winBonusHealth) {
        this.winBonusHealth = winBonusHealth;
    }
}
