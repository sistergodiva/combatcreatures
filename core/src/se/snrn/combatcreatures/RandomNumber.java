package se.snrn.combatcreatures;

public class RandomNumber {

    public static int range(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


    public static int d4() {
        return range(1, 4);
    }

    public static int d6() {
        return range(1, 6);
    }

    public static int d8() {
        return range(1, 8);
    }

    public static int d10() {
        return range(1, 10);
    }

    public static int d12() {
        return range(1, 12);
    }
}
