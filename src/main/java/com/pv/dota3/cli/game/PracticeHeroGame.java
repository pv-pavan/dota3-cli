package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.enums.FightResultCode;

import java.io.Serializable;

/**
 * Created by pv on 23/10/16.
 */
public class PracticeHeroGame extends Game {

    private Player enemy;

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public PracticeHeroGame(String name, Player hero, Player enemy) {
        setName(name);
        setPlayer(hero);
        this.enemy = enemy;
    }

    private boolean finalCheckPoint = false;

    @Override
    public void explore() {
        System.out.println("Exploring....");
        //do nothing
    }

    @Override
    public FightResultCode fight() {
        while(getPlayer().getLife() > 0 && enemy.getLife() > 0){
            enemy = getPlayer().attack(enemy);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(getPlayer().getLife() > 0 && enemy.getLife() <=0){
            finalCheckPoint = true;
            return FightResultCode.WON;
        }else{
            return FightResultCode.LOST;
        }
    }

    @Override
    public boolean isFinalCheckPoint() {
        return finalCheckPoint;
    }
}
