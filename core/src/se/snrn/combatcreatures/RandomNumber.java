package se.snrn.combatcreatures;

public class RandomNumber {

    public static int range(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
