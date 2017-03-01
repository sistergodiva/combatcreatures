package se.snrn.combatcreatures.highscore;

import se.snrn.combatcreatures.entities.player.Player;

public class Score {
    private  String namn;
    private Player player;
    private int score;
    private int level;
    private int floor;
    private  String startTime;
    private  String endTime;

    public Score(Player player) {

        this.player = player;
        this.score = player.getScore();
        this.level = player.getLevel();
        this.floor = player.getFloor();
    }

    public Score(String namn, int score, int level, int floor, String startTime, String endTime) {


        this.namn = namn;
        this.score = score;
        this.level = level;
        this.floor = floor;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getNamn() {
        return namn;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getFloor() {
        return floor;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
