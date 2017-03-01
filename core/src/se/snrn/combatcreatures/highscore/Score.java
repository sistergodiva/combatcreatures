package se.snrn.combatcreatures.highscore;

import se.snrn.combatcreatures.entities.player.Player;

public class Score {
    private Player player;
    private int score;
    private int level;
    private int floor;

    public Score(Player player) {

        this.player = player;
        this.score = player.getScore();
        this.level = player.getLevel();
        this.floor = player.getFloor();
    }
}
