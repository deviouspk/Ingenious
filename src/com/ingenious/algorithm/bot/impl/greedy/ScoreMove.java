package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.model.Move;
import com.ingenious.model.Tile;

public class ScoreMove {
    public Move move;
    public int scoreHead=0;
    public int scoreTail=0;
    public Tile head;
    public Tile tail;

    public ScoreMove(Move move, int scoreHead, int scoreTail, Tile head, Tile tail) {
        this.move = move;
        this.head = head;
        this.tail = tail;
        this.scoreHead = scoreHead;
        this.scoreTail = scoreTail;
    }

    public int getTotalScore(){
        int score = this.getScoreHead() + this.getScoreTail();
        return score;
    }

    public int getScoreHead(){
        return this.scoreHead;
    }

    public int getScoreTail(){
        return scoreTail;
    }

    public Tile[] getColor(){
        Tile [] colors = {head,tail};
        return colors;
    }

    public Move getMove(){
        return this.move;
    }

    public boolean contains_color(Tile color){
        if(color.equals(head) || color.equals(tail)){
            return true;
        }
        return false;
    }

    public int scoreColor(Tile color){
        if(head.equals(tail) && color.equals(head)){
            return scoreTail+scoreHead;
        }
        if(head.equals(color)){
            return scoreHead;
        }
        if(tail.equals(color)){
            return scoreTail;
        }
        return 0;
    }
}
