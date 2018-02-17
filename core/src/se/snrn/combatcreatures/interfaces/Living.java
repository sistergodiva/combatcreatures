package se.snrn.combatcreatures.interfaces;


public interface Living {
    boolean isAlive();
    void setAlive(boolean alive);
    void takeDamage(int damage);
    void healDamage(int damage);
    int getHp();
    void die();

    void tick();
}
