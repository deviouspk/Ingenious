package com.ingenious.models;

public class Score {

    public int redScore = 0;
    public int greenScore = 0;
    public int blueScore = 0;
    public int orangeScore = 0;
    public int yellowScore = 0;
    public int purpleScore = 0;

    public Score() {

    }

    private Score(int redScore, int greenScore, int blueScore, int orangeScore, int yellowScore, int purpleScore) {
        this.redScore = redScore;
        this.greenScore = greenScore;
        this.blueScore = blueScore;
        this.orangeScore = orangeScore;
        this.yellowScore = yellowScore;
        this.purpleScore = purpleScore;
    }

    public Score getClone() {
        return new Score(this.redScore, this.greenScore, this.blueScore, this.orangeScore, this.yellowScore, this.yellowScore);
    }

    public int[] getScores() {
        int[] scores = new int[6];
        scores[0] = this.redScore;
        scores[1] = this.greenScore;
        scores[2] = this.blueScore;
        scores[3] = this.orangeScore;
        scores[4] = this.yellowScore;
        scores[5] = this.purpleScore;
        return scores;
    }

    public int[] toArray() {
        return new int[]{redScore, greenScore, blueScore, orangeScore, yellowScore, purpleScore};
    }

}
