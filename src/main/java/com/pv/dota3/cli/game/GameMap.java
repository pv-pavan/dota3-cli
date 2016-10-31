package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.beans.GameCheckPoint;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class represents a game map for explorable game. Map contains checkpoints with enemy heroes
 *
 * Created by pv on 14/10/16.
 */
public class GameMap implements Serializable{
    private List<GameCheckPoint> gameCheckPointList = new LinkedList();


    private GameCheckPoint currentCheckPoint;
    private int currentCheckPointIndex;


    public GameMap(List<GameCheckPoint> gameCheckPoints){
        this.gameCheckPointList = gameCheckPoints;
        currentCheckPointIndex = -1;
    }

    public List<GameCheckPoint> getGameCheckPointList() {
        return gameCheckPointList;
    }

    public GameCheckPoint getCurrentCheckPoint() {
        return currentCheckPoint;
    }

    public boolean isFinalCheckPoint(){
        return currentCheckPointIndex ==  gameCheckPointList.size()-1;
    }

    public void setCurrentCheckPoint(GameCheckPoint currentCheckPoint) {
        this.currentCheckPoint = currentCheckPoint;
    }

    public GameCheckPoint next(){
        if(currentCheckPoint!=null && currentCheckPoint.getResidentHero().getLife()>0){
            System.out.println("Continue fighting...\n"+currentCheckPoint.getResidentHero().toString());
            return currentCheckPoint;
        }

        if( currentCheckPointIndex == (gameCheckPointList.size()-1)){
            System.out.println("You have reached your final check point. Be ready to fight - "+currentCheckPoint.getResidentHero().getHero().name());
        }
        else{
            currentCheckPointIndex++;
            currentCheckPoint = gameCheckPointList.get(currentCheckPointIndex);
            System.out.println("You have reached checkpoint# "+currentCheckPointIndex+". "+currentCheckPoint.getResidentHero().getHero().name()+" lurks around here.. be watchful..");
        }
        System.out.println("You will be awarded "+currentCheckPoint.getWinBonusHealth()+" bonus health if you win.");
        System.out.println(currentCheckPoint.getResidentHero().toString());

        return currentCheckPoint;
    }

    public void resetMap(){
        currentCheckPointIndex = -1;
    }

}
