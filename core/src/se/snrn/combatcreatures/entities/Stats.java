package se.snrn.combatcreatures.entities;

public class Stats {

    private int magicAttack;
    private int normalAttack;
    private int magicDefence;
    private int normalDefence;
    private int health;
    private int mana;

    public Stats(int magicAttack, int normalAttack, int magicDefence, int normalDefence, int health, int mana) {
        this.magicAttack = magicAttack;
        this.normalAttack = normalAttack;
        this.magicDefence = magicDefence;
        this.normalDefence = normalDefence;
        this.health = health;
        this.mana = mana;
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

    public void setMana(int mana) {
        this.mana = mana;
    }
}
