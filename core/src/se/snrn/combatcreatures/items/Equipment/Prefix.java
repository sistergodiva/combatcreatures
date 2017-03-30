package se.snrn.combatcreatures.items.Equipment;


public enum Prefix {
    CARAMEL("Caramel", Stat.NDE, +2),
    SPICY("Spicy", Stat.MAT, +1);


    private final String name;
    private final Stat stat;
    private final int value;

    Prefix(String name, Stat stat, int value) {

        this.name = name;
        this.stat = stat;
        this.value = value;
    }
}
