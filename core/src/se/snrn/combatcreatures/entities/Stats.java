package se.snrn.combatcreatures.entities;

import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.items.Equipment.Stat;

public class Stats {
    private int str;

    private int dex;
    private int wis;

    private int hp;
    private int ep;
    private int mp;

    private int flame;
    private int grass;
    private int water;

    private int[] stats;

    public Stats(int str, int dex, int wis, int hp, int ep, int mp, int flame, int grass, int water) {
        stats = new int[9];

        stats[0] = str;
        stats[1] = dex;
        stats[2] = wis;
        stats[3] = hp;
        stats[4] = ep;
        stats[5] = mp;
        stats[6] = flame;
        stats[7] = grass;
        stats[8] = water;
    }

    public Stats() {
        stats = new int[9];
        stats[0] = this.str = 10;
        stats[1] = this.dex = 10;
        stats[2] = this.wis = 10;
        stats[3] = this.hp = 65;
        stats[4] = this.ep = 65;
        stats[5] = this.mp = 65;
        stats[6] = this.flame = 65;
        stats[7] = this.grass = 65;
        stats[8] = this.water = 65;
    }

    public Stats(JsonValue jsonStats) {
        stats = new int[9];

        stats[0] = jsonStats.getInt("str");
        stats[1] = jsonStats.getInt("dex");
        stats[2] = jsonStats.getInt("wis");
        stats[3] = jsonStats.getInt("hp");
        stats[4] = jsonStats.getInt("ep");
        stats[5] = jsonStats.getInt("mp");
        stats[6] = jsonStats.getInt("flame");
        stats[7] = jsonStats.getInt("grass");
        stats[8] = jsonStats.getInt("water");
    }


    public int getStatFromEnum(Stat statEnum) {
        return stats[statEnum.ordinal()];
    }

    public void setStatFromEnum(Stat statEnum, int value) {
        stats[statEnum.ordinal()] = value;
    }

    public void increaseStatFromEnum(Stat statEnum) {
        stats[statEnum.ordinal()]++;
    }

    public void decreaseStatFromEnum(Stat statEnum) {
        stats[statEnum.ordinal()]--;
    }
}
