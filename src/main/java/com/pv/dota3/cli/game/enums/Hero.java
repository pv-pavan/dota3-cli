package com.pv.dota3.cli.game.enums;

import java.util.Random;

/**
 * Enumeration represents all available heroes in the game.
 *
 * Created by pv on 14/10/16.
 */
public enum Hero {

    JuggerNaut(5,9,3,75, 9),
    Mirana(3,7,2,100, 9 ),
    BountyHunter(4,10,3,65,10),
    WitchDoctor(3,7,3,75, 10),
    ;

    private int minAttack;
    private int maxAttack;
    private int armour;
    private int health;
    private int expPerAttack;
    private String heroDesc;

    Hero(int minAttack, int maxAttack, int armour, int health, int expPerAttack) {
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.armour = armour;
        this.health = health;
        this.expPerAttack = expPerAttack;
    }

    public int getMinAttack() {
        return minAttack;
    }

    static {
        JuggerNaut.setHeroDesc("Yurnero the Juggernaut is a melee agility hero whose abilities allow him to sprint into battle and recklessly devastate enemies in an impenetrable flurry of blades.");
        Mirana.setHeroDesc("Mirana, the Princess of the Moon, is a ranged Agility Hero that uses her abilities to surprise, chase, and assault enemies. She is an excellent huntress and widely known for her Sacred Arrow");
        BountyHunter.setHeroDesc("Gondar the Bounty Hunter is a melee agility Hero that excels at hunting, chasing down and killing single targets.");
        WitchDoctor.setHeroDesc("Zharvakko the Witch Doctor is a ranged intelligence Hero,  A master of voodoo curses and healing arts.");
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExpPerAttack() {
        return expPerAttack;
    }

    public void setExpPerAttack(int expPerAttack) {
        this.expPerAttack = expPerAttack;
    }

    public String getHeroDesc() {
        return heroDesc;
    }

    public void setHeroDesc(String heroDesc) {
        this.heroDesc = heroDesc;
    }


    public int getRandAttackDmg(){
        Random random = new Random();
        return random.nextInt(this.getMaxAttack()-this.getMinAttack())+this.getMinAttack();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        return builder.append("==========================\n")
                .append(getHeroDesc()).append("\n")
                .append("==========================").append("\n")
                .append("Attack Range: "+minAttack+" - "+maxAttack).append("\n")
                .append("Armour: "+armour).append("\n")
                .append("Health: "+health).append("\n")
                .append("Exp per attack: "+expPerAttack).append("\n")
                .append("==========================").append("\n").toString();
    }
}
