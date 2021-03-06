package com.ingenious.algorithm.bot.impl.qlearning;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by carolley on 10-Jan-18.
 */
public class qlearning extends BotAlgorithm{

    private double epsilon;
    private Qtable qtable;
    private Information popup;
    private double P_RATE = 1.0;


    public qlearning(double epsilon){
        this.epsilon = epsilon;
        this.qtable = new Qtable();
        Information popup = new Information();
        this.popup = popup;
    }

    @Override
    public Move execute(Game game) {
        if(random()){
            popup.setType(" RANDOM ");
            Move move = randomMove(game);
            return move;
        }
        else{
            popup.setType(" Q ");
            Move move = qMove(game);
            return move;
        }
    }


    public double getEpsilon(){
        return this.epsilon;
    }

    public void setEpsilon(double e){
        this.epsilon = e;
    }

    public boolean random(){
        double e = Math.random();
        if(e <= getEpsilon()){
            return true;
        }
        return false;
    }

    public Move randomMove(Game game){
        Generator generator = new Generator();
        ArrayList<Move> moves = generator.generateActions(game);
        int moveIndex = (int) (Math.random() * moves.size());
        Move move = moves.get(moveIndex);
        Generator converter = new Generator();
        State state = converter.convert(move, game);
        setNewQ(state);
        return move;
    }

    public double reward(State state){
        return state.reward();
    }

    public double setNewQ(State state){
        State state1 = getQtable().getState(state);
        if(state1==null){
            popup.error("STATE EQUALS NULL");
        }
        double oldQ = state1.getQ_value();
        double alpha = 1/1+state1.getVisited();
        double newQ = (1-alpha)*oldQ +  (alpha*0.5*reward(state1));
        state1.setQ_value(newQ);
        state1.visited();
        //popup.popup(oldQ,newQ);
        return newQ;
    }

    public Qtable getQtable() {
        return this.qtable;
    }

    private Move qMove(Game game){
        Generator generator = new Generator();
        ArrayList<Move> moves = generator.generateActions(game);
        Move move = highestQ(moves, game);
        State state = generator.convert(move, game);
        setNewQ(state);
        return move;
    }

    private Move highestQ(ArrayList<Move> moves, Game game){
        Generator gen = new Generator();
        double max = gen.convert(moves.get(0), game).getQ_value();
        Move maxMove = moves.get(0);
        for(Move move: moves){
            State state = gen.convert(move, game);
            double q = this.qtable.getState(state).getQ_value();
            if(q > max){
                max = q;
                maxMove = move;
            }
        }
        System.out.println("MAX MOVE :" + max);
        return maxMove;
    }

    public void end(){
        QTable_File f = new QTable_File();
        try {
            f.save_Qtable(this.getQtable());
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }


}