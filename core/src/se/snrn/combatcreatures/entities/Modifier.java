package se.snrn.combatcreatures.entities;


public class Modifier {

    private String name;
    private Stats stats;
    private int amount;

    public Modifier(String name, Stats stats, int amount) {
        this.name = name;
        this.stats = stats;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Stats getStat() {
        return stats;
    }

    public int getAmount() {
        return amount;
    }

}
