package se.snrn.combatcreatures.entities;


public class Modifier {

    private String name;
    private String stat;
    private int amount;

    public Modifier(String name, String stat, int amount) {
        this.name = name;
        this.stat = stat;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getStat() {
        return stat;
    }

    public int getAmount() {
        return amount;
    }

}
