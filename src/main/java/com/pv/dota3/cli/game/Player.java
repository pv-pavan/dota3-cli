package com.pv.dota3.cli.game;

import com.pv.dota3.cli.game.consumables.Consumable;
import com.pv.dota3.cli.game.enums.Hero;

import java.util.*;

/**
 * Created by pv on 14/10/16.
 */
public class Player extends GameObject {
    private Hero hero;
    private int armour;
    private int level;
    private int life;
    private int exp;

    private List<Consumable> consumables = new ArrayList<>();

    private static int EXP_LEVEL_UP = 25;

    public Player(Hero hero) {
        this.hero = hero;
        this.level = 0;
        this.exp = 0;
        this.armour = hero.getArmour();
        this.life = hero.getHealth();
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public void addConsumable(Consumable c){
        getConsumables().add(c);
    }

    public List<Consumable> getConsumables() {
        if(consumables==null) return new ArrayList<>();
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public void addExp(int exp){
        this.exp +=exp;
        if(this.exp>EXP_LEVEL_UP){
            this.exp = exp % EXP_LEVEL_UP;
            this.level++;
            System.out.println(hero.name()+" leveled up. Current level: "+level);
        }
    }

    public void addLife(int life){
        this.life +=life;
    }

    public void takeDamage(int damage){
        int armourSave = getRandArmourSave();
        if(armourSave == 0) System.out.println(" Pure damage. ");
        else System.out.println(" Armour save: "+armourSave+".");
        this.life -=damage;
        if(armourSave>damage){
            armourSave = damage;
        }
        this.life +=armourSave;
    }


    public int getRandArmourSave(){
        Random random = new Random();
        return random.nextInt(armour);
    }


    public Player attack(Player that){
        int randP = this.getHero().getRandAttackDmg();
        int randE = that.getHero().getRandAttackDmg();

        int expP = this.getHero().getExpPerAttack();
        int expE = that.getHero().getExpPerAttack();

        System.out.println();
        System.out.print("You did "+randP+ " damage to enemy.");
        that.takeDamage(randP);
        System.out.print("You took "+randE+ " damage.");
        this.takeDamage(randE);
        System.out.println(this.getInfo("Player")+" | "+that.getInfo("Enemy"));

        this.addExp(expP);
        that.addExp(expE);


        if(this.getLife()<20 && this.getConsumables().size() > 0){
            System.out.println("\nYou are about to die.. Using your inventory items:");
            ListIterator<Consumable> iterator = getConsumables().listIterator();
            while(iterator.hasNext()){
                Consumable c = iterator.next();
                c.apply(this);
                iterator.remove();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Your stats now: "+toString());
        }

        return that;
    }

    public String getInfo(String prefix){
        StringBuilder builder = new StringBuilder();
        return builder
                .append("["+prefix+"] ").append("Health- ").append(life).append(" Exp- ").append(exp).append(" Level- ").append(level).toString();
    }


    public String toString(){
        StringBuilder builder = new StringBuilder();
        return builder
                .append("======================\n")
                .append("Hero - "+hero.name()+"\n")
                .append(hero.getHeroDesc()+"\n")
                .append("Level - "+level+"\n")
                .append("Health - "+life+"\n")
                .append("Exp - "+exp+"\n")
                .append("Armour - "+armour+"\n")
                .append("Item inventory- "+getConsumables().size()+"\n")
                .append("======================\n")
                .toString();
    }
}
