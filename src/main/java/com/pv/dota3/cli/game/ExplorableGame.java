package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.enums.FightResultCode;

import java.io.Serializable;

/**
 * Class indicates game that a player can explore, collect consumables and fight enemy heroes at check points.
 *
 * Created by pv on 14/10/16.
 */
public class ExplorableGame extends Game {
    private GameMap gameMap;

    public ExplorableGame(String name, Player p) {
        setName(name);
        setPlayer(p);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void explore(){

        System.out.println("Exploring...");

        try {
            Thread.sleep(2000);
            GameUtil.assignRandomConsumable(getPlayer());
        }catch (Exception ex){

        }
        gameMap.next();
    }

    public FightResultCode fight(){

        System.out.println("Fighting...");
        Player enemy = this.gameMap.getCurrentCheckPoint().getResidentHero();

        while(getPlayer().getLife() > 0 && enemy.getLife() > 0){
            enemy = getPlayer().attack(enemy);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.gameMap.getCurrentCheckPoint().setResidentHero(enemy);

        if(getPlayer().getLife() > 0 && this.getGameMap().getCurrentCheckPoint().getResidentHero().getLife()<=0){
            System.out.println("\nGame won.\n\n");
            return FightResultCode.WON;
        }else{
            System.out.println("\nGame Lost.\n\n");
            return FightResultCode.LOST;
        }

    }

    @Override
    public boolean isFinalCheckPoint() {
        return gameMap.isFinalCheckPoint();
    }
}
