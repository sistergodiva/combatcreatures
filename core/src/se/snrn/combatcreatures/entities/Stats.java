package se.snrn.combatcreatures.entities;

import se.snrn.combatcreatures.items.Equipment.Stat;

public class Stats {


    private int[] stats;

    public Stats(int magicAttack, int normalAttack, int magicDefence, int normalDefence, int health, int mana) {


        stats = new int[6];

        stats[0] = magicAttack;
        stats[1] = normalAttack;
        stats[2] = magicDefence;
        stats[3] = normalDefence;
        stats[4] = health;
        stats[5] = mana;

    }

    public int getStatFromEnum(Stat statEnum) {
        return stats[statEnum.ordinal()];
    }

    public void increaseStatFromEnum(Stat statEnum) {
        stats[statEnum.ordinal()]++;
    }

    public void decreaseStatFromEnum(Stat statEnum) {
        stats[statEnum.ordinal()]--;
    }


}
