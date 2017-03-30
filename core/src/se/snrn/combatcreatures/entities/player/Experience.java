package se.snrn.combatcreatures.entities.player;


public class Experience {

    static int baseXp = 10;
    static int[] levelXp;


    public Experience() {
        levelXp = new int[20];
        for (int i = 0; i < 20; i++) {
             levelXp[i] = (int) (baseXp * Math.pow(i, 2));
        }

    }


    public static int getLevel(int xp) {
        for (int i = 0; i < levelXp.length; i++) {
            if(xp < levelXp[i]){
                return i;
            }
        }
        return 0;
    }

}
