package com.pv.dota3.cli.game.factory;

import com.pv.dota3.cli.game.Game;
import com.pv.dota3.cli.game.Player;
import com.pv.dota3.cli.game.enums.Hero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by pv on 23/10/16.
 */
public abstract class AbstractGameGenerator implements IGameGenerator {
    public abstract Game generate();


    public Hero chooseHero(){
        Hero heroChosen;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Step 2. Choose your hero: \n A. JuggerNaut (Default) \n B. Mirana \n C. BountyHunter\n D. WitchDoctor \n \nYour Option: ");
        String option = scanner.nextLine();
        switch (option){
            case "A":
                heroChosen = Hero.JuggerNaut;
                break;
            case "B":
                heroChosen = Hero.Mirana;
                break;
            case "C":
                heroChosen = Hero.BountyHunter;
                break;
            case "D":
                heroChosen = Hero.WitchDoctor;
                break;
            default:
                heroChosen = Hero.JuggerNaut;
                System.out.println("I do not understand your option. Picking default hero..");
        }
        return heroChosen;
    }
    public String getGameName(){
        System.out.println("Generating game...\n");
        System.out.print("Step 1. Choose a name for your game (Random) :");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        return generateGameName(temp);
    }
    private String generateGameName(String temp){
        if(temp == null || temp.isEmpty()) temp = "GA";
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyHHmmss");
        Date resultdate = new Date(System.currentTimeMillis());
        return temp+"_"+sdf.format(resultdate);
    }
}
