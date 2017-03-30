package se.snrn.combatcreatures.entities;

import se.snrn.combatcreatures.items.Equipment.Stat;

public class Stats {

    private int magicAttack;
    private int normalAttack;
    private int magicDefence;
    private int normalDefence;
    private int health;
    private int mana;

    private int[] stats;

    public Stats(int magicAttack, int normalAttack, int magicDefence, int normalDefence, int health, int mana) {
        this.magicAttack = magicAttack;
        this.normalAttack = normalAttack;
        this.magicDefence = magicDefence;
        this.normalDefence = normalDefence;
        this.health = health;
        this.mana = mana;


        stats = new int[6];

        stats[0] = magicAttack;
        stats[1] = normalAttack;
        stats[2] = magicDefence;
        stats[3] = normalDefence;
        stats[4] = health;
        stats[5] = mana;

    }

    public int getStatFromEnum(Stat statEnum){
        return stats[statEnum.ordinal()];
    }

    public void increaseStatFromEnum(Stat statEnum){
        stats[statEnum.ordinal()]++;
    }

    public void decreaseStatFromEnum(Stat statEnum){
        stats[statEnum.ordinal()]--;
    }


    public int getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }

    public int getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(int normalAttack) {
        this.normalAttack = normalAttack;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public void setMagicDefence(int magicDefence) {
        this.magicDefence = magicDefence;
    }

    public int getNormalDefence() {
        return normalDefence;
    }

    public void setNormalDefence(int normalDefence) {
        this.normalDefence = normalDefence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "magicAttack=" + magicAttack +
                ", normalAttack=" + normalAttack +
                ", magicDefence=" + magicDefence +
                ", normalDefence=" + normalDefence +
                ", health=" + health +
                ", mana=" + mana +
                '}';
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
